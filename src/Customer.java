import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Hasta ve onun gerçekleştirebildiği işlemler

public class Customer extends BaseUser{

    //Customer field'ları
    private String table = "customer";
    private String userType = "Hasta";

    //Veritabanı için hangi tabloya yazılacağı bilgisini buradan alıyor
    @Override
    public String getTable() {
        return table;
    }

    //Kullanıcının rolünü al
    @Override
    public String getUserType() {
        return userType;
    }

    //Kullanıcı id'sini doğrudan veri tabanından al
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

    //Kullanıcı ismini doğrudan veri tabanından al
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

    //Bu kullanıcıyı sil
    @Override
    public void delThisUser() {
        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        mysqlDBManager.deleteUser(getTable(), this.id);
    }

    //Kullanıcı bilgilerini günceller
    @Override
    public void update(String update, String newValue) {
        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        mysqlDBManager.updateInfo(getTable(), this, update, newValue);
    }

    //Ana sayfa'da gösterilecek paneli al (Kendi panelini göster)
    @Override
    public JPanel getPanel() {
        return new MainMenuPanel.MainPanel(this);
    }

    //Randevu oluştur veri tabanına bu bilgileri yükle
    @Override
    public void createApt(int id, String doctor, String date) {
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            String query = "INSERT INTO appointment(cid, doctor, apt_date)" +
                    "VALUES("+id+" , '"+doctor+"' , '"+date+"')";

            statement.execute(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
    }

    //Varolan randevuları sistemden al
    @Override
    public ResultSet getApt() {
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            //Mysql'de kullanılacak kod parçası
            String query = "SELECT * FROM appointment " +
                    "WHERE cid = "+this.id;

            return statement.executeQuery(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu(getApt)",JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    //Randevu sil
    @Override
    public void delApt(int aptID) {
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            //Mysql'de kullanılacak kod parçası
            String query = "DELETE FROM appointment " +
                    "WHERE apt_id = "+aptID;

            statement.execute(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
    }

    //Bu kullanıcı için konulan teşhisleri al
    @Override
    public ResultSet getDiagnoses() {
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                    mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

            Statement statement = connection.createStatement();

            //Mysql'de kullanılacak kod parçası
            String query = "SELECT * FROM diagnoses " +
                    "WHERE cid = "+this.id;

            return statement.executeQuery(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                    "Bir Hata Oluştu(getDiagnoses)",JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    //Yetkisiz metod
    @Override
    public void addEmployee(BaseUser employee, String userRole) {

    }

    //Yetkisiz metod
    @Override
    public void addToClinic(BaseUser employee, String clinic) {

    }

    //Yetkisiz metod
    @Override
    public void updateInfo() {

    }

    //Yetkisiz metod
    @Override
    public ResultSet getUsers() {
        return null;
    }

    //Yetkisiz metod
    @Override
    public void createDiagnose(int aptID, int customerID, String diagnose, String date) {

    }

}
