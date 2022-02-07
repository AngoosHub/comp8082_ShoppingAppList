package bcit.comp8082.myapplication;


public class Item {

    private int item_id;
    private String item_name;
    private String item_desc;
    private float item_price;
    private String item_img;

    public Item (int item_id, String item_name, String item_desc, float item_price, String item_img) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_desc = item_desc;
        this.item_price = item_price;
        this.item_img = item_img;
    }

    public Item() {

    }



    public int getItem_id(){
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public float getItem_price() {
        return item_price;
    }

    public void setItem_price(float item_price) {
        this.item_price = item_price;
    }

    public String getItem_img() {
        return item_img;
    }

    public void setItem_img(String item_img) {
        this.item_img = item_img;
    }

}

