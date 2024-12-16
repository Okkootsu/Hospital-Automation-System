import javax.swing.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

// Mysql veri tabanına bağlantı sağlayan ana işlemler

public class MysqlDBManager {

    // Bağlantı için gerekli bilgiler
    private  String sqlUrl;
    private  String sqlUsername;
    private  String sqlPassword;
    //

    MysqlDBManager () {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/resources/config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }

        this.sqlUrl = properties.getProperty("db.url");
        this.sqlUsername = properties.getProperty("db.user");
        this.sqlPassword = properties.getProperty("db.password");
    }

    //Bilgilerin alınması
    public String getSqlUrl(){
        return sqlUrl;
    }

    public String getSqlUsername() {
        return sqlUsername;
    }

    public String getSqlPassword() {
        return sqlPassword;
    }
    //

    // Kullanıcı ekleme metodu, table hangi tabloya ekleneceğini belirler
    public void addUser(String table, BaseUser user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(getSqlUrl(),getSqlUsername(),getSqlPassword());

            Statement statement = connection.createStatement();

            // Mysql'de çalışmasını istediğimiz kodu buraya yazıyoruz
            String query = "INSERT INTO "+table+"(fullName, tc, password)" +
                    "VALUES('"+user.username+"', "+user.tc+" , '"+user.password+"')";

            statement.execute(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
    }

    // Kullanıcı bilgilerini güncelle
    public void updateInfo(String table, BaseUser user, String update, String newValue) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(getSqlUrl(),getSqlUsername(),getSqlPassword());

            Statement statement = connection.createStatement();

            // Mysql'de çalışmasını istediğimiz kodu buraya yazıyoruz
            String query = "UPDATE "+table+" " +
                            "SET "+update+" = '"+newValue+"' " +
                            "WHERE id = " + user.id;


            statement.execute(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
    }

    // Kullanıcı sil
    public void deleteUser(String table, int userID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(getSqlUrl(),getSqlUsername(),getSqlPassword());

            Statement statement = connection.createStatement();

            // Mysql'de çalışmasını istediğimiz kodu buraya yazıyoruz
            String query = "DELETE FROM "+table+" " +
                    "WHERE id = "+userID;

            statement.execute(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
    }

    // Kullanıcı bilgilerini al
    public ResultSet getInfo(String table, BaseUser user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(getSqlUrl(),getSqlUsername(),getSqlPassword());

            Statement statement = connection.createStatement();

            // Mysql'de çalışmasını istediğimiz kodu buraya yazıyoruz
            String query = "SELECT * FROM "+table+" " +
                    "WHERE tc = "+user.tc;

            return statement.executeQuery(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu (getInfo)",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    // Kullanıcının ismini al
    public String getUsername(String table, int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(getSqlUrl(),getSqlUsername(),getSqlPassword());

            Statement statement = connection.createStatement();

            // Mysql'de çalışmasını istediğimiz kodu buraya yazıyoruz
            String query = "SELECT fullName FROM "+table+" " +
                    "WHERE id = "+id;

            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();

            return resultSet.getString("fullName");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu (getInfo)",JOptionPane.ERROR_MESSAGE);
        }

        return ""; // işlem başarısız olursa boş string döndür
    }

}
