package common.connection;

import java.io.Serializable;

import common.data.Worker;

public interface Request extends Serializable {
    String getStringArg();

    Worker getWorker();

    String getCommandName();
}
