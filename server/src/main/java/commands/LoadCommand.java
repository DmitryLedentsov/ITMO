package commands;

import collection.CollectionManager;
import common.commands.*;
import common.data.Worker;
import common.exceptions.*;
import common.file.ReaderWriter;

public class LoadCommand extends CommandImpl {
    ReaderWriter fileManager;
    CollectionManager<Worker> collectionManager;

    public LoadCommand(CollectionManager<Worker> cm, ReaderWriter fm) {
        super("load", CommandType.SERVER_ONLY);
        collectionManager = cm;
        fileManager = fm;
    }

    @Override
    public String execute() throws FileException {
        if (hasStringArg()) {
            fileManager.setPath(getStringArg());
        }
        collectionManager.deserializeCollection(fileManager.read());
        return "collection successfully loaded";
    }
}
