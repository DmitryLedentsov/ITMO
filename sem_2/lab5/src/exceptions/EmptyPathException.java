package exceptions;

public class EmptyPathException extends FileException{
    public EmptyPathException(){
        super("path is empty");
    }
}
