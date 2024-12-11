import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin extends Employee {

    private String userRole = "Admin";

    @Override
    public void addEmployee(BaseUser employee, String userRole) {

        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "INSERT INTO employee(fullName, tc, password, role)" +
                    "VALUES('"+employee.username+"', "+employee.tc+" , '"+employee.password+"' , '"+userRole+"')";

            statement.execute(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public JPanel getPanel(){
        return new MainMenuPanel.MainAdminPanel(this);
    }

    @Override
    public void delUser() {
        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        mysqlDBManager.deleteUser(getTable(), this.id);
    }

    @Override
    public ResultSet getUsers(){
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM "+getTable()+" ";

            return statement.executeQuery(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu (getInfo)",JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }


    public void delUser(int id) {
        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        mysqlDBManager.deleteUser(getTable(), id);
    }
}
