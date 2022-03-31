package common.io;

import java.util.Scanner;

import common.exceptions.FileException;
import common.file.FileManager;

/**
 * Operates input
 */
public class FileInputManager extends InputManagerImpl {
    public FileInputManager(String path) throws FileException {
        super(new Scanner(new FileManager(path).read()));
        getScanner().useDelimiter("\n");
    }
}
