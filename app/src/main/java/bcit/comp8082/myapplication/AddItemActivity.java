package bcit.comp8082.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {
    Button confirm;
    Button cancel;
    EditText itemName;
    EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        confirm = findViewById(R.id.item_confirm_btn);
        cancel = findViewById(R.id.item_cancel_btn);
        itemName = findViewById(R.id.item_name_input);
        price = findViewById(R.id.item_price_input);
    }

    public void cancel(final View v){
        finish();
    }

    public void confirm(final View v) {
        Intent intent = new Intent();
        intent.putExtra("ITEMPRICE", Double.parseDouble(price.getText().toString()));
        intent.putExtra("ITEMNAME", itemName.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}