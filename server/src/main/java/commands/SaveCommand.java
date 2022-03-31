package commands;

import collection.CollectionManager;
import common.commands.*;
import common.data.Worker;
import common.exceptions.*;
import common.file.ReaderWriter;

public class SaveCommand extends CommandImpl {
    ReaderWriter fileManager;
    CollectionManager<Worker> collectionManager;

    public SaveCommand(CollectionManager<Worker> cm, ReaderWriter fm) {
        super("save", CommandType.SERVER_ONLY);
        collectionManager = cm;
        fileManager = fm;
    }

    @Override
    public String execute() throws FileException {
        if (hasStringArg()) {
            fileManager.setPath(getStringArg());
        }
        fileManager.write(collectionManager.serializeCollection());
        return "collection successfully saved";
    }
}
