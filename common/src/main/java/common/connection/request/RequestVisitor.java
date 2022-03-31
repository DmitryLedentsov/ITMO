package common.connection.request;
public interface RequestVisitor {
    public void visit(AuthMsg msg);
    public void visit(CommandMsg msg);
}
