public enum Songs {
    TYCHKA_SONG(": 'Так весело поют!'"),
    SECOND_VERSE("второй куплет");
    private String val;
    private Songs(String s){
        val = s;
    }
    @Override
    public String toString(){
        return val;
    }
}
