import javax.swing.*;
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
    public abstract void delThisUser();
    public abstract ResultSet getApt();
    public abstract void delApt(int aptID);
    public abstract void createApt(int id, String clinic, String doctor, String date);
    public abstract void addEmployee(BaseUser employee, String userRole);
    public abstract void update(String update, String newValue);

    public abstract JPanel getPanel();

    public abstract void updateInfo();

    public abstract ResultSet getUsers();

    public abstract String getUserType();
}
