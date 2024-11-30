public class Employee extends BaseUser{

    private String table = "employee";

    @Override
    public String getTable() {
        return table;
    }
}
