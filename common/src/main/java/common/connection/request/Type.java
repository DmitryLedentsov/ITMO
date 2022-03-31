package common.connection.request;

import common.connection.AnswerMsg;
import common.connection.Response;

public enum Type {
    EXEC_COMMAND,
    AUTH;
    public void A(){
        Request<Response,Type> r = new Request<Response,Type>(new AnswerMsg(),Type.EXEC_COMMAND);
        boolean b = r.getType()==Type.EXEC_COMMAND;
    }
}
