package collection;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * interface for storing elements
 *
 * @param <T> type of elements
 */
public interface CollectionManager<T> {
    /**
     * generates new unique ID for collection
     *
     * @return
     */
    int generateNextId();

    /**
     * sorts collection
     */
    void sort();

    Vector<T> getCollection();

    /**
     * adds new element
     *
     * @param element
     */
    void add(T element);

    /**
     * get information about collection
     *
     * @return info
     */
    String getInfo();

    /**
     * checks if collection contains element with particular id
     *
     * @param ID
     * @return
     */
    boolean checkID(Integer ID);

    /**
     * removes element by id
     *
     * @param id
     */
    boolean removeByID(Integer id);

    /**
     * updates element by id
     *
     * @param id
     * @param newElement
     */
    boolean updateByID(Integer id, T newElement);

    /**
     * get collection size
     *
     * @return
     */
    int getSize();

    void clear();

    void removeFirst();

    /**
     * adds element if it is greater than max
     *
     * @param element
     */
    boolean addIfMax(T element);

    /**
     * adds element if it is smaller than min
     *
     * @param element
     */
    boolean addIfMin(T element);

    /**
     * print all elements which name starts with substring
     *
     * @param start
     */
    List<T> filterStartsWithName(String start);

    Map<LocalDate, Integer> groupByEndDate();

    /**
     * print all unique values of salary field
     */
    List<Long> getUniqueSalaries();

    /**
     * convert collection to json
     *
     * @param json
     * @return true if success, false if not
     */
    boolean deserializeCollection(String json);

    /**
     * parse collection from json
     *
     * @return
     */
    String serializeCollection();

}
