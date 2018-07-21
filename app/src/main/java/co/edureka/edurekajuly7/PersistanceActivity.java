package co.edureka.edurekajuly7;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class PersistanceActivity extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener{

    EditText eTxtData;
    Button btnPersist;

    String data;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    void initViews(){
        eTxtData = findViewById(R.id.editTextData);
        btnPersist = findViewById(R.id.buttonPersist);
        btnPersist.setOnClickListener(this);

        preferences = getSharedPreferences("myPrefs",MODE_PRIVATE);
        preferences.registerOnSharedPreferenceChangeListener(this);

        editor = preferences.edit();

        String name = preferences.getString("keyName","");
        getSupportActionBar().setTitle("Welcome, "+name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistance);
        initViews();
    }

    void writeDataInInternalFile(){
        try{

            // We are not mentioning the Path for the data.txt.
            // Path for Internal Files is Fixed.
            FileOutputStream outputStream = openFileOutput("data.txt", MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();

            Toast.makeText(this,"Data Written in File",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void writeDataInExternalFile(){
        try{

            // Get the path of SD Card !!
            String path = Environment.getExternalStorageDirectory().getPath();

            File file = new File(path,"data.txt");
            FileOutputStream outputStream = new FileOutputStream(file);

            outputStream.write(data.getBytes());
            outputStream.close();

            Toast.makeText(this,"Data Written in File",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readDataFromExternalFile(){
        try{

            // Get the path of SD Card !!
            String path = Environment.getExternalStorageDirectory().getPath();

            File file = new File(path,"data.txt");

            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

            String line = buffer.readLine();

            eTxtData.setText(line);

            buffer.close();
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    void readDataFromInternalFile(){
        try{


            FileInputStream inputStream = openFileInput("data.txt");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

            String line = buffer.readLine();

            eTxtData.setText(line);

            buffer.close();
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {

        data = eTxtData.getText().toString().trim();

        editor.putString("keyData",data);
        editor.putString("keyName","John Watson");
        editor.putInt("keyAge",30);

        //editor.commit(); // Save the data on the same Thread
        editor.apply(); // Save the data Async i.e. in the background

        Toast.makeText(this,"Data Written in SharedPreferences",Toast.LENGTH_LONG).show();

        //writeDataInInternalFile();
        //readDataFromInternalFile();


        //writeDataInExternalFile();

        /*if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            readDataFromExternalFile();
        }else{
            Toast.makeText(this,"SD Card Not Ready",Toast.LENGTH_LONG).show();
        }*/

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
