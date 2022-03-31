package common.data;

import java.time.LocalDate;
import java.util.Date;

public class DefaultWorker extends Worker {
    public DefaultWorker(String name, Coordinates coordinates, Long salary, LocalDate endDate, Position position, Status status, Organization organization) {
        super(name, coordinates, salary, endDate, position, status, organization);
        setCreationDate(new Date());
    }
}
