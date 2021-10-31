public class Nest extends SceneObject {
    public Nest(String s){
        super(s);
    }
    private Placeable inside;
    public void Put(Placeable obj){
        inside = obj;
        inside.SetPlace(this);
        System.out.printf("объект %s добавлен в объект %s\n",inside,this);
    }
}
