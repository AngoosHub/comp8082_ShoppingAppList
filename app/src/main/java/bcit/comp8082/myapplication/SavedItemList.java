package bcit.comp8082.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import bcit.comp8082.myapplication.models.DBHelper;
import bcit.comp8082.myapplication.models.Item;
import bcit.comp8082.myapplication.models.ItemsList;
import bcit.comp8082.myapplication.models.RecyclerItemAdapter;

public class SavedItemList extends AppCompatActivity {

    static final int REQUEST_ITEM_ADD = 1;

    Button add;
    Button done;
    RecyclerView recyclerView;
    RecyclerItemAdapter adapter;
    DBHelper db;

    TextView PH;
    ItemsList items;
    ArrayList <Item> item_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_item_list);
        add = findViewById(R.id.add_item);
        recyclerView = findViewById(R.id.items_list);

//        items = new ItemsList("Saved List", new ArrayList<Item>());

        db = new DBHelper(getApplicationContext());
        item_arr = db.getAllItem();
        PH = findViewById(R.id.empty_item_list);

        adapter = new RecyclerItemAdapter(SavedItemList.this, this,  item_arr);
//        Objects.requireNonNull(getSupportActionBar()).hide();
        setUpRecyclerView(adapter);
        updateDisplay();
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
    public void updateDisplay() {
        if (item_arr.size() != 0){
            PH.setText("");
        } else {
            PH.setText("No item");
        }
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
            byte[] imageBytes = data.getByteArrayExtra("IMAGE");
            Log.e("length", String.valueOf(imageBytes.length));

            Item item = new Item(1, itemName, "Desc" ,itemPrice, imageBytes);
            db.insertItem(item);

            item_arr.clear();
            item_arr.addAll(db.getAllItem());

            Log.e("length1", String.valueOf(item_arr.get(0).getItem_img().length));

            updateDisplay();
            recyclerView.scrollToPosition(item_arr.size());
        }
    }

}

