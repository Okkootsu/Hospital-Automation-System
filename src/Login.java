import javax.swing.*;

public class Login implements ILoginDal{
    Login(BaseUser user){
        if(isIncorrect(user)){
            JOptionPane.showMessageDialog(null,"Alanlar boş bırakılamaz \n" +
                    "Lütfen tekrar deneyiniz",
                    "Uyarı",JOptionPane.INFORMATION_MESSAGE);
        }
        else {

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

        return false;
    }
}
