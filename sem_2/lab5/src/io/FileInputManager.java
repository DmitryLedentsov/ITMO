package io;
import java.util.Scanner;

import file.FileManager;
/**
 * Operates input
 */
public class FileInputManager extends InputManagerImpl{
    public FileInputManager(String path){
        super(new Scanner(new FileManager(path).read()));
        getScanner().useDelimiter("\n");
    }
}
