package bcit.comp8082.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddListActivity extends AppCompatActivity {
    Button confirm;
    Button cancel;
    EditText listName;
    EditText desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        confirm = findViewById(R.id.list_confirm_btn);
        cancel = findViewById(R.id.list_cancel_btn);
        listName = findViewById(R.id.list_name_input);
        desc = findViewById(R.id.list_desc_input);
    }

    public void cancel(final View v){
        finish();
    }

    public void confirm(final View v) {
        Intent intent = new Intent();
        intent.putExtra("list_name", listName.getText().toString());
        intent.putExtra("list_desc", desc.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}