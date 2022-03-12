package common.commands;

import common.connection.Request;
import common.connection.Response;

/**
 * Command callback interface
 */

public interface Command {
    public Response run();
    public String getName();
    public CommandType getType();
    public void setArgument(Request a);
}