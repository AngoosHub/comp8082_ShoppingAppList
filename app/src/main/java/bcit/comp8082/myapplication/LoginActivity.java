package bcit.comp8082.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bcit.comp8082.myapplication.models.DBHelper;
import bcit.comp8082.myapplication.models.Item;
import bcit.comp8082.myapplication.models.RecyclerItemAdapter;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button signup;
    Button change_password;
    EditText username;
    EditText password;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.button_login);
        signup = findViewById(R.id.button_signup);
        change_password = findViewById(R.id.button_change_password);
        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
        db = new DBHelper(getApplicationContext());

        // Comment to stop deleting database
        //getApplicationContext().deleteDatabase(DBHelper.DATABASE_NAME);

        login();
        signup();
        change_password();
    }


    public void login() {
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username_str = username.getText().toString();
                String password_str = password.getText().toString();

                if (username_str.isEmpty() || password_str.isEmpty()) {
                    buildAlertDialogue("Login Error",
                            "Username and password field cannot be empty.");
                    return;
                }

                Boolean verify = db.verifyUserLogin(username_str, password_str);

                if (verify) {
                    Intent intent = new Intent(getApplicationContext(), AllListsActivity.class);
                    intent.putExtra("username", username_str);
                    intent.putExtra("password", password_str);
                    startActivity(intent);
                }
                else {
                    buildAlertDialogue("Login Error",
                            "Incorrect username and password.");
                }
            }
        });
    }

    public void signup() {
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username_str = username.getText().toString();
                String password_str = password.getText().toString();

                if (username_str.isEmpty() || password_str.isEmpty()) {
                    buildAlertDialogue("Signup Error",
                            "Username and password field cannot be empty.");
                    return;
                }

                if (db.checkUsernameExists(username_str)) {
                    buildAlertDialogue("Signup Error",
                            "Username already exists, please enter a different username.");
                    return;
                }

                Boolean result = db.insertUser(username_str, password_str);

                if (result) {
                    buildAlertDialogue("Signup Success",
                            "User has been successfully created.");
                } else {
                    buildAlertDialogue("Signup Error",
                            "Failed to insert user into database.");
                }

            }
        });
    }

    public void change_password() {
        change_password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username_str = username.getText().toString();
                String password_str = password.getText().toString();

                if (username_str.isEmpty() || password_str.isEmpty()) {
                    buildAlertDialogue("Change password Error",
                            "Username and password field cannot be empty.");
                    return;
                }

                if (!db.checkUsernameExists(username_str)) {
                    buildAlertDialogue("Change password Error",
                            "Username doesn't exists, please enter a valid username.");
                    return;
                }
                Boolean result1 = db.deleteUser(username_str);
                Boolean result2 = db.insertUser(username_str, password_str);

                if (result1 && result2) {
                    buildAlertDialogue("Change password Success",
                            "User password has been successfully changed.");
                } else {
                    buildAlertDialogue("Change password Error",
                            "Failed to change user password in database.");
                }

            }
        });
    }

    public void buildAlertDialogue(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        builder.setMessage(message)
                .setTitle(title);


        // Add the buttons
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
//        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User cancelled the dialog
//            }
//        });
        // Set other dialog properties

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void done(View v){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}