package common.connection.request;

public class AuthMsg {
    private final String login;
    private final String password;
    public AuthMsg(String login,String password){
        this.login = login;
        this.password = password;
    }
    public String getLogin(){
        return login;
    }

    public  String getPassword(){
        return  password;
    }
}
