package bcit.comp8082.myapplication.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class List {

    private int list_id;
    private String username;
    private String list_name;
    private String list_desc;
    private int list_datetime;

    /** User method for creating new list.
     */
    public List (int list_id, String username, String list_name, String list_desc) {
        this.list_id = list_id;
        this.username = username;
        this.list_name = list_name;
        this.list_desc = list_desc;
        this.list_datetime = (int) (new Date().getTime()/1000);;
    }

    /** Datebase method for creating list for recycler view.
     */
    public List (int list_id, String username, String list_name, String list_desc, int list_datetime) {
        this.list_id = list_id;
        this.username = username;
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

    public String getUsername(){
        return username;
    }

    public void setusername(String username) {
        this.username = username;
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
    
    public String getList_datetime_as_string() {
        String dateAsText = new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date(((long)list_datetime)*1000L));
        return dateAsText;
    }
}
