package bcit.comp8082.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddItemActivity extends AppCompatActivity {
    Button confirm;
    Button cancel;
    EditText itemName;
    EditText price;
    ImageView image;

    int SELECT_PICTURE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        confirm = findViewById(R.id.item_confirm_btn);
        cancel = findViewById(R.id.item_cancel_btn);
        itemName = findViewById(R.id.item_name_input);
        price = findViewById(R.id.item_price_input);
        image = findViewById(R.id.itemImage);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }
    public void selectImage() {
        Intent i = new Intent();
        i.setType("image/");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Item Image"), SELECT_PICTURE);
    }

    public void cancel(final View v){
        finish();
    }

    public void confirm(final View v) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();
        Intent intent = new Intent();
        intent.putExtra("ITEMPRICE", Double.parseDouble(price.getText().toString()));
        intent.putExtra("ITEMNAME", itemName.getText().toString());
        intent.putExtra("IMAGE", imageInByte);

        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == SELECT_PICTURE) {
                Uri imageUri = data.getData();
                if(null != imageUri) {
                    image.setImageURI(imageUri);
                }
            }
        }
    }
}