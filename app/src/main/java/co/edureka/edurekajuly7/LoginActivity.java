package co.edureka.edurekajuly7;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtEmail, eTxtPassword;
    Button btnLogin;

    void initViews(){
        eTxtEmail = findViewById(R.id.editTextEmail);
        eTxtPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    void showNotification(){

        //1. Create a NotificationManager which will notify the Notification
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //2. Check for the Version. If Version is Greater than or equal to Oreo, associate NotificationChannel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("myChannelId","MyChannel",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //3. Use NotificationCompat.Builder to build Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("This is Title");
        builder.setContentText("This is Text");
        builder.setSmallIcon(R.drawable.folder);

        // VIBRATE Permissions required in Manifest
        builder.setDefaults(Notification.DEFAULT_ALL); // Vibrate, LED's and Sound

        builder.setChannelId("myChannelId");

        Intent intent = new Intent(LoginActivity.this,MyFragmentsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        builder.setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle("This is Big Title"));
        builder.addAction(android.R.drawable.ic_menu_add,"Add",pendingIntent);
        builder.addAction(android.R.drawable.ic_menu_delete,"Delete",pendingIntent);

        //4. Build the Notification
        Notification notification = builder.build();

        //5. Show the Notification
        notificationManager.notify(101,notification);
    }

    @Override
    public void onClick(View v) {

        String email = eTxtEmail.getText().toString();
        String password = eTxtPassword.getText().toString();

        if(email.equals("admin@example.com") && password.equals("password123")){

            // Explicit Intent
            //Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            //startActivity(intent);

            // Implicit Intent
            //Intent intent = new Intent("co.edureka.edurekajuly7.homeactivity");
            //startActivity(intent);

            // Forward Passing - Pass the data from Activity1 to Activity2
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);

            // Put the data directly in Intent
            /*intent.putExtra("keyName","John Watson");
            intent.putExtra("keyEmail",email);
            intent.putExtra("keyAge",30);*/

            // Put the data in Bundle
            Bundle bundle = new Bundle();
            bundle.putString("keyName","John Watson");
            bundle.putString("keyEmail",email);
            bundle.putInt("keyAge",30);

            intent.putExtra("keyBundle",bundle); // Level of Encapsulation

            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);


        }else{
            Toast.makeText(this,"Please Enter Valid Details",Toast.LENGTH_LONG).show();

            showNotification();

        }

    }


    // If my Activity goes in the background use this method to save data temporarily
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i("LoginActivity","==onSaveInstanceState==");

        //outState.putString("keyName","John Watosn");
        //...
        //...
    }

    // When Activity Object is removed from memory by the System
    // We can recover the data in onRestoreInstanceState which we saved in onSaveInstanceState in the bundle
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.i("LoginActivity","==onRestoreInstanceState==");

        //String name = savedInstanceState.getString("keyName");
        //..
        //...

    }


    // To Create Menu in Android Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Explicit Menu Creation
        /*menu.add(1,101,1,"All Songs");
        menu.add(1,201,1,"Favourites");
        menu.add(2,301,1,"Artists");
        menu.add(2,401,1,"Recently Played");*/
        //....

        // Implicit Menu Creation
        getMenuInflater().inflate(R.menu.menu_login,menu);

        return super.onCreateOptionsMenu(menu);
    }


    // To Handle MenuItem Clicks in Android Activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case 101:
                Intent intent = new Intent(LoginActivity.this,MyFragmentsActivity.class);
                startActivity(intent);
                break;

            case 201:

                break;

            case 301:

                break;

            case R.id.bbc:
                Intent intent1 = new Intent(LoginActivity.this,MyFragmentsActivity.class);
                startActivity(intent1);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
