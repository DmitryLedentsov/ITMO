import java.util.ArrayList;
public class Nest extends SceneObject implements Container<Placeable>{
    public Nest(String s){
        super(s);
        inside = new ArrayList<Placeable>();
    }
    private ArrayList<Placeable> inside;
    public void put(Placeable obj){
        inside.add(obj);
        obj.setPlace(this);
        System.out.printf("объект %s добавлен в объект %s\n",obj,this);
    }
    public void remove(Placeable obj){
        obj.setPlace(null);
        inside.remove(obj);
        System.out.printf("объект %s удалён из объекта %s\n",obj,this);
    }
}
