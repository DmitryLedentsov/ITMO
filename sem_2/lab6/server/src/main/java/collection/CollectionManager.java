package collection;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * interface for storing elements
 * @param <T> type of elements
 */
public interface CollectionManager<T> {
    /**
     * generates new unique ID for collection
     * @return
     */
    public int generateNextId();

    /**
     * sorts collection
     */
    public void sort();
    
    public Vector<T> getCollection();

    /**
     * adds new element
     * @param element
     */
    public void add(T element);

    /**
     * get information about collection
     * @return info
     */
    public String getInfo();

    /**
     * checks if collection contains element with particular id
     * @param ID
     * @return
     */
    public boolean checkID(Integer ID);

    /**
     * removes element by id
     * @param id
     */
    public boolean removeByID(Integer id);

    /**
     * updates element by id
     * @param id
     * @param newElement
     */
    public boolean updateByID(Integer id, T newElement);

    /**
     * get collection size
     * @return
     */
    public int getSize();
   
    public void clear();

    public void removeFirst();

    /**
     * adds element if it is greater than max
     * @param element
     */
    public boolean addIfMax(T element);

    /**
     * adds element if it is smaller than min
     * @param element
     */
    public boolean addIfMin(T element);

    /**
     * print all elements which name starts with substring
     * @param start
     */
    public  List<T> filterStartsWithName(String start);

    public Map<LocalDate,Integer> groupByEndDate();

    /**
     * print all unique values of salary field
     */
    public List<Long> getUniqueSalaries();

    /**
     * convert collection to json
     * @param json
     * @return true if success, false if not
     */
    public boolean deserializeCollection(String json);

    /**
     * parse collection from json
     * @return
     */
    public String serializeCollection();
   
}
