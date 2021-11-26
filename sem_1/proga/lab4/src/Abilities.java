import java.util.HashSet;
public class Abilities implements Container<AbilityType>{
    private HashSet<AbilityType> inner;
    public Abilities(){
        inner = new HashSet<AbilityType>();
    }
    public void put(AbilityType t){
        inner.add(t);
    }
    public void remove(AbilityType t){
        inner.remove(t);
    }
    public boolean has(AbilityType t){
        return inner.contains(t);
    }
}