package exceptions;
/**
 * thrown when input doesnt match enum
 */
public class InvalidEnumException extends InvalidDataException{
    public InvalidEnumException(){
        super("wrong constant");
    }
}
