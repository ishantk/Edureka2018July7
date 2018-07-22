package co.edureka.edurekajuly7;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BooksActivity extends AppCompatActivity {

    StringBuffer response;

    ArrayList<Book> bookList1;
    ArrayList<String> bookList2;

    ListView listView;
    ArrayAdapter<String> adapter;

    ProgressDialog progressDialog;

    StringRequest stringRequest;
    RequestQueue requestQueue;

    MyBooksReceiver booksReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        booksReceiver = new MyBooksReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("books.fetched");

        LocalBroadcastManager.getInstance(this).registerReceiver(booksReceiver,filter);

        requestQueue = Volley.newRequestQueue(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false); // Dialog will not dismiss on pressing back key

        listView = findViewById(R.id.listView);

        bookList1 = new ArrayList<>();
        bookList2 = new ArrayList<>();

        response = new StringBuffer();

        // Start the Thread -> It shall run asynchronously i.e. parallel to the Activity in the background
        if(isInternetConnected()) {
            //progressDialog.show();
            //new BooksFetcher().start();

            //new BooksFetcherTask().execute();

            progressDialog.show();
            //fetchBooks();

            Intent intent = new Intent(BooksActivity.this,BooksFetcherService.class);
            startService(intent);

        }else{
            Toast.makeText(this,"Please Connect to Internet and Try Again!!",Toast.LENGTH_LONG).show();
        }
    }

    boolean isInternetConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return  (networkInfo!=null && networkInfo.isConnected());
    }

    //1. Thread-Handler Model

    // BookFetcher is a background Thread and we cannot update UI here
    // We cannot display Toast, we cannot setText on any View etc..
    // has nothing to do with Activity because it is not running in the activity
    class BooksFetcher extends Thread{

        @Override
        public void run() {

            try {
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

                handler.sendEmptyMessage(101);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    // is running in Activity
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 101){
                parseResponse();
            }
        }
    };


    // 2. AsyncTask - API in Android to manage tasks in the background. Is a wrapper around Thread Handler

    class BooksFetcherTask extends AsyncTask{

        // runs in the activity
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        // runs async outside the activity thread
        @Override
        protected Object doInBackground(Object[] objects) {

            try {
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

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        // runs in the activity
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            parseResponse();

        }
    }

    //3. Volley - Library to perform Network operations.
    void fetchBooks(){

        stringRequest = new StringRequest(

                Request.Method.GET,

                "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        response.append(res);
                        parseResponse();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        /*
        stringRequest = new StringRequest(

                Request.Method.POST,

                "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        response.append(res);
                        parseResponse();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        )

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("key","value");
                //...
                return params;
            }
        }
        ;
        */

        requestQueue.add(stringRequest); // Sending a Request
    }


    void parseResponse(){
        //Toast.makeText(this,"Response: "+response.toString(),Toast.LENGTH_LONG).show();
        Log.i("Response",response.toString());

        try{

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("bookstore");
            for(int i=0;i<jsonArray.length();i++){

                JSONObject jObj = jsonArray.getJSONObject(i);
                Book book = new Book();

                book.price = jObj.getString("price");
                book.name = jObj.getString("name");
                book.author = jObj.getString("author");

                bookList1.add(book);
                bookList2.add(book.toString());
            }

            // Use bookList1 and write a custom adapter
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,bookList2);
            listView.setAdapter(adapter);
            progressDialog.dismiss();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //4. Service and Receiver Model
    class MyBooksReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals("books.fetched")) {

                String res = intent.getStringExtra("keyResponse");
                Log.i("Receiver", "==onReceive==" + res);

                response.append(res);
                parseResponse();
            }

        }
    }

}
