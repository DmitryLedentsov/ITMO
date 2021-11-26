public enum Directions{
    EAST("на восток"),
    WEST("на запад"),
    NORTH("север"),
    SOUTH("юг"),
    DOWN("вниз"),
    UP("вверх"),
    HORIZONTAL("горизонтально"),
    EAST_DIRECTION("восточном направлении")
    ;
    private String val;
    private Directions(String s){
        val = s;
    }
    @Override
    public String toString(){
        return val;
    }

    public Directions invert(){
        switch (this){
            case EAST:
            return WEST;
            case WEST:
            return EAST;
            case UP:
            return DOWN;
            case DOWN:
            return UP;
            default:
            return null; //error
        }

    }
}
