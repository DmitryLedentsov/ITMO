
public class Main {
    public static void main(String[] args) throws Exceptions.ScenarioException {
        /*
        Nest nest = new Nest("гнездо");
        Bear tychka = new Bear("тучка");
        Bee bees = new Bee("пчелы");
        nest.put(bees);
        tychka.sing(Songs.TYCHKA_SONG, SoundType.NORMAL);
        bees.buzz(SoundType.SUSPISIOUS);
        bees.takeOff();
        nest.remove(bees);
        tychka.sing(Songs.SECOND_VERSE, SoundType.NORMAL);
        bees.flyAround(tychka);
        Bee one_bee = new Bee("одна пчела");
        one_bee.sitOn(tychka.nose);
        one_bee.takeOff();
        */
        try {


            Rocket rocket = new Rocket("рокета");

            Rocket.Engine engine = new Rocket.Engine("двигатель");
            Rocket.Engine main_engine = new Rocket.Engine("главный двигатель");
            Rocket.Engine rotary_engine = new Rocket.Engine("двигатель поворота") {
                @Override
                public void run() {
                    super.run();
                    getRocket().setDirection(this.jet.getDirection().invert());
                }
            };
            rocket.bottom.put(engine);
            rocket.bottom.put(main_engine);

            main_engine.addAbility(AbilityType.TRANSLATIONAL_MOTION);
            main_engine.jet.setMountType(MountType.VERTICALLY);
            main_engine.jet.setDirection(Directions.DOWN);
            main_engine.run();

            rocket.top.put(rotary_engine);
            rotary_engine.jet.setMountType(MountType.HORIZONTAL);
            rotary_engine.jet.addAbility(AbilityType.ROTATE_ANY_DIRECTION);
            //rotary_engine.jet.setDirection(Directions.EAST);
            rotary_engine.jet.rotate(Directions.EAST);
            rotary_engine.run();

            Rocket.Engine engine_1 = new Rocket.Engine("тестовый двигатель");
            engine_1.jet.setDirection(Directions.DOWN);
            engine_1.run();
        } catch (Exception e){
            System.err.println("что то пошло не так: " + e.getMessage());
            throw new Exceptions.ScenarioException();
        } finally {
            System.out.println("конец");
        }
    }
}
