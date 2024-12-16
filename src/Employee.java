import javax.swing.*;
import java.sql.ResultSet;

// Genel Çalışan sınıfı ve sahip olduğu metodlar

public class Employee extends BaseUser{

    //Field'lar
    private String table = "employee";
    private String userType = "Çalışan";

    //Tabloyu al
    @Override
    public String getTable() {
        return table;
    }

    // Id'yi veri tabanından çek
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

    //Kullanıcı adını sistemden al
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

        return ""; //Başarısız olursa boş string döndür
    }

    //Kullanıcı bilgilerini güncelle
    @Override
    public void update(String update, String newValue) {
        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        mysqlDBManager.updateInfo(getTable(), this, update, newValue);
    }

    // Kullanıcı bilgilerini anlık güncelle -> Sayfada dinamik görünmesi için
    @Override
    public void updateInfo() {

        MysqlDBManager mysqlDBManager = new MysqlDBManager();

        try {
            ResultSet resultSet = mysqlDBManager.getInfo(getTable(),this);

            if(resultSet.next()){
                this.username = resultSet.getString("fullName");
                this.password = resultSet.getString("password");
                this.tc = resultSet.getLong("tc");
                this.id = getId(this);
            }

            else {
                JOptionPane.showMessageDialog(null,"Kullanıcı bulunamadı" ,
                        "Uyarı!",JOptionPane.INFORMATION_MESSAGE);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Hata kodu: "+e.getMessage() ,
                    "Bir Hata Oluştu!",JOptionPane.ERROR_MESSAGE);
        }
    }

    //Yetkisiz metod
    @Override
    public void delThisUser() {

    }

    //Yetkisiz metod
    @Override
    public ResultSet getApt() {
        return null;
    }

    //Yetkisiz metod
    @Override
    public void delApt(int aptID) {

    }

    //Yetkisiz metod
    @Override
    public void createApt(int id, String clinic, String doctor, String date) {

    }

    //Yetkisiz metod
    @Override
    public void addEmployee(BaseUser employee, String userRole) {

    }

    //Yetkisiz metod
    @Override
    public JPanel getPanel() {
        return null;
    }

    //Yetkisiz metod
    @Override
    public ResultSet getUsers() {
        return null;
    }

    //Yetkisiz metod
    @Override
    public String getUserType() {
        return userType;
    }

    //Yetkisiz metod
    @Override
    public ResultSet getDiagnoses() {
        return null;
    }

    //Yetkisiz metod
    @Override
    public void createDiagnose(int aptID, int customerID, String diagnose, String date) {

    }

}
