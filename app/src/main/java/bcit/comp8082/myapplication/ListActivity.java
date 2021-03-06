package bcit.comp8082.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import bcit.comp8082.myapplication.models.DBHelper;
import bcit.comp8082.myapplication.models.Item;
import bcit.comp8082.myapplication.models.ItemsList;
import bcit.comp8082.myapplication.models.List;
import bcit.comp8082.myapplication.models.RecyclerItemAdapter;
import bcit.comp8082.myapplication.models.RecyclerItemListAdapter;

public class ListActivity extends AppCompatActivity {

    static final int REQUEST_ITEM_ADD = 1;

    Button add;
    Button done;
    RecyclerView recyclerView;
    RecyclerItemListAdapter adapter;
    DBHelper db;

    int list_id;
    Intent intent;

    TextView title;
    TextView PH;

    ArrayList<ItemsList> itemslist_list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_item_list);
        add = findViewById(R.id.add_item);
        recyclerView = findViewById(R.id.items_list);
        PH = findViewById(R.id.empty_item_list);
        title = findViewById(R.id.title_text);
        itemslist_list = new ArrayList<>();
        db = new DBHelper(getApplicationContext());

        intent = getIntent();
        list_id = intent.getIntExtra("LIST_ID", -1);
        Log.e("list-id", String.valueOf(list_id));
        Log.e("list", db.getItemsList(list_id).toString());

        title.setText(intent.getStringExtra("LIST_NAME"));
        retrieve_items_list();

        adapter = new RecyclerItemListAdapter(ListActivity.this, this, itemslist_list, list_id);

        setUpRecyclerView(adapter);

        updateDisplay();
    }

    private void retrieve_items_list() {
        ArrayList<ItemsList> temp_list = db.getItemsList(list_id);
        itemslist_list.clear();
        for (ItemsList itemslist : temp_list) {
            int item_id = itemslist.getItems_list_item_id();
            Item item = db.getItem(item_id);
            if (item != null) {
                itemslist.addItem(item);
                itemslist_list.add(itemslist);
            }
        }
    }

    public void setUpRecyclerView(RecyclerItemListAdapter adapter) {
        Log.d("adapter: ", "adding " + adapter.toString());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void addItem(View v) {
        Intent intent = new Intent(this, SavedItemList.class);
        startActivityForResult(intent, REQUEST_ITEM_ADD);
    }
    public void updateDisplay(){
        if (itemslist_list.size() != 0){
            int total = 0;
            for (ItemsList itemslist : itemslist_list) {
                total += (itemslist.getItem().getItem_price() * itemslist.getItems_list_item_qty());
            }
            PH.setText("Total Price: $" + total);
        } else {
            PH.setText("No item");
        }
    }
    public void done(View v) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ITEM_ADD && resultCode == RESULT_OK) {
            int new_item_id = data.getIntExtra("ITEM_ID", -1);
            ItemsList item_list = itemslist_list.stream().filter(list ->
                    list.getItems_list_item_id() == new_item_id).findFirst().orElse(null);

            if (item_list != null) {
                item_list.setItems_list_item_qty(item_list.getItems_list_item_qty() + 1);
                db.updateItemsList(item_list);
            }
            else {
                ItemsList itemsList = new ItemsList(0, list_id , new_item_id, 1);
                db.insertItemsList(itemsList);
            }
            retrieve_items_list();
            updateDisplay();
            int num = itemslist_list.indexOf(item_list);
            int idx = 0;
            for (ItemsList temp_item : itemslist_list) {
                if (temp_item.getItems_list_item_id() == new_item_id) {
                    adapter.notifyItemChanged(idx);
                    break;
                }
                idx++;
            }
            recyclerView.scrollToPosition(itemslist_list.size());
        }
    }
}
