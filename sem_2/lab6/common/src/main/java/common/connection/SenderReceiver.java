package common.connection;

import java.io.Closeable;

/**
 * interface for sending and receiving
 */
public interface SenderReceiver extends Closeable {
    public final int BUFFER_SIZE = 4096;
}
