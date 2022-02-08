package bcit.comp8082.myapplication.models;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


public class Item {

    private int item_id;
    private String item_name;
    private String item_desc;
    private double item_price;
    private String item_img;

    public Item (int item_id, String item_name, String item_desc, double item_price, String item_img) {
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

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public String getItem_img() {
        return item_img;
    }

    public void setItem_img(String item_img) {
        this.item_img = item_img;
    }

}


//public class Item {
//    private String name;
//    private double price;
//    private Bitmap image;
//    private String lastPurchase;
//
//    public Item(String name, double price) {
//        this.name = name;
//        this.price = price;
//    }
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public Bitmap getImage() {
//        return image;
//    }
//
//    public void setImage(Bitmap image) {
//        this.image = image;
//    }
//
//    public String getLastPurchase() {
//        return lastPurchase;
//    }
//
//    public void setLastPurchase(String lastPurchase) {
//        this.lastPurchase = lastPurchase;
//    }
//}

