import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Admin sınıfı ve onun kullanaildiği özellikler

public class Admin extends Employee {

    //Bu sınıf tipinde oluşturulan kullanıcının rolü
    private String userType = "Admin";



    //Bu kullanıcının mesleğini veya kullanıcı tipini al -> Doktor, Admin veya Hasta olabilir
    @Override
    public String getUserType() {
        return userType;
    }

    //Ana uygulama için ekrana getirilecek view/panel
    @Override
    public JPanel getPanel(){
        return new MainMenuPanel.MainAdminPanel(this);
    }

    //Bu kullanıcıyı sil (giriş yapılan kullanıcı)
    @Override
    public void delThisUser() {
        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        mysqlDBManager.deleteUser(getTable(), this.id);
    }

    //Veritabanındaki personelleri almak için fonk.
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
                    "Bir Hata Oluştu (getUsers)",JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    //Veritabanından personel sil
    public void delUser(int id) {
        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        mysqlDBManager.deleteUser(getTable(), id);
    }

    //Veritabanına personel eklemek için fonksiyon
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

}
