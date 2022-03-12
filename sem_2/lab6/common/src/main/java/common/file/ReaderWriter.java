package common.file;

import common.exceptions.FileException;

public interface ReaderWriter {
    /**
     * sets path to file
     * @param pth
     */
    public void setPath(String pth);

    /**
     * reads data
     * @return
     */
    public String read() throws FileException;

    /**
     * saves data
     * @param data
     */
    public void write(String data) throws FileException;
}
