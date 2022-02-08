package bcit.comp8082.myapplication.models;


import java.util.ArrayList;

public class ItemList {
    private String name;
    private ArrayList<Item> items;
    private String LastUpdate;

    public ItemList(String name, ArrayList<Item> items) {
        this.name = name;
        this.items = items;
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public void addItem(Item item){
        this.items.add(item);
    }
    public int getSize(){
        return this.items.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }
}