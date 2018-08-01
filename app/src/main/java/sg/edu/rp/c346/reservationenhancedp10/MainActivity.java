package sg.edu.rp.c346.reservationenhancedp10;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnReset, btnConfirm;
    EditText etPax, etName, etDate, etTime, etPhone;
    CheckBox cbSmoking;

    String name = "";
    String date = "";
    String time = "";
    String pax = "";
    String phone = "";
    int aYear = 0;
    int aDay = 0;
    int aMonth = 0;
    int aHour = 0;
    int aMinute = 0;
    Calendar calander = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPhone = findViewById(R.id.userPhone);
        btnConfirm = findViewById(R.id.buttonConfirm);
        btnReset = findViewById(R.id.buttonReset);
        etDate = findViewById(R.id.editTextDay);
        etTime = findViewById(R.id.editTextTime);
        etPax = findViewById(R.id.groupSize);
        etName = findViewById(R.id.userName);
        cbSmoking = findViewById(R.id.checkBoxSmoking);
        aDay = calander.get(Calendar.DAY_OF_MONTH);
        aMonth = calander.get(Calendar.MONTH);
        aYear = calander.get(Calendar.YEAR);
        aHour = calander.get(Calendar.HOUR);
        aMinute = calander.get(Calendar.MINUTE);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText("Date: " + dayOfMonth +"/"+(month+1)+"/"+year);
                        aYear = year;
                        aMonth = month;
                        aDay = dayOfMonth;
                    }
                };

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, aYear, aMonth, aDay);
                myDateDialog.show();
            }
        });


        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String currMin = "";
                        if (minute < 10){
                            currMin += "0"+minute;
                        } else {
                            currMin += minute+"";
                        }
                        etTime.setText("Time: "+ hourOfDay+ ":" + currMin);
                        aHour = hourOfDay;
                        aMinute = minute;
                    }
                };
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, aHour, aMinute, true);
                myTimeDialog.show();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smoking = "";
                name = etName.getText().toString();
                date = etDate.getText().toString();
                time = etTime.getText().toString();
                pax = etPax.getText().toString();
                if (name.length() != 0 || date.length() != 0 || time.length() != 0 || pax.length() != 0){
                    if (cbSmoking.isChecked()){
                        smoking += "Yes";
                    } else {
                        smoking += "No";
                    }
                    String output = String.format("New Registration\nName: %s\nSmoking: %s\nSize: %s\nDate: %s\nTime: %s"
                            ,name, smoking, pax , date, time);
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                    myBuilder.setTitle("Confirm Your Order");
                    myBuilder.setMessage(output);
                    myBuilder.setCancelable(false);
                    myBuilder.setPositiveButton("Confirm",null);

                    myBuilder.setNeutralButton("Cancel", null);
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();
                } else {
                    Toast.makeText(MainActivity.this, "Please ensure all info are filled up!!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDate.setText(null);
                etTime.setText(null);
                etName.setText(null);
                etPax.setText(null);
                etPhone.setText(null);
                cbSmoking.setChecked(false);
                aDay = calander.get(Calendar.DAY_OF_MONTH);
                aMonth = calander.get(Calendar.MONTH);
                aYear = calander.get(Calendar.YEAR);
                aHour = calander.get(Calendar.HOUR);
                aMinute = calander.get(Calendar.MINUTE);
            }
        });
    }
}
