package co.edureka.edurekajuly7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    TextView txtTitle,txtProfile;

    StringBuffer buffer = new StringBuffer();

    void initViews(){
        txtTitle = findViewById(R.id.textViewTitle);
        txtProfile = findViewById(R.id.textViewProfile);

        String today = new Date().toString();

        txtTitle.setText("Welcome\nIts: "+today);

        // Receive the Intent from the Activity which executed this Activity
        Intent rcv = getIntent();
        /*String name = rcv.getStringExtra("keyName");
        String email = rcv.getStringExtra("keyEmail");
        int age = rcv.getIntExtra("keyAge",0);*/

        Bundle rcvBun = rcv.getBundleExtra("keyBundle");

        String name = rcvBun.getString("keyName");
        String email = rcvBun.getString("keyEmail");
        int age = rcvBun.getInt("keyAge",0);

        buffer.append(name+"\n"+email+"\n"+age);

        txtProfile.setText(buffer.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
    }

    public void clickHandler(View view){


        // Backward Passing : Passing the data from Activity2 into Activity1
        Intent intent = new Intent(HomeActivity.this,AddressDetailsActivity.class);

        // We are starting AddressDetailsActivity and will expect data back from it over here
        startActivityForResult(intent,101);

    }


    // Will be executed when AddressDetailsActivity will execute setResult method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101 && resultCode == 201){
            String adrsLine = data.getStringExtra("keyAddress");
            String city = data.getStringExtra("keyCity");

            buffer.append("\n\nAddress:"+adrsLine+"\nCity: "+city);

            txtProfile.setText(buffer.toString());
        }
    }
}
