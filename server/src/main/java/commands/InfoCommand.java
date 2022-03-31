package commands;

import common.exceptions.*;
import collection.CollectionManager;
import common.commands.*;
import common.data.*;

public class InfoCommand extends CommandImpl {
    private final CollectionManager<Worker> collectionManager;

    public InfoCommand(CollectionManager<Worker> cm) {
        super("info", CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute() throws InvalidDataException {
        return collectionManager.getInfo();
    }

}
