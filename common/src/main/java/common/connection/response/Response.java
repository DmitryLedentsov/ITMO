package common.connection.response;


import common.connection.request.Type;

import java.io.Serializable;

public class Response {
    private Serializable data;
    private Type type;
    public Response(Serializable obj, Type t){
        data = obj;
        type = t;
    }
    public Serializable getData(){
        return data;
    }
    public Type getType(){
        return type;
    }
}
