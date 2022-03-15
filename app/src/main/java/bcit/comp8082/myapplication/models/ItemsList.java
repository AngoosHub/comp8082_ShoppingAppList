package bcit.comp8082.myapplication.models;


import java.util.ArrayList;


public class ItemsList {

    private int items_list_id;
    private int items_list_list_id;
    private int items_list_item_id;
    private int items_list_item_qty;
    private Item item; // used only in RecyclerItemListAdapter to deal with update issue

    public ItemsList (int items_list_id, int items_list_list_id, int items_list_item_id,
                      int items_list_item_qty) {
        this.items_list_id = items_list_id;
        this.items_list_list_id = items_list_list_id;
        this.items_list_item_id = items_list_item_id;
        this.items_list_item_qty = items_list_item_qty;
    }

    public ItemsList() {

    }

    public void addItem (Item item) {
        this.item = item; // used only in RecyclerItemListAdapter to deal with update issue
    }

    public Item getItem () {
        return this.item; // used only in RecyclerItemListAdapter to deal with update issue
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

