
public abstract class SceneObject {
    private String Name;
    public SceneObject(String s){
        this.Name = s;
        System.out.printf("объект %s создан \n", toString());
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass()!= obj.getClass()) return false;
        SceneObject another = (SceneObject)obj;
        return this.toString() == another.toString();
    }
    @Override
    public int hashCode() {
        return this.Name.hashCode();
    }
    @Override
    public String toString(){
        return this.Name;
    }
}
