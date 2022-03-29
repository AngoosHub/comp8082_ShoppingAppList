package bcit.comp8082.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SearchListActivity extends AppCompatActivity {
    Button search;
    Button back;
    Button reset;
    private EditText fromDateDisplay;
    private EditText toDateDisplay;
    private static DateFormat dateAsText = new SimpleDateFormat("yyyy-MM-dd");

    private Calendar dateStringToCalendarDate(String date) {
        Date selectedDate = new Date();
        try {
            selectedDate = dateAsText.parse(date);
        } catch (ParseException e) {}
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);
        return calendar;

    }

    private DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            setDate(fromDateDisplay, year, month, day);
        }
    };

    private DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            setDate(toDateDisplay, year, month, day);
        }
    };

    private View.OnClickListener startCalendarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            showCalendar(fromDateDisplay, startDateListener);

        }
    };

    private View.OnFocusChangeListener startCalendarChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
                showCalendar(fromDateDisplay, startDateListener);

            }
        }
    };

    private View.OnClickListener endCalendarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            showCalendar(toDateDisplay, endDateListener);

        }
    };

    private View.OnFocusChangeListener endCalendarChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {

                showCalendar(toDateDisplay,endDateListener);
            }
        }
    };
    private void showCalendar(EditText view, DatePickerDialog.OnDateSetListener dateSetListener) {
        Calendar cal = dateStringToCalendarDate(view.getText().toString());

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                SearchListActivity.this,
                dateSetListener,
                year,month,day);
        dialog.show();
    }

    private void setDate(final EditText editText, int year, int month, int day) {
        month++;
        final String date = String.format(Locale.getDefault(), "%d-%02d-%02d", year, month, day);
        editText.setText(date);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        fromDateDisplay = (EditText) findViewById(R.id.etFromDateTime);
        toDateDisplay = (EditText) findViewById(R.id.etToDateTime);

        fromDateDisplay.setOnClickListener(startCalendarClickListener);
        toDateDisplay.setOnClickListener(endCalendarClickListener);

        fromDateDisplay.setOnFocusChangeListener(startCalendarChangeListener);
        toDateDisplay.setOnFocusChangeListener(endCalendarChangeListener);

        search = findViewById(R.id.searchBtn);
        search.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                search_lists(v);
            }
        });

        back = findViewById(R.id.backBtn);
        back.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                back(v);
            }
        });
        reset = findViewById(R.id.resetBtn);
        reset.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                reset(v);
            }
        });
        try {
            Calendar calendar = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date now = calendar.getTime();
            String todayStr = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(now);
            Date today = format.parse((String) todayStr);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            String tomorrowStr = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());
            Date tomorrow = format.parse((String) tomorrowStr);
            ((EditText) findViewById(R.id.etFromDateTime)).setText(new SimpleDateFormat(
                    "yyyy-MM-dd", Locale.getDefault()).format(today));
            ((EditText) findViewById(R.id.etToDateTime)).setText(new SimpleDateFormat(
                    "yyyy-MM-dd", Locale.getDefault()).format(tomorrow));
        } catch (Exception ex) { }
    }

    public void back(View v) {
        finish();
    }

    public void reset(View v) {
        Intent intent = new Intent();
        intent.putExtra("RESET", true);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void search_lists(View v) {
        Intent intent = new Intent();
        EditText from = (EditText) findViewById(R.id.etFromDateTime);
        EditText to = (EditText) findViewById(R.id.etToDateTime);
        String frDate = from.getText().toString();
        String toDate = to.getText().toString();
        intent.putExtra("RESET", false);
        intent.putExtra("FROMDATE", frDate);
        intent.putExtra("TODATE", toDate);
        setResult(RESULT_OK, intent);
        finish();
    }

}