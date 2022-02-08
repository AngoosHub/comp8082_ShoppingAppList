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

import bcit.comp8082.myapplication.models.Item;
import bcit.comp8082.myapplication.models.ItemList;
import bcit.comp8082.myapplication.models.RecyclerItemAdapter;

public class SavedItemList extends AppCompatActivity {

    static final int REQUEST_ITEM_ADD = 1;

    Button add;
    Button done;
    TextView itemsText;
    RecyclerView recyclerView;
    RecyclerItemAdapter adapter;

    ItemList items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_item_list);
        add = findViewById(R.id.add_item);
        itemsText = findViewById(R.id.item_list);
        recyclerView = findViewById(R.id.items_list);

        items = new ItemList("Saved List", new ArrayList<Item>());

        adapter = new RecyclerItemAdapter(SavedItemList.this, items.getItems());
        setUpRecyclerView(adapter);
    }

    public void addItem(View v) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, REQUEST_ITEM_ADD);
    }

    public void setUpRecyclerView(RecyclerItemAdapter adapter) {
        Log.d("adapter: ", "adding " + adapter.toString());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void done(View v){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ITEM_ADD && resultCode == RESULT_OK) {
            String itemName = data.getStringExtra("ITEMNAME");
            double itemPrice = data.getDoubleExtra("ITEMPRICE", -1);
            ArrayList<String> tags = new ArrayList<String>();
            Item item = new Item(itemName, itemPrice);

            items.addItem(item);

            recyclerView.scrollToPosition(items.getSize());
        }
    }
}
