import javax.swing.*;
import java.sql.ResultSet;

public class Login implements ILoginDal{

    public boolean isCompleted(BaseUser user){

        if(isIncorrect(user)){
            JOptionPane.showMessageDialog(null,"Girilen bilgiler hatalı \n" +
                            "Lütfen tekrar deneyiniz",
                    "Uyarı",JOptionPane.INFORMATION_MESSAGE);

            return false;
        }
        else {
            return true;
        }

    }

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

        try{
            MysqlDBManager mysqlDBManager = new MysqlDBManager();

            ResultSet resultSet = mysqlDBManager.getInfo(user.getTable(), user);

            if(resultSet.next()){

                if(resultSet.getLong("tc") != user.tc){
                    resultSet.close();
                    return true;
                }

                if( !(resultSet.getString("fullName").equals(user.username)) ){
                    resultSet.close();
                    return true;
                }

                if( !(resultSet.getString("password").equals(user.password)) ){
                    resultSet.close();
                    return true;
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
