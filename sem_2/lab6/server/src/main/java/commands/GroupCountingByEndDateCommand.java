package commands;
import common.commands.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

import collection.*;
import common.data.*;
import common.exceptions.*;
import static common.utils.DateConverter.*;
public class GroupCountingByEndDateCommand extends CommandImpl{
    private CollectionManager<Worker> collectionManager;
    public GroupCountingByEndDateCommand(CollectionManager<Worker> cm){
        super("group_counting_by_end_date",CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute(){
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        Map<LocalDate,Integer> map = collectionManager.groupByEndDate();
        if(map.isEmpty()) return "none of the elements have endDate field";

        String res = "";
        Iterator<Map.Entry<LocalDate, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<LocalDate,Integer> pair= (Map.Entry<LocalDate,Integer>)it.next();
            LocalDate endDate = (LocalDate)pair.getKey();
            int quantity = ((Integer)(map.get(endDate))).intValue();
            res+=dateToString(endDate)+" : " + Integer.toString(quantity) + "\n";
        }
        
        return res;
    }
}
