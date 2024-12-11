import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer extends BaseUser{

    private String table = "customer";
    private String userType = "Hasta";

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
        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        mysqlDBManager.deleteUser(getTable(), this.id);
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

    @Override
    public void createApt(int id, String clinic, String doctor, String date) {
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "INSERT INTO appointment(cid, clinic, doctor, apt_date)" +
                    "VALUES("+id+", '"+clinic+"' , '"+doctor+"' , '"+date+"')";

            statement.execute(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
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
        return new MainMenuPanel.MainPanel(this);
    }

    @Override
    public ResultSet getApt(BaseUser user) {
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM appointment " +
                    "WHERE cid = "+user.id;

            return statement.executeQuery(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu(getApt)",JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }
}
