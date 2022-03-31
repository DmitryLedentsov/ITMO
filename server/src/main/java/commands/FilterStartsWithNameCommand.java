package commands;

import common.commands.*;

import java.util.List;

import collection.*;
import common.data.*;
import common.exceptions.*;

public class FilterStartsWithNameCommand extends CommandImpl {
    private final CollectionManager<Worker> collectionManager;

    public FilterStartsWithNameCommand(CollectionManager<Worker> cm) {
        super("filter_starts_with_name", CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute() {
        if (!hasStringArg()) throw new MissedCommandArgumentException();
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        String start = getStringArg();
        List<Worker> list = collectionManager.filterStartsWithName(getStringArg());
        if (list.isEmpty()) return "none of elements have name which starts with " + start;
        return list.stream()
                .sorted(new Worker.SortingComparator())
                .map(Worker::toString).reduce("", (a, b) -> a + b + "\n");
    }
}
