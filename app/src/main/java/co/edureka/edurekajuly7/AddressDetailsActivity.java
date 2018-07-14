package co.edureka.edurekajuly7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddressDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtAddressLine, eTxtCity;
    Button btnDone;

    void initViews(){
        eTxtAddressLine = findViewById(R.id.editTextAddressLine);
        eTxtCity = findViewById(R.id.editTextCity);
        btnDone = findViewById(R.id.buttonDone);

        btnDone.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_details);
        initViews();
    }

    @Override
    public void onClick(View v) {

        String adrsLine = eTxtAddressLine.getText().toString();
        String city = eTxtCity.getText().toString();

        // Sending the data Back to HomeActivity
        Intent data = new Intent();
        data.putExtra("keyAddress",adrsLine);
        data.putExtra("keyCity",city);

        setResult(201,data); // Sending Back the Data to HomeActivity. setResult shall execute onActivityResult method in HomeActivity
        finish(); // To Destroy this activity so that we can go back to the previous Activity which is expecting data

    }
}
