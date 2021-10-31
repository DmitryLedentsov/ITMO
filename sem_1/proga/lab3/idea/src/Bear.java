public class Bear extends LifeObject implements Singable {
    public void Sing(Songs s, SoundType t){
        System.out.printf("объект медведь %s %s поет %s \n", toString(), t, s);
    }
    public void Sing(SoundType t){
        System.out.printf("объект медведь %s %s поет \n", toString(), t);
    }
    public final Nose nose;
    public Bear(String s){
        super(s);
        this.nose = new Nose("нос медведя " + toString());
    }
    public class Nose extends SceneObject{
        public Nose(String s){
            super(s);
        }
    }
}
