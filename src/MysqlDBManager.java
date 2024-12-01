import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlDBManager {

    private final String sqlUrl = "jdbc:mysql://127.0.0.1:3306/hospitaldb";
    private final String sqlUsername = "root";
    private final String sqlPassword = "volkancomp159357258A";

    public String getSqlUrl(){
        return sqlUrl;
    }

    public String getSqlUsername() {
        return sqlUsername;
    }

    public String getSqlPassword() {
        return sqlPassword;
    }


    public void addUser(String table, BaseUser user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(getSqlUrl(),getSqlUsername(),getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "INSERT INTO "+table+"(fullName, tc, password)" +
                    "VALUES('"+user.username+"', "+user.tc+" , '"+user.password+"')";

            statement.execute(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
    }


    public void updateInfo() {

    }


    public void deleteUser() {

    }


    public ResultSet getInfo(String table, BaseUser user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(getSqlUrl(),getSqlUsername(),getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM "+table+" " +
                    "WHERE tc = "+user.tc;

            return statement.executeQuery(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu (getInfo)",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}
