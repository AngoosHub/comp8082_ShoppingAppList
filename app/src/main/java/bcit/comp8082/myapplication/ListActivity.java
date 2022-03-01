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
import bcit.comp8082.myapplication.models.RecyclerItemAdapter;

public class ListActivity extends AppCompatActivity {

    static final int REQUEST_ITEM_ADD = 1;

    Button add;
    Button done;
    RecyclerView recyclerView;
    RecyclerItemAdapter adapter;
    DBHelper db;

    int list_id;
    Intent intent;

    TextView title;

    ArrayList<Item> item_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_item_list);
        add = findViewById(R.id.add_item);
        recyclerView = findViewById(R.id.items_list);
        title = findViewById(R.id.title_text);

        db = new DBHelper(getApplicationContext());
        item_arr = db.getAllItem();
        intent = getIntent();
        list_id = intent.getIntExtra("LIST_ID", -1);
        Log.e("list-id", String.valueOf(list_id));
        Log.e("list", db.getItemsList(list_id).toString());

        title.setText(intent.getStringExtra("LIST_NAME"));

        adapter = new RecyclerItemAdapter(ListActivity.this, this, item_arr);

        setUpRecyclerView(adapter);

    }
    public void setUpRecyclerView(RecyclerItemAdapter adapter) {
        Log.d("adapter: ", "adding " + adapter.toString());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void addItem(View v) {
        Intent intent = new Intent(this, SavedItemList.class);
        startActivityForResult(intent, REQUEST_ITEM_ADD);
    }
    public void done(View v) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ITEM_ADD && resultCode == RESULT_OK) {
            int item_id = data.getIntExtra("ITEM_ID", -1);
            Item i = db.getAllItem().get(item_id - 1);

            item_arr.add(i);
            recyclerView.scrollToPosition(item_arr.size());
        }
    }
}
