public enum SoundType {
    FUNNY("весело"),
    SUSPISIOUS("подозрительно"),
    NORMAL("");
    private String val;
    private SoundType(String s){
        val = s;
    }
    public String toString(){
        return val;
    }
}
