import java.sql.ResultSet;

public interface IAppointmentQueries {

    void createApt(int id, String clinic, String doctor, String date);
    ResultSet getApt(BaseUser user);
}
