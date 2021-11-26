public class Bee extends LifeObject implements Flyable, Buzzable, Placeable{
    private SceneObject place;
    
    public Bee(String s){
        super(s);
    }

    public void takeOff(){
        System.out.printf("объект %s взлетел c объекта %s \n", this, place);
    }

    public void sitOn(SceneObject obj){
        setPlace(obj);
        System.out.printf("объект %s приземлился на объект %s \n", this, place);
    }
    public void flyAround(SceneObject obj){
        System.out.printf("объект %s летает вокруг объекта %s \n", this, obj);
    }
    public void buzz(SoundType t){
        System.out.printf("объект %s %s жужжит \n", this, t);
    }
    public void setPlace(SceneObject obj){
        place = obj;
    }
}
