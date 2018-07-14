package co.edureka.edurekajuly7;

import android.app.NotificationManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyFragmentsActivity extends AppCompatActivity {

    UpperFragment upperFragment;
    LowerFragment lowerFragment;

    void initViews(){

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(101); // Remove the Notification

        upperFragment = new UpperFragment();
        lowerFragment = new LowerFragment();

        // FragmentManager to add/remove/replace the fragments in the FrameLayout

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.upperFrame,upperFragment).commit();
        fragmentManager.beginTransaction().add(R.id.lowerFrame,lowerFragment).commit();

        //fragmentManager.beginTransaction().replace(R.id.lowerFrame,someOtherFragment).commit();
        //fragmentManager.beginTransaction().remove(upperFragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragments);

        initViews();
    }
}
