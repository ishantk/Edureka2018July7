package co.edureka.edurekajuly7;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class MyLocationActivity extends AppCompatActivity { //implements LocationListener {

    TextView txtLocationData;
    //LocationManager locationManager;

    GoogleApiClient apiClient;
    GoogleApiClient.Builder builder;

    Location myFavLocation; // Initialize and set Latitude and Longitude

    GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(@Nullable Bundle bundle) {
            Toast.makeText(MyLocationActivity.this, "Connected to Google Services", Toast.LENGTH_LONG).show();

            if (ActivityCompat.checkSelfPermission(MyLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MyLocationActivity.this, "Please Grant Permissions in the Settings to access Location", Toast.LENGTH_LONG).show();
            } else {

                Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                txtLocationData.setText("Location is: " + latitude + " - " + longitude);
            }

        }

        @Override
        public void onConnectionSuspended(int i) {
            Toast.makeText(MyLocationActivity.this, "Connection Suspended with Google Services", Toast.LENGTH_LONG).show();
        }
    };

    GoogleApiClient.OnConnectionFailedListener connectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Toast.makeText(MyLocationActivity.this, "Connection Failed with Google Services", Toast.LENGTH_LONG).show();
        }
    };

    void fetchLocationAgainAndAgain() {

        @SuppressLint("RestrictedApi")

        LocationRequest request = new LocationRequest();
        request.setInterval(10000);
        request.setFastestInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //...
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, request, new com.google.android.gms.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //....

                //float distance = location.distanceTo(myFavLocation);
                //float speed = location.getSpeed(); // mps

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        txtLocationData = findViewById(R.id.textViewLocation);

        // initialized location manager
        //locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        builder = new GoogleApiClient.Builder(this);
        builder.addConnectionCallbacks(connectionCallbacks);
        builder.addOnConnectionFailedListener(connectionFailedListener);
        builder.addApi(LocationServices.API);

        apiClient = builder.build();


    }

    public void clickHandler(View view) {

        apiClient.connect(); // Connect to google Services to fetch Location

        /*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Please Grant Permissions in the Settings to access Location",Toast.LENGTH_LONG).show();
        }else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 5, this);
        }

        // Explore below API to explicitly get permissions from User in App without going to Settings
        //ActivityCompat.requestPermissions();
        */
    }


    /*
    // Executed whenever thr is a change in location after 10 secs or 5 mtrs
    @Override
    public void onLocationChanged(Location location) {

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        txtLocationData.setText("Location is: "+latitude+" - "+longitude);

        // Once, we get the location we shall remove the listener
        //locationManager.removeUpdates(this);

        // Reverse Geocoding | Fetch Address from Latitude and Longitude
        try {
            Geocoder geocoder = new Geocoder(this);

            List<Address> list = geocoder.getFromLocation(latitude,longitude,5);

            if(list !=null && list.size()>0){

                Address address = list.get(0); // Fetching 0th index Address, closest match

                StringBuffer buffer = new StringBuffer();

                for(int i=0;i<=address.getMaxAddressLineIndex();i++){
                    buffer.append(address.getAddressLine(i)+"\n");
                }

                txtLocationData.setText("Location is: "+latitude+" - "+longitude+"\n"+buffer.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // No More listening to location updates
        locationManager.removeUpdates(this);
    }

    */
}

