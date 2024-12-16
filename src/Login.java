import javax.swing.*;
import java.sql.ResultSet;

public class Login implements ILoginDal{

    // İşlem bitince haber ver
    public boolean isCompleted(BaseUser user){

        if(isIncorrect(user)){
            JOptionPane.showMessageDialog(null,"Girilen bilgiler hatalı \n" +
                            "Lütfen tekrar deneyiniz",
                    "Uyarı",JOptionPane.INFORMATION_MESSAGE);

            return false;
        }
        else {
            user.id = user.getId(user); //Giriş yapan kullanıcının bilgilerini ekle
            user.username = user.getUsername(user);
            return true;
        }

    }

    // Giriş için kontroller
    @Override
    public boolean isIncorrect(BaseUser user) {


        if(user.password.isEmpty()){
            return true;
        }

        if(String.valueOf(Math.abs(user.tc)).length() != 11){
            return true;
        }

        if(user.password.length() > 45){
            return true;
        }

        try{    // Veri tabanı kontrolleri
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            ResultSet resultSet = mysqlDBManager.getInfo(user.getTable(), user);

            if(resultSet.next()){

                if(resultSet.getLong("tc") != user.tc){
                    resultSet.close();
                    return true;
                }

                if( !(resultSet.getString("password").equals(user.password)) ){
                    resultSet.close();
                    return true;
                }

                if(user instanceof Employee){ // Çalışan kontrolü
                    if( !(resultSet.getString("role").equals(user.getUserType())) ){ //uygun meslekte mi?
                        return true;
                    }
                }
            }
            else {
                resultSet.close();
                return true;
            }

        }catch (Exception e){
            return true;
        }

        return false;
    }
}
