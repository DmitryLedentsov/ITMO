package main;


import java.io.PrintStream;

import client.Client;
import common.exceptions.ConnectionException;
import common.exceptions.InvalidPortException;
import common.exceptions.InvalidProgramArgumentsException;

import static common.io.OutputManager.*;




public class Main {
    //public static Logger logger = LogManager.getLogger("logger");
    //static final Logger logger = LogManager.getRootLogger();
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        print("\r\n __          __           _                 __  __                                         \r\n \\ \\        / /          | |               |  \\/  |                                        \r\n  \\ \\  /\\  / /___   _ __ | | __ ___  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __ \r\n   \\ \\/  \\/ // _ \\ | '__|| |/ // _ \\| '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|\r\n    \\  /\\  /| (_) || |   |   <|  __/| |    | |  | || (_| || | | || (_| || (_| ||  __/| |   \r\n     \\/  \\/  \\___/ |_|   |_|\\_\\\\___||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|   \r\n                                                                          __/ |            \r\n                                                                         |___/             \r\n");
        print("\t\t\t\t\t\t\t\t\t by Dimka Ledentsov");
        print("\n");

        //args = new String[]{"localhost","4445"};
        String addr  = "";
        int port = 0;
        try {
            if (args.length != 2) throw new InvalidProgramArgumentsException("no address passed by arguments");
            addr = args[0];
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e){
                throw new InvalidPortException();
            }
            Client client = new Client(addr,port);
            client.start();
        }
        catch (InvalidProgramArgumentsException| ConnectionException e){
            print(e.getMessage());
        }
        //System.out.println(res.getMessage());
    }
}
