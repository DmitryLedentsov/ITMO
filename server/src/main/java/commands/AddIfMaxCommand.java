package commands;

import common.exceptions.*;
import collection.CollectionManager;
import common.commands.*;
import common.data.*;

public class AddIfMaxCommand extends CommandImpl {
    private final CollectionManager<Worker> collectionManager;

    public AddIfMaxCommand(CollectionManager<Worker> cm) {
        super("add_if_max", CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute() {
        boolean success = collectionManager.addIfMax(getWorkerArg());
        if (success) return ("Added element: " + getWorkerArg().toString());
        else throw new CommandException("cannot add");
    }
}
