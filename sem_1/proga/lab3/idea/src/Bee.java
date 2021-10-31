public class Bee extends LifeObject implements Flyable, Buzzable, Placeable{
    private SceneObject place;
    public Bee(String s){
        super(s);
    }

    public void TakeOff(){
        System.out.printf("объект %s взлетел c объекта %s \n", this, place);
    }

    public void SitOn(SceneObject obj){
        SetPlace(obj);
        System.out.printf("объект %s приземлился на объект %s \n", this, place);
    }
    public void FlyAround(SceneObject obj){
        System.out.printf("объект %s летает вокруг объекта %s \n", this, obj);
    }
    public void Buzz(SoundType t){
        System.out.printf("объект %s %s жужжит \n", this, t);
    }
    public void SetPlace(SceneObject obj){
        place = obj;
    }
}
