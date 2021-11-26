public class Exceptions {
    public static class ExistException extends RuntimeException{
        public ExistException(String s){
            super(s);
        }
    }
    public static class ScenarioException extends  Exception{
        public  ScenarioException(){
            super();
        }
    }

}
