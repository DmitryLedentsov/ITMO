package commands;

import common.data.Worker;

public class Argument implements common.connection.Request {
    private final String arg;
    private final Worker worker;

    public Argument(String s, Worker w) {
        arg = s;
        worker = w;
    }

    public String getStringArg() {
        return arg;
    }

    public Worker getWorker() {
        return worker;
    }

    public String getCommandName() {
        return "";
    }
}
