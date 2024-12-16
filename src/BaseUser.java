import javax.swing.*;
import java.sql.ResultSet;

public abstract class BaseUser {
    //User fields
    protected int id;
    protected String username;
    protected String password;
    protected long tc;
    protected String table;
    protected String userType;


    //Genel kullanıcı metodları
    public abstract String getTable();
    public abstract int getId(BaseUser user);
    public abstract String getUsername(BaseUser user);
    public abstract String getUserType();
    public abstract void delThisUser();

    //Genel GUI çağırma metodu
    public abstract JPanel getPanel();

    //Admin metodları
    public abstract ResultSet getUsers();
    public abstract void addEmployee(BaseUser employee, String userRole);
    public abstract void addToClinic(BaseUser employee, String clinic);


    //Teşhis metodları -> Customer, Doktor için
    public abstract ResultSet getDiagnoses();
    public abstract void createDiagnose(int aptID, int customerID, String diagnose, String date);

    //Randevu metodları -> Customer, Doktor için
    public abstract ResultSet getApt();
    public abstract void delApt(int aptID);
    public abstract void createApt(int id, String doctor, String date);

    //Genel metod
    public abstract void update(String update, String newValue);


    public abstract void updateInfo();
}
