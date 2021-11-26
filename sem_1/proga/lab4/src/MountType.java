public enum MountType{
    HORIZONTAL("горизонтально"),
    VERTICALLY("вертикально")
    ;
    private String val;
    private MountType(String s){
        val = s;
    }
    @Override
    public String toString() {
        return val;
    }
}
