package common.connection.request;

public interface Acceptable {
    public void accept(RequestVisitor visitor);
}
