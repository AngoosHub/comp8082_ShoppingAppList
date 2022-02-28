package bcit.comp8082.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import bcit.comp8082.myapplication.models.DBHelper;
import bcit.comp8082.myapplication.models.List;
import bcit.comp8082.myapplication.models.RecyclerListAdapter;


public class AllListsActivity extends AppCompatActivity {

    static final int REQUEST_LIST_ADD = 1;

    Button add;
    Button done;
    RecyclerView recyclerView;
    RecyclerListAdapter adapter;
    DBHelper db;
    String username;
    String password;

    ArrayList<List> list_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lists);
        recyclerView = findViewById(R.id.lists_recyclerview);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        add = findViewById(R.id.add_list);
        add.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                addList(v);
            }
        });

        done = findViewById(R.id.list_done);
        done.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                done(v);
            }
        });

        db = new DBHelper(getApplicationContext());
        list_arr = db.getAllList(username);

        adapter = new RecyclerListAdapter(AllListsActivity.this, list_arr, username, password);

        setUpRecyclerView(adapter);
    }

    public void addList(View v) {
        Intent intent = new Intent(this, AddListActivity.class);
        startActivityForResult(intent, REQUEST_LIST_ADD);
    }

    public void setUpRecyclerView(RecyclerListAdapter adapter) {
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

        if (requestCode == REQUEST_LIST_ADD && resultCode == RESULT_OK) {
            String listName = data.getStringExtra("list_name");
            String listDesc = data.getStringExtra("list_desc");

            List list = new List(1, username, listName, listDesc);
            db.insertList(list);

            list_arr.clear();
            ArrayList<List> db_arr = db.getAllList(username);
            list_arr.addAll(db_arr);
            int arr_size = list_arr.size();
            recyclerView.scrollToPosition(arr_size);
        }
    }

}

