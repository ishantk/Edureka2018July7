package co.edureka.edurekajuly7;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class BooksFetcherService extends IntentService {

    public BooksFetcherService(){
        super("MyService");
    }

    public BooksFetcherService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            try {

                StringBuffer response = new StringBuffer();

                // URL, where we shall be requesting
                URL url = new URL("http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2");

                // Sending Request
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Receiving Response
                InputStream inputStream = urlConnection.getInputStream();

                // Read the Response
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                while((line = bufferedReader.readLine()) !=null ) {
                    response.append(line + "\n");
                }

                Intent intent1 = new Intent("books.fetched");
                intent1.putExtra("keyResponse",response.toString());
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);

                Log.i("Service","onHandleIntent - "+response.toString());

            }catch (Exception e){
                Log.i("Service","Some Error");
                e.printStackTrace();
            }


        }
    }


}
