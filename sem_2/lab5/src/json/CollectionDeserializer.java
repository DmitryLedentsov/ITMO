package json;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import data.*;
import com.google.gson.*;
import static io.OutputManager.*;

/**
 * type adapter for json deserialization
 */
public class CollectionDeserializer implements JsonDeserializer<Vector<Worker>>{
    private HashSet<Integer> uniqueIds;

    /**
     * constructor
     * @param uniqueIds set of ids. useful for generating new id
     */
    public CollectionDeserializer(HashSet<Integer> uniqueIds){
        this.uniqueIds = uniqueIds;
    }
    @Override
    public Vector<Worker> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        Vector<Worker> collection = new Vector<>();
        JsonArray workers = json.getAsJsonArray();
        int damagedElements = 0;
        for (JsonElement jsonWorker: workers){
            Worker worker = null;
            try{
                if(jsonWorker.getAsJsonObject().entrySet().isEmpty()){
                    printErr("empty worker found");
                    throw new JsonParseException("empty worker");
                }
                if(!jsonWorker.getAsJsonObject().has("id")) {
                    printErr("found worker without id");
                    throw new JsonParseException("no id");
                }
                worker = (Worker) context.deserialize(jsonWorker, Worker.class);
                
                Integer id = worker.getId();
                
                if(uniqueIds.contains(id)) {
                    printErr("database already contains worker with id #" + Integer.toString(id));
                    throw new JsonParseException("id isnt unique");
                }
                if(!worker.validate()) {
                    printErr("worker #"+Integer.toString(id) + " doesnt match specific conditions");
                    throw new JsonParseException("invalid worker data"); 
                }      
                uniqueIds.add(id);        
                collection.add(worker);
            } catch (JsonParseException e){
                damagedElements += 1;
            }
        }   
        if(collection.size()==0){
            if(damagedElements == 0) printErr("database is empty");
            else  printErr("all elements in database are damaged");
            throw new JsonParseException("no data");
        }
        if (damagedElements != 0) printErr(Integer.toString(damagedElements) + " elements in database are damaged"); 
        return collection;
    }
}
