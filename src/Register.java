import javax.swing.*;
import java.sql.ResultSet;

// Kayıt olma kontrollerinin yapıldığı class

public class Register implements ILoginDal {

    MysqlDBManager mysqlDBManager;

    Register(BaseUser user){ // Hatalı bilgi olduğunda işlemi kabul etme
        if(isIncorrect(user)){
            JOptionPane.showMessageDialog(null,"Girilen bilgiler hatalı \n " +
                            "Lütfen tekrar deneyiniz",
                    "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
        }
        else { // Bilgiler doğruysa kullanıcı bilgilerini sisteme kaydet
            mysqlDBManager = new MysqlDBManager();

            mysqlDBManager.addUser(user.getTable(),user);

            JOptionPane.showMessageDialog(null,"Kullanıcı sisteme eklendi",
                    "Kullanıcı Oluşturuldu",JOptionPane.PLAIN_MESSAGE);
        }
    }

    // Kayıt olma için kontroller
    @Override
    public boolean isIncorrect(BaseUser user) {

        if(user.username.isEmpty()){
            return true;
        }

        if(user.password.isEmpty()){
            return true;
        }

        if(String.valueOf(Math.abs(user.tc)).length() != 11){
            return true;
        }

        if(user.username.length() > 45){
            return true;
        }

        if(user.password.length() > 45){
            return true;
        }

        //Tc kontrol
        try {
            MysqlDBManager mysqlDBManager = new MysqlDBManager();
            ResultSet resultSet = mysqlDBManager.getInfo(user.getTable(), user);

            if(resultSet.next()){
                if(resultSet.getLong("tc") == user.tc){

                    resultSet.close();

                    return true;
                }
            }

            resultSet.close();

        }catch (Exception e){

            return true;
        }

        return false;
    }
}
