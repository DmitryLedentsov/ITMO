package common.connection;

import java.io.Serializable;

public interface Response extends Serializable{
    public String getMessage();
    public Status getStatus();
}
