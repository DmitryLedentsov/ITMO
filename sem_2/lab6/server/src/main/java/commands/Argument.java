package commands;

import common.data.Worker;

public class Argument implements common.connection.Request{
    private String arg;
    private Worker worker;
    public Argument(String s, Worker w){
        arg = s;
        worker = w;
    }
    public String getStringArg(){
        return arg;
    }
    public Worker getWorker(){
        return worker;
    }
    public String getCommandName(){
        return "";
    }
}
