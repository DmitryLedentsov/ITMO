package main;
import data.*;
import file.FileManager;
import io.*;

import java.io.PrintStream;
import collection.CollectionManager;
import collection.WorkerCollectionManager;

import static io.OutputManager.*;

import commands.*;
public class Main {
    
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        print("\r\n __          __           _                 __  __                                         \r\n \\ \\        / /          | |               |  \\/  |                                        \r\n  \\ \\  /\\  / /___   _ __ | | __ ___  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __ \r\n   \\ \\/  \\/ // _ \\ | '__|| |/ // _ \\| '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|\r\n    \\  /\\  /| (_) || |   |   <|  __/| |    | |  | || (_| || | | || (_| || (_| ||  __/| |   \r\n     \\/  \\/  \\___/ |_|   |_|\\_\\\\___||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|   \r\n                                                                          __/ |            \r\n                                                                         |___/             \r\n");
        print("\t\t\t\t\t\t\t\t\t by Dimka Ledentsov");
        print("\n");
        FileManager fileManager = new FileManager();
        CollectionManager<Worker> collectionManager = new WorkerCollectionManager();
        if (args.length!=0){
            fileManager.setPath(args[0]);
            collectionManager.deserializeCollection(fileManager.read());
        } else{
            print("no file passed by argument. you can load file using load command");
        }
        
        InputManager consoleManager = new ConsoleInputManager();
        CommandManager commandManager = new CommandManager(collectionManager,consoleManager,fileManager);
        commandManager.consoleMode();    
    }
}
