package co.edureka.edurekajuly7;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ActivityTwo extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{

    AutoCompleteTextView autoTxt;
    ArrayAdapter<String> adapter;


    TextView txtDateTime;
    Button btn,btnDateTime;

    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    TimePickerDialog timePickerDialog;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;

    MyBroadcastReceiver myBroadcastReceiver;
    YourBroadcastReceiver yourBroadcastReceiver;


    void showTimePickerDialog(){

        onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                txtDateTime.setText(hourOfDay+" : "+minute);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hh,mm,true);
        timePickerDialog.show();

    }

    void showDatePickerDialog(){

        // Anonymous Class
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtDateTime.setText(year+"/"+(month+1)+"/"+dayOfMonth);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int mm = calendar.get(Calendar.MONTH);
        int yy = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(this,onDateSetListener,yy,mm,dd);
        datePickerDialog.show(); // To Display the Dialog

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        autoTxt = findViewById(R.id.autoCompleteTextView);
        btn = findViewById(R.id.buttonSubmit);

        txtDateTime = findViewById(R.id.textViewDateTime);
        btnDateTime = findViewById(R.id.buttonGetDT);

        // We Can Customize ArrayAdapter
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter.add("HandBags"); // 0th index
        adapter.add("Handkerchiefs");
        adapter.add("Wallet");
        adapter.add("WallClock");
        adapter.add("Belt");
        adapter.add("Shoes");
        adapter.add("Shiner");  // 1st index
        //...

        // Set ArrayAdapter on ACTV
        autoTxt.setAdapter(adapter);
        autoTxt.setOnItemSelectedListener(this);

        btn.setOnClickListener(this);
        btnDateTime.setOnClickListener(this);

        registerMyReceiver();
        registerYourReceiver();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = adapter.getItem(position);
        Log.i("AutoComplete","Item Selected: "+item);
        Toast.makeText(this,"You Selected "+item,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.buttonSubmit) {
            String item = autoTxt.getText().toString();
            Log.i("AutoComplete", "Item Selected: " + item);
            Toast.makeText(this, "You Selected " + item, Toast.LENGTH_LONG).show();
        }else{
            //showDatePickerDialog();
            //showTimePickerDialog();

            // For User Defined Actions, we have to fire the event ourselves
            Intent intent = new Intent("a.b.c.d");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        }

    }

    void registerMyReceiver(){

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);   // Installation of a new App
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED); // Un-Installation of a new App
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED); // Updation of a new App
        filter.addAction(Intent.ACTION_BATTERY_LOW);     // Battery
        filter.addDataScheme("package"); // We are looking for the package name when we shall receive the event

        myBroadcastReceiver = new MyBroadcastReceiver();

        registerReceiver(myBroadcastReceiver,filter);
    }


    // Class Created within Activity itself
    class MyBroadcastReceiver extends BroadcastReceiver{

        // BroadcastReceiver has only 1 LifeCycle method -> onReceive
        // onReceive will be executed when any even is fired by System for which we have registered
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            String packageName = intent.getData().getSchemeSpecificPart();

            if(action.equals(Intent.ACTION_PACKAGE_ADDED)){
                Log.i("MyBroadcastReceiver","Package Added: "+packageName);
            }

            if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){
                Log.i("MyBroadcastReceiver","Package Removed: "+packageName);
            }

            //...

            if(action.equals(Intent.ACTION_BATTERY_LOW)){
                Log.i("MyBroadcastReceiver","Battery LOW");
            }

        }
    }

    public void registerYourReceiver(){

        // Custom Intent Filter. Its User-Defined !!
        IntentFilter filter = new IntentFilter();
        filter.addAction("a.b.c.d");
        filter.addAction("this.is.awesome");

        yourBroadcastReceiver = new YourBroadcastReceiver();

        LocalBroadcastManager.getInstance(this).registerReceiver(yourBroadcastReceiver,filter);
    }

    // Class Created within Activity itself
    class YourBroadcastReceiver extends BroadcastReceiver {

        // BroadcastReceiver has only 1 LifeCycle method -> onReceive
        // onReceive will be executed when any even is fired by System for which we have registered
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

                if (action.equals("a.b.c.d")) {
                    Log.i("MyBroadcastReceiver", "Action a.b.c.d received");
                }

                if(action.equals("this.is.awesome")){
                    Log.i("MyBroadcastReceiver","Action this.is.awesome received");
                }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // No More Listening to the Events
        unregisterReceiver(myBroadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(yourBroadcastReceiver);
    }
}
