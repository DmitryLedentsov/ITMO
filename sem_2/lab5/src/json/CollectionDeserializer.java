package json;

import java.lang.reflect.Type;
import java.util.Vector;
import data.*;
import com.google.gson.*;
import static io.OutputManager.*;

/**
 * type adapter for json deserialization
 */
public class CollectionDeserializer implements JsonDeserializer<Vector<Worker>>{
    @Override
    public Vector<Worker> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        Vector<Worker> collection = new Vector<>();
        JsonArray workers = json.getAsJsonArray();
        int damagedElements = 0;
        for (JsonElement jsonWorker: workers){
            Worker worker = null;
            try{
                worker = (Worker) context.deserialize(jsonWorker, Worker.class);
                if(!worker.validate()) throw new JsonParseException("invalid data");
                else collection.add(worker);
            } catch (JsonParseException e){
                //printErr(e.getMessage());
                damagedElements += 1;
            }
        }   
        if (damagedElements != 0) printErr(Integer.toString(damagedElements) + " elements in database are damaged. the rest were loaded successfully"); 
        return collection;
    }
}
