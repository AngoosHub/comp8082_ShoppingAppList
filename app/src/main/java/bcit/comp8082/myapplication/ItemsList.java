package bcit.comp8082.myapplication;

public class ItemsList {

    private int items_list_id;
    private int items_list_list_id;
    private int items_list_item_id;
    private int items_list_item_qty;
    private float items_list_backup_price;

    public ItemsList (int items_list_id, int items_list_list_id, int items_list_item_id,
                      int items_list_item_qty, float items_list_backup_price) {
        this.items_list_id = items_list_id;
        this.items_list_list_id = items_list_list_id;
        this.items_list_item_id = items_list_item_id;
        this.items_list_item_qty = items_list_item_qty;
        this.items_list_backup_price = items_list_backup_price;
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

    public float getItems_list_backup_price() {
        return items_list_backup_price;
    }

    public void setItems_list_backup_price(float items_list_backup_price) {
        this.items_list_backup_price = items_list_backup_price;
    }

}
