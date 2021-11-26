public class Gases extends SceneObject implements Placeable, Directionable{
    private Directions direction;
    private SceneObject place;
    public Gases(String s){
      super(s);
    }
    public void setPlace(SceneObject o){
        place = o;
        System.out.printf("объект %s помещен в объект %s\n", this,place);
    }
    public void setDirection(Directions d){
        direction = d;
    }
    public void burst(){
        System.out.printf("объект %s вырывается из объекта %s %s\n", this,place,direction);
    }
}
