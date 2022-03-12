package common.connection;

import java.io.Serializable;

import common.data.Worker;

public interface Request extends Serializable{
    public String getStringArg();
    public Worker getWorker();
    public String getCommandName();
}
