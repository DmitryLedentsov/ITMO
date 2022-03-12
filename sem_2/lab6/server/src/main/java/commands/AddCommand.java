package commands;

import common.exceptions.*;
import collection.CollectionManager;
import common.commands.*;
import common.data.*;
public class AddCommand extends CommandImpl{
    private CollectionManager<Worker> collectionManager;
    public AddCommand(CollectionManager<Worker> cm){
        super("add",CommandType.NORMAL);
        collectionManager = cm;
    }
    public CollectionManager<Worker> getManager(){
        return collectionManager;
    }

    @Override
    public String execute() throws InvalidDataException, CommandException {
        getManager().add(getWorkerArg());
        return "Added element: " + getWorkerArg().toString();
    }
}
