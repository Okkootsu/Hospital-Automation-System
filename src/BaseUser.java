import java.sql.ResultSet;

public abstract class BaseUser {

    protected int id;
    protected String username;
    protected String password;
    protected long tc;
    protected String table;
    protected String userType;

    public abstract String getTable();
    public abstract int getId(BaseUser user);
    public abstract String getUsername(BaseUser user);
    public abstract String getUserType();
    public abstract void delUser();
    public abstract ResultSet getApt(BaseUser user);
    public abstract void createApt(int id, String clinic, String doctor, String date);
}
