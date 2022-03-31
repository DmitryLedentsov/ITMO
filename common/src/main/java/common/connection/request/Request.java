package common.connection.request;

import common.exceptions.InvalidDataException;

import java.io.Serializable;

public class Request<T extends Serializable> {
    private final T data;
    private final Type type;
    public Request(T obj, Type t){
        data = obj;
        type = t;
    }
    public Serializable getData(){
        return data;
    }
    public Type getType(){
        return type;
    }
    public T cast() throws InvalidDataException{
        try {
            return (T) data;
        }catch (ClassCastException e){
            throw new InvalidDataException("");
        }
    }
}
