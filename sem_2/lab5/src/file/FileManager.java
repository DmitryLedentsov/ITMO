package file;

import static io.OutputManager.*;
import exceptions.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Operates the main collection file for saving/loading.
 */
public class FileManager implements ReaderWriter{
    private InputStream inputStream;
    private String path;
    /**
     * Constructor, just save variable for all class.
     * @param pth Path to collection file.
     */
    public FileManager(String pth){
        path = pth;
    }
    public void setPath(String pth){
        path = pth;
    }

    public FileManager(){
        path = null;
    }
    public String read()
    {
        String str = "";
        try {
            if (path == null) throw new EmptyPathException();
            InputStreamReader reader = null;

            File file = new File(path);
            if (!file.exists()) throw new FileNotExistsException();
            if(!file.canRead()) throw new FileWrongPermissionsException("cannot read file");
            inputStream = new FileInputStream(file);
            reader = new InputStreamReader(inputStream);
            int currectSymbol;
            while ((currectSymbol = reader.read()) != -1) {
                str += ((char)currectSymbol);
            }
            reader.close();        
        }
        catch(FileException e){
            printErr(e.getMessage());
        } catch (IOException e) {
            printErr("cannot access file");
        } 
        return str;
    }

    private void create(File file) throws CannotCreateFileException{
        try{
            file.createNewFile(); 
        } catch(IOException e){
            throw new CannotCreateFileException();
        }
    }
    public boolean write(String str){
        boolean res = true;
        try{
            if (path == null) throw new EmptyPathException();

            File file = new File(path);

            if(!file.exists()) {
                print("file " + path +" doesnt exist, trying to create new file");
                create(file);
            };
            if(!file.canWrite()) throw new FileWrongPermissionsException("cannot write file");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(str);
            //print("successfully saved");
            writer.close();
        } catch(FileException e){
            printErr(e.getMessage());
            res = false;
        } catch (IOException e) {
            res = false;
            printErr("cannot access file");
        }
        return res;
    }
}