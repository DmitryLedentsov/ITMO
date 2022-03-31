package commands;

import common.commands.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

import collection.*;
import common.data.*;
import common.exceptions.*;

import static common.utils.DateConverter.*;

public class GroupCountingByEndDateCommand extends CommandImpl {
    private final CollectionManager<Worker> collectionManager;

    public GroupCountingByEndDateCommand(CollectionManager<Worker> cm) {
        super("group_counting_by_end_date", CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute() {
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        Map<LocalDate, Integer> map = collectionManager.groupByEndDate();
        if (map.isEmpty()) return "none of the elements have endDate field";

        String res = "";
        for (Map.Entry<LocalDate, Integer> pair : map.entrySet()) {
            LocalDate endDate = pair.getKey();
            int quantity = map.get(endDate);
            res += dateToString(endDate) + " : " + quantity + "\n";
        }

        return res;
    }
}
