package common.connection;

import java.io.Serializable;

import common.data.Worker;

/**
 * Message witch include command and arguments
 */
public class CommandMsg implements Request {
    private final String commandName;
    private final String commandStringArgument;
    private final Serializable commandObjectArgument;

    public CommandMsg(String commandNm, String commandSA, Serializable commandOA) {
        commandName = commandNm;
        commandStringArgument = commandSA;
        commandObjectArgument = commandOA;
    }

    /**
     * @return Command name.
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * @return Command string argument.
     */
    public String getStringArg() {
        return commandStringArgument;
    }

    /**
     * @return Command object argument.
     */
    public Worker getWorker() {
        return (Worker) commandObjectArgument;
    }
}