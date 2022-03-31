package common.commands;

import common.connection.Status;

public interface Result {
    String getMessage();

    Status getStatus();
}
