package common.commands;

import common.connection.*;
import common.data.Worker;
import common.exceptions.*;

/**
 * basic command implementation
 */
public abstract class CommandImpl implements Command {
    private final CommandType type;
    private final String name;
    private Request arg;

    public CommandImpl(String n, CommandType t) {
        name = n;
        type = t;
    }

    public CommandType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
