package commands;

    
import common.commands.*;

import java.util.List;

import collection.*;
import common.data.*;
import common.exceptions.*;
public class PrintUniqueSalaryCommand extends CommandImpl{
    private CollectionManager<Worker> collectionManager;
    public PrintUniqueSalaryCommand(CollectionManager<Worker> cm){
        super("print_unique_salary",CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute(){
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        List<Long> list = collectionManager.getUniqueSalaries();
        return list.stream().map(n->Long.toString(n)).reduce("", (a,b)->a + b + "\n");
    }
}

