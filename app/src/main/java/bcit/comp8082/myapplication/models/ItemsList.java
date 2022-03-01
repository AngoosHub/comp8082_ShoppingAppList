package bcit.comp8082.myapplication.models;


import java.util.ArrayList;


public class ItemsList {

    private int items_list_id;
    private int items_list_list_id;
    private int items_list_item_id;
    private int items_list_item_qty;

    public ItemsList (int items_list_id, int items_list_list_id, int items_list_item_id,
                      int items_list_item_qty) {
        this.items_list_id = items_list_id;
        this.items_list_list_id = items_list_list_id;
        this.items_list_item_id = items_list_item_id;
        this.items_list_item_qty = items_list_item_qty;
    }

    public ItemsList() {

    }



    public int getItems_list_id(){
        return items_list_id;
    }

    public void setItems_list_id(int items_list_id) {
        this.items_list_id = items_list_id;
    }

    public int getItems_list_list_id() {
        return items_list_list_id;
    }

    public void setItems_list_list_id(int items_list_list_id) {
        this.items_list_list_id = items_list_list_id;
    }
    public int getItems_list_item_id() {
        return items_list_item_id;
    }

    public void setItems_list_item_id(int items_list_item_id) {
        this.items_list_item_id = items_list_item_id;
    }

    public int getItems_list_item_qty() {
        return items_list_item_qty;
    }

    public void setItems_list_item_qty(int items_list_item_qty) {
        this.items_list_item_qty = items_list_item_qty;
    }

}


//
//public class ItemList {
//    private String name;
//    private ArrayList<Item> items;
//    private String LastUpdate;
//
//    public ItemList(String name, ArrayList<Item> items) {
//        this.name = name;
//        this.items = items;
//    }
//    public ArrayList<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(ArrayList<Item> items) {
//        this.items = items;
//    }
//    public void addItem(Item item){
//        this.items.add(item);
//    }
//    public int getSize(){
//        return this.items.size();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLastUpdate() {
//        return LastUpdate;
//    }
//
//    public void setLastUpdate(String lastUpdate) {
//        LastUpdate = lastUpdate;
//    }
//}