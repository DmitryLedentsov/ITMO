import java.util.HashMap;

public class Rocket extends SceneObject implements Forceable, Directionable {
    final Section top;
    final Section bottom;
    private Directions direction;

    public Rocket(String s) {
        super(s);
        top = new Section("верхняя часть рокеты");
        bottom = new Section("хвостовая часть рокеты");
    }

    public void applyForce(Force F) {
        F.apply();
    }

    public void setDirection(Directions d) {
        direction = d;
        System.out.printf("объект %s отклонился %s", this, direction);
    }

    public abstract static class Detail extends SceneObject implements Placeable, Abilityable {
        private Rocket rocket;
        private SceneObject place;
        private Abilities abilities;

        Detail(String s) {
            super(s);
            rocket = null;
            abilities = new Abilities();
        }

        public void setPlace(SceneObject o) {
            place = o;
            System.out.printf("объект %s перемещен в объект %s", this, o);
        }

        public Rocket getRocket() {
            return rocket;
        }

        public void setRocket(Rocket r) {
            rocket = r;
        }

        public void addAbility(AbilityType t) {
            abilities.put(t);
            System.out.printf("объекту %s добавлена способность %s\n", this, t);
        }

        public boolean hasAbility(AbilityType t) {
            return abilities.has(t);
        }
    }

    public class Section extends SceneObject implements GetableContainer<Detail> {
        private HashMap<String, Detail> inside;

        public Section(String s) {
            super(s);
            inside = new HashMap<String, Detail>();
        }

        //public Placeable get(String a)
        public void put(Detail obj) {
            inside.put(obj.toString(), obj);
            obj.setPlace(this);//Rocket.this
            obj.setRocket(Rocket.this);
            System.out.printf("объект %s добавлен в объект %s\n", obj, this);
        }

        public void remove(Detail obj) {
            obj.setPlace(null);
            obj.setRocket(null);
            inside.remove(obj.toString());
            System.out.printf("объект %s удалён из объекта %s\n", obj, this);
        }

        public Detail get(String key) {
            return inside.get(key);
        }
    }

    public static class Engine extends Detail {
        private Section place;
        final Jet jet;

        public Engine(String s) {
            super(s);
            jet = this.new Jet("сопло");
            jet.setName(String.format("сопло объекта %s", this));
        }

        @Override
        public void setPlace(SceneObject p) {
            place = (Section) p;
        }

        public class Jet extends Detail implements Directionable, Mountable {
            private Engine place;
            private Directions direction;
            private MountType mount_type;

            public Jet(String s) {
                super(s);
                setPlace(Engine.this);
            }

            public void setPlace(SceneObject p) {
                place = (Engine) p;
                System.out.printf("объект %s перемещён в объект %s \n", this, place);
            }

            public void setDirection(Directions d) {
                direction = d;
                System.out.printf("объект %s установлен в направление %s\n", this, direction);
            }

            public void setMountType(MountType t) {
                mount_type = t;
                System.out.printf("объект %s установлен %s \n", this, mount_type);
            }

            public Directions getDirection() {
                return direction;
            }

            public void rotate(Directions d) {
                System.out.printf("объект %s поворачивается %s\n", this, d);
                this.setDirection(d);
            }
        }

        public void run() throws Exceptions.ExistException {
            if (this.jet.getDirection() == null) throw new Exceptions.ExistException("у объекта нет направления");
            System.out.printf("объект %s начинает работать\n", this);
            Gases gases = new Gases("горячие газы");
            gases.setDirection(this.jet.getDirection());
            gases.setPlace(this.jet);
            gases.burst();

            try {
                getRocket().applyForce(() -> {
                    Directions d = this.jet.getDirection().invert();
                    System.out.printf("реактивная сила толкает объект %s %s \n", this.getRocket(), d);
                });
            } catch (NullPointerException E) {
                throw new Exceptions.ExistException("двигатель не находится внутри рокеты");
            }
        }
    }
}
