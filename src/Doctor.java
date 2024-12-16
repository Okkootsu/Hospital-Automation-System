import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Doktor sınıfı ve yapabileceği işlemler

public class Doctor extends Employee {

    private String userType = "Doktor";

    //Kendi ana sayfa panelini al
    @Override
    public JPanel getPanel(){
        return new MainMenuPanel.MainDoctorPanel(this);
    }

    //Meslek bilgisine eriş
    @Override
    public String getUserType(){
        return userType;
    }

    //Sahip olduğu randevuları getir
    @Override
    public ResultSet getApt(){
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM appointment " +
                    "WHERE pid = "+this.id+" ";

            return statement.executeQuery(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu(getApt)",JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    //Randevuyu silme
    @Override
    public void delApt(int aptID) {
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "DELETE FROM appointment " +
                    "WHERE apt_id = "+aptID;

            statement.execute(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
    }

    //Koyduğu teşhisleri getir
    @Override
    public ResultSet getDiagnoses() {
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM diagnoses " +
                    "WHERE pid = "+this.id;

            return statement.executeQuery(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu(getDiagnoses)",JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    //Teşhis oluşturma
    public void createDiagnose(int aptID, int customerID, String diagnose, String date) {
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "INSERT INTO diagnoses(apt_id, pid, cid, diagnose, apt_date)" +
                    "VALUES("+aptID+", "+this.id+" , "+customerID+" , '"+diagnose+"' , '"+date+"')";

            statement.execute(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
    }

}
