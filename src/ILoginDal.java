public interface ILoginDal {

    //Login işleminde gerekli kontrolleri yapmak için
    boolean isIncorrect(BaseUser user);

}
