import javax.swing.*;
import java.sql.ResultSet;

public class Employee extends BaseUser{

    private String table = "employee";
    private String userRole = "Çalışan";

    @Override
    public String getTable() {
        return table;
    }

    @Override
    public int getId(BaseUser user) {
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            ResultSet resultSet = mysqlDBManager.getInfo(getTable(),user);

            resultSet.next();

            return resultSet.getInt("id");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu(getId)",JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    @Override
    public void delUser() {

    }

    @Override
    public ResultSet getApt(BaseUser user) {
        return null;
    }

    @Override
    public void createApt(int id, String clinic, String doctor, String date) {

    }

    @Override
    public void addEmployee(BaseUser employee, String userRole) {

    }

    @Override
    public void update(String update, String newValue) {
        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        mysqlDBManager.updateInfo(getTable(), this, update, newValue);
    }

    @Override
    public JPanel getPanel() {
        return null;
    }

    @Override
    public String getUsername(BaseUser user) {

        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            ResultSet resultSet = mysqlDBManager.getInfo(getTable(),user);

            resultSet.next();

            return resultSet.getString("fullName");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu(getId)",JOptionPane.ERROR_MESSAGE);
        }

        return "";
    }

}
