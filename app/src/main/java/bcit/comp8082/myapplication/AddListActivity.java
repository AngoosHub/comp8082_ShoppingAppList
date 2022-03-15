package bcit.comp8082.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddListActivity extends AppCompatActivity {
    Button confirm;
    Button cancel;
    EditText listName;
    EditText desc;

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    TextView tvDate;
    final Calendar myCalendar = Calendar. getInstance () ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        confirm = findViewById(R.id.list_confirm_btn);
        cancel = findViewById(R.id.list_cancel_btn);
        listName = findViewById(R.id.list_name_input);
        desc = findViewById(R.id.list_desc_input);
        tvDate = findViewById(R.id.list_date_result);
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

    private void scheduleNotification (Notification notification , long delay) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher. class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager. RTC_WAKEUP , delay , pendingIntent);
    }
    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle("It's time for grocery shopping!") ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet (DatePicker view , int year , int monthOfYear , int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year) ;
            myCalendar.set(Calendar.MONTH, monthOfYear) ;
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth) ;
//            updateLabel() ;
        }
    } ;
    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            updateLabel();
        }
    } ;
    public void setDate (View view) {
        new DatePickerDialog(
                AddListActivity. this, date ,
                myCalendar.get(Calendar.YEAR) ,
                myCalendar.get(Calendar.MONTH) ,
                myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    public void setTime (View view) {
        new TimePickerDialog(
                AddListActivity.this,
                time,
                myCalendar.get(Calendar.HOUR_OF_DAY),
                myCalendar.get(Calendar.MINUTE),
                true
        ).show();
    }
    private void updateLabel () {
        String myFormat = "dd/MM/yy" ;
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat , Locale. getDefault ()) ;
        Date date = myCalendar.getTime() ;
        Toast.makeText(this, "Notification Set!", Toast.LENGTH_SHORT).show();
        tvDate.setText(sdf.format(date)) ;
        scheduleNotification(getNotification( tvDate.getText().toString()) , date.getTime()) ;
    }
}