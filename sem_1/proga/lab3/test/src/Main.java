public class Main {
    public static void main(String[] args) throws Exception {
        Nest nest = new Nest("гнездо");
        Bear tychka = new Bear("тучка");
        Bee bees = new Bee("пчелы");
        nest.Put(bees);
        tychka.Sing(Songs.TYCHKA_SONG, SoundType.NORMAL);
        bees.Buzz(SoundType.SUSPISIOUS);
        bees.TakeOff();
        tychka.Sing(Songs.SECOND_VERSE, SoundType.NORMAL);
        bees.FlyAround(tychka);
        Bee one_bee = new Bee("одна пчела");
        one_bee.SitOn(tychka.nose);
        one_bee.TakeOff();
    }
}
