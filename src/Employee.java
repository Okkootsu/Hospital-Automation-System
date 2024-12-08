public class Employee extends BaseUser{

    private String table = "employee";

    @Override
    public String getTable() {
        return table;
    }

    @Override
    public int getId(BaseUser user) {
        return -1;
    }

    @Override
    public void delUser() {

    }
}
