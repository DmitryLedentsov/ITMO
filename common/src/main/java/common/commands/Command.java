package common.commands;

import common.connection.Request;
import common.connection.Response;
import common.exceptions.CommandException;
import common.exceptions.ConnectionException;
import common.exceptions.FileException;
import common.exceptions.InvalidDataException;

/**
 * Command callback interface
 */

public interface Command extends  Acceptable{
    public String execute() throws InvalidDataException, CommandException, FileException, ConnectionException;
    final CommandType type = CommandType.NORMAL;
    final String name = "";
}