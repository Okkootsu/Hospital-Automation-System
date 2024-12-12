import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor extends Employee {

    private String userType = "Doktor";

    @Override
    public JPanel getPanel(){
        return new MainMenuPanel.MainDoctorPanel(this);
    }

    @Override
    public String getUserType(){
        return userType;
    }

    @Override
    public ResultSet getApt(){
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM appointment " +
                    "WHERE doctor = '"+this.username+"' ";

            return statement.executeQuery(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu(getApt)",JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }
}
