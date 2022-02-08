package commands;

import static io.OutputManager.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import collection.CollectionManager;
import data.*;
import exceptions.*;
import file.ReaderWriter;
import io.*;
public class CommandManager implements Commandable{
    private Map<String,Command> map;
    private CollectionManager<Worker> collectionManager;
    private InputManager inputManager;
    private ReaderWriter fileManager;
    private boolean isRunning;
    private String currentScriptFileName;

    private static Stack<String> callStack = new Stack<>();

    public void clearStack(){
        callStack.clear();
    }
    public CommandManager(CollectionManager<Worker> cManager, InputManager iManager, ReaderWriter fManager){

        isRunning = false;
        this.inputManager = iManager;
        this.collectionManager = cManager;
        this.fileManager = fManager;

        currentScriptFileName = "";
        map = new HashMap<String,Command>();
        addCommand("info", (a)->print(collectionManager.getInfo()));
        addCommand("help", (a)->print(getHelp()));
        addCommand("show", (a)->{
            if (collectionManager.getCollection().isEmpty()) print("collection is empty");
            else print(collectionManager.serializeCollection());
        });
        addCommand("add", (a)->{
            collectionManager.add(inputManager.readWorker());
        });
        addCommand("update", (arg)->{
            int id = 0;
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            }
            try{
                id = Integer.parseInt(arg);
            } catch (NumberFormatException e){
                throw new InvalidCommandArgumentException("id must be integer");
            }
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            if (!collectionManager.checkID(id)) throw new InvalidCommandArgumentException("no such id");
            
            collectionManager.updateByID(id, inputManager.readWorker());
            
        });
        addCommand("remove_by_id", (arg)->{
            int id = 0;
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            }
            try{
                id = Integer.parseInt(arg);
            } catch (NumberFormatException e){
                throw new InvalidCommandArgumentException("id must be integer");
            }
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            if (!collectionManager.checkID(id)) throw new InvalidCommandArgumentException("no such id");
            
            collectionManager.removeByID(id);
            
        });
        addCommand("clear", (a)->{
            collectionManager.clear();
        });

        addCommand("save", (arg)->{
            if (!(arg == null ||arg.equals(""))) fileManager.setPath(arg);
            if (collectionManager.getCollection().isEmpty()) print("collection is empty");
            if(!fileManager.write(collectionManager.serializeCollection())) throw new CommandException("cannot save collection");
            
        });
        addCommand("execute_script",(arg)->{
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            } 
            
            if (callStack.contains(currentScriptFileName)) throw new RecursiveScriptExecuteException();

            callStack.push(currentScriptFileName);
            CommandManager process = new CommandManager(collectionManager, inputManager, fileManager);
            process.fileMode(arg);
            callStack.pop();
            print("successfully executed script " + arg);
            
        });
        addCommand("exit", (a)->isRunning=false);
        addCommand("remove_first", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.removeFirst();
        });
        addCommand("add_if_max", (a)->collectionManager.addIfMax(inputManager.readWorker()));
        addCommand("add_if_min", (a)->collectionManager.addIfMin(inputManager.readWorker()));
        addCommand("group_counting_by_end_date", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.groupByEndDate();
        });
        addCommand("filter_starts_with_name", (arg)->{
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            } else{
                if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
                collectionManager.printStartsWithName(arg);
            }
        });
        addCommand("print_unique_salary", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.printUniqueSalary();
        });

        addCommand("load", (arg)->{
            if (!(arg == null ||arg.equals(""))) fileManager.setPath(arg);
            String data = fileManager.read();
            if(data.equals("")) throw new CommandException("cannot load, data not found");
            collectionManager.deserializeCollection(data);
            print("collection successfully loaded");
        });
    }

    public void addCommand(String key, Command c){
        map.put(key, c);
    }

    public void runCommand(String s, String arg){
        try{
            if (! hasCommand(s)) throw new NoSuchCommandException();
            map.get(s).run(arg);
        } 
        catch(CommandException|InvalidDataException e){
            printErr(e.getMessage());
        }
    }
    public void runCommand(String s){
        runCommand(s,null);
    }
    public boolean hasCommand(String s){
        return map.containsKey(s);
    }
    public void consoleMode(){
        inputManager = new ConsoleInputManager();
        isRunning = true;
        while(isRunning){
            System.out.print("enter command (help to get command list): ");
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }
    public void fileMode(String path){
        currentScriptFileName = path;
        isRunning = true;
        inputManager = new FileInputManager(path);
        isRunning = true;
        while(isRunning && inputManager.getScanner().hasNextLine()){
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }

    public static String getHelp(){ 
        return "\r\nhelp : show help for available commands\r\n\r\ninfo : Write to standard output information about the collection (type,\r\ninitialization date, number of elements, etc.)\r\n\r\nshow : print to standard output all elements of the collection in\r\nstring representation\r\n\r\nadd {element} : add a new element to the collection\r\n\r\nupdate id {element} : update the value of the collection element whose id\r\nequal to given\r\n\r\nremove_by_id id : remove an element from the collection by its id\r\n\r\nclear : clear the collection\r\n\r\nsave (file_name - optional) : save the collection to a file\r\n\r\nload (file_name - optional): load collection from file\r\n\r\nexecute_script file_name : read and execute script from specified file.\r\nThe script contains commands in the same form in which they are entered\r\nuser is interactive.\r\n\r\nexit : exit the program (without saving to a file)\r\n\r\nremove_first : remove the first element from the collection\r\n\r\nadd_if_max {element} : add a new element to the collection if its\r\n\r\nvalue is greater than the value of the largest element of this collection\r\n\r\nadd_if_min {element} : add a new element to the collection if it\r\nthe value is less than the smallest element of this collection\r\n\r\ngroup_counting_by_end_date : group the elements of the collection by\r\nthe value of the endDate field, display the number of elements in each group\r\n\r\nfilter_starts_with_name name : output elements, value of field name\r\nwhich starts with the given substring\r\n\r\nprint_unique_salary : print the unique values of the salary field of all\r\nitems in the collection\r\n";
    }
}
