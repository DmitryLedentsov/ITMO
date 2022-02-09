package collection;


import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import data.Worker;
import json.*;

import static io.OutputManager.*;
import static utils.DateConverter.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


//@Alias Vector<Worker>  NameToNumbers;
/**
 * Operates collection.
 */
public class WorkerCollectionManager implements CollectionManager<Worker>{
    private Vector<Worker> collection;
    private java.time.LocalDateTime initDate;
    private HashSet<Integer> uniqueIds;
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
            uniqueIds.add(id);
            return id;
        }
    }

    public void sort(){
        Collections.sort(collection);
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
        worker.setId(generateNextId());
        collection.add(worker);
        print("Added element:");
        print(worker.toString());
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
        for (Worker worker: collection)
        {
            if (worker.getId() == ID)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete element by ID
     * @param id ID
     */
    public void removeByID(Integer id){
        for (Worker worker : collection){
            if (worker.getId() == id){
                collection.remove(worker);
                uniqueIds.remove(id);
                print("element #"+Integer.toString(id)+" successfully deleted");
                return;
            }
        }
    }
    /**
    * Delete element by ID
    * @param id ID
    */
   public void updateByID(Integer id, Worker newWorker){
       int idx = 0;
       for (Worker worker : collection){
           if (worker.getId() == id){
               newWorker.setId(id);
               collection.set(idx, newWorker);
               print("element #"+Integer.toString(id)+" successfully updated");
               return;
           }
           idx += 1;
       }
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
        print("element #"+Integer.toString(id)+" successfully deleted");

    }
    /**
     * Add if ID of element bigger then max in collection
     * @param worker Element
     */
    public void addIfMax(Worker worker){
        for (Worker Worker : collection){
            if (worker.compareTo(Worker)==-1){
                printErr("unable to add");
                return;
            }
        }
        add(worker);
    }

     /**
     * Add if ID of element smaller then min in collection
     * @param worker Element
     */
    public void addIfMin(Worker worker){
        for (Worker Worker : collection){
            if (worker.compareTo(Worker)==1){
                printErr("unable to add");
                return;
            }
        }
        add(worker);
    }

    public void printStartsWithName(String start){
        LinkedList<Worker> list = new LinkedList<>();
        for (Worker worker : collection){
            if (worker.getName().startsWith(start.trim())){
                list.add(worker);
            }
        }
        if (list.isEmpty()) print("none of elements have name which starts with " + start);
        else{
            print("starts with: " + start);
            for (Worker worker: list){
                print(worker.toString());
            }
        }
    }

    public void groupByEndDate(){
        HashMap<LocalDate,AtomicInteger> map = new HashMap<>();
        for (Worker worker: collection){
            LocalDate endDate = worker.getEndDate();
            if(endDate!=null){
                if (map.containsKey(endDate)){
                    map.get(endDate).incrementAndGet();
                } else{
                    map.put(endDate, new AtomicInteger(1));
                }
            }
        }
        if (map.size()==0) print("none of the elements have endDate field");
        else{
            Iterator<Map.Entry<LocalDate, AtomicInteger>> it = map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<LocalDate,AtomicInteger> pair= (Map.Entry<LocalDate,AtomicInteger>)it.next();
                LocalDate endDate = (LocalDate)pair.getKey();
                int quantity = ((AtomicInteger)(map.get(endDate))).intValue();
                print(dateToString(endDate)+" : " + Integer.toString(quantity));
            }
        }
    }

    public void printUniqueSalary(){
        LinkedList<Long> salaries = new LinkedList<>();
        print("unique salaries:");
        for (Worker worker: collection){
            if (! salaries.contains(worker.getSalary())){
                print(worker.getSalary());
                salaries.add(worker.getSalary());
            }
        }
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
            printErr("wrong json data");
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