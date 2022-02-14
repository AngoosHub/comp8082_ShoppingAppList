package bcit.comp8082.myapplication.models;

public class List {

    private int list_id;
    private int user_id;
    private String list_name;
    private String list_desc;
    private int list_datetime;

    public List (int list_id, int user_id, String list_name, String list_desc, int list_datetime) {
        this.list_id = list_id;
        this.user_id = user_id;
        this.list_name = list_name;
        this.list_desc = list_desc;
        this.list_datetime = list_datetime;
    }

    public List() {

    }



    public int getList_id(){
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public int getUser_id(){
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }
    public String getList_desc() {
        return list_desc;
    }

    public void setList_desc(String list_desc) {
        this.list_desc = list_desc;
    }

    public int getList_datetime() {
        return list_datetime;
    }

    public void setList_datetime(int list_datetime) {
        this.list_datetime = list_datetime;
    }
    

}
