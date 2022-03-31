package common.connection;

import common.commands.Result;

import java.io.Serializable;

public interface Response extends Serializable, Result {
    String getMessage();

    Status getStatus();
}
