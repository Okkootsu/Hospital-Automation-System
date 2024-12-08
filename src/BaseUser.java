public abstract class BaseUser {

    protected int id;
    protected String username;
    protected String password;
    protected long tc;
    protected String table;

    public abstract String getTable();
    public abstract int getId(BaseUser user);
    public abstract void delUser();
}
