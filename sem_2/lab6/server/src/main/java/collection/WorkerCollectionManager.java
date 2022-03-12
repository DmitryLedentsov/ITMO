package collection;


import java.util.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.stream.Collectors;

import common.data.*;
import json.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


//@Alias Vector<Worker>  NameToNumbers;
/**
 * Operates collection.
 */
public class WorkerCollectionManager implements CollectionManager<Worker>{
    private Vector<Worker> collection;
    private java.time.LocalDateTime initDate;
    private Set<Integer> uniqueIds;
    /**
     * Constructor, set start values
     */
    public WorkerCollectionManager()
    {
        uniqueIds = new HashSet<>();
        collection = new Vector<>();
        initDate = java.time.LocalDateTime.now();
    }

    public int generateNextId(){
        if (collection.isEmpty())
            return 1;
        else {
            Integer id = collection.lastElement().getId() + 1;
            if(uniqueIds.contains(id)){
                while (uniqueIds.contains(id)) id+=1;
            }
            //uniqueIds.add(id);
            return id;
        }
    }

    public void sort(){
        Collections.sort(collection, new Worker.SortingComparator());
    }

    /**
     * Return collection
     * @return Collection
     */
    public Vector<Worker> getCollection()
    {
        return collection;
    }

    /**
     * Add element to collection
     * @param worker Element of collection
     */
    public void add(Worker worker){
        Integer id = generateNextId();
        uniqueIds.add(id);
        worker.setId(id);
        collection.add(worker); 
    }


    /**
     * Get information about collection
     * @return Information
     */
    public String getInfo(){
        return "Vector of Worker, size: " + Integer.toString(collection.size()) + ", initialization date: " + initDate.toString();
    }

     /**
     * Give info about is this ID used
     * @param ID ID
     * @return is it used or not
     */
    public boolean checkID(Integer ID){
        if (uniqueIds.contains(ID)) return true;
        return false;
    }

    /**
     * Delete element by ID
     * @param id ID
     */
    public boolean removeByID(Integer id){
        Optional<Worker> worker = collection.stream()
                .filter(w->w.getId()==id)
                .findFirst();
        if(worker.isPresent()){
            collection.remove(worker.get());
            uniqueIds.remove(id);
            return  true;
        }
        return  false;
    }
    /**
    * Delete element by ID
    * @param id ID
    */
   public boolean updateByID(Integer id, Worker newWorker){
       Optional<Worker> worker = collection.stream()
               .filter(w-> w.getId() == id)
               .findFirst();
       if(worker.isPresent()){
           collection.remove(worker.get());
           newWorker.setId(id);
           collection.add(newWorker);
           return true;
       }
       return false;
   }

    /**
     * Get size of collection
     * @return Size of collection
     */
    public int getSize(){
        return collection.size();
    }

   
    public void clear(){
        collection.clear();
        uniqueIds.clear();
    }

    public void removeFirst(){
        int id = collection.get(0).getId();
        collection.remove(0);
        uniqueIds.remove(id);
    }
    /**
     * Add if ID of element bigger then max in collection
     * @param worker Element
     */
    public boolean addIfMax(Worker worker){
        if (collection.stream()
                .max(Worker::compareTo)
                .filter(w->w.compareTo(worker)==1)
                .isPresent())
        {
            return false;
        }
        add(worker);
        return true;
    }

     /**
     * Add if ID of element smaller then min in collection
     * @param worker Element
     */
    public boolean addIfMin(Worker worker){
        if (collection.stream()
                .min(Worker::compareTo)
                .filter(w->w.compareTo(worker)==-1)
                .isPresent())
        {
            return false;
        }
        add(worker);
        return true;
    }

    public List<Worker> filterStartsWithName(String start){

        List<Worker> list = collection.stream()
                .filter(w-> w.getName().startsWith(start.trim()))
                .collect(Collectors.toList());
        return list;
    }

    public Map<LocalDate,Integer> groupByEndDate(){
        HashMap<LocalDate,Integer> map = new HashMap<>();
        collection.stream()
                .filter((worker -> worker.getEndDate()!=null))
                .forEach((worker)->{
            LocalDate endDate = worker.getEndDate();
                if (map.containsKey(endDate)){
                    Integer q = map.get(endDate);
                    map.replace(endDate, q+1);
                } else{
                    map.put(endDate, 1);
                }
        });
        return map;
    }

    public List<Long> getUniqueSalaries(){
        List<Long> salaries = new LinkedList<>();
        //out.info("unique salaries:");
        salaries = collection.stream()
                .map(worker -> worker.getSalary())
                .distinct()
                .collect(Collectors.toList());
        return salaries;
    }

    public boolean deserializeCollection(String json){
        boolean success = true;
        try {
            if (json == null || json.equals("")){
                collection =  new Vector<Worker>();
            } else {
                Type collectionType = new TypeToken<Vector<Worker>>(){}.getType();
                Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(collectionType, new CollectionDeserializer(uniqueIds))
                .create();
                collection = gson.fromJson(json.trim(), collectionType);
            }
        } catch (JsonParseException e){
            success = false;
        } 
        return success;
        
    }

    public String serializeCollection(){
        if (collection == null || collection.isEmpty()) return "";
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
        .registerTypeAdapter(Date.class, new DateSerializer())
        .setPrettyPrinting().create();
        String json = gson.toJson(collection);
        return json;
    }
}