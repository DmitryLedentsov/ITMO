public enum AbilityType {
    TRANSLATIONAL_MOTION("сообщать поступательное движение"),
    ROTATE_ANY_DIRECTION("поворачиваться в любую сторону \n" +
            "Если, например, нужно было повернуть ракету на запад, сопло двигателя поворачивалось на восток\n")
    ;

    private String val;
    private AbilityType(String s){
            val = s;
        }
    @Override
    public String toString(){
        return val;
    }
}
