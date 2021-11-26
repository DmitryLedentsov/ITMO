
public abstract class SceneObject implements Nameable {
    private String name;


    public SceneObject(String s){
        this.name = s;
        System.out.printf("объект %s создан \n", toString());
    }
    public SceneObject(){}
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass()!= obj.getClass()) return false;
        SceneObject another = (SceneObject)obj;
        return this.toString() == another.toString();
    }
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    @Override
    public String toString(){
        return this.getName();
    }
    public SceneObject setName(String s){
        name = s;
        return this;
    }

    public String getName(){
        return name;
    }
}
