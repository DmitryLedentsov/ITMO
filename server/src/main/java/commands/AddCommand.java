package commands;

import common.exceptions.*;
import collection.CollectionManager;
import common.commands.*;
import common.data.*;

public class AddCommand implements Command {
    private Worker worker;
    public final String name = "add";
    public final CommandType type = CommandType.NORMAL;
    private final CollectionManager<Worker> collectionManager;
    public AddCommand(CollectionManager<Worker> cm) {
        //super("add",CommandType.NORMAL);
        collectionManager = cm;
    }
    public void setArg(Worker w){
        worker = w;
    }
    public CollectionManager<Worker> getManager() {
        return collectionManager;
    }

    @Override
    public String execute() throws InvalidDataException, CommandException {
        getManager().add(worker);

        return "Added element: " + worker;
    }

    @Override public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
