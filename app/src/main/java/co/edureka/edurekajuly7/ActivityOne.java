package co.edureka.edurekajuly7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Date;

public class ActivityOne extends AppCompatActivity {

    // Declare a Reference to the WebView Class
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding the Layout on Activity
        setContentView(R.layout.activity_webview);

        // To Hide the ActionBar
        getSupportActionBar().hide();

        Log.i("ActivityOne","--onCreate--");

        // After setContentView, Initialize the reference webView
        // findViewById will create an Object of WebView by reading XML File, and will return referennce to the object
        // which is stored in ref variable
        webView = findViewById(R.id.webView);

        // Load WebPage in WebView

        //1. Set the WebViewClient
        webView.setWebViewClient(new WebViewClient());

        //2. Enable JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        //3. Load URL which you wann display
        webView.loadUrl("https://www.amazon.in/");
    }

    @Override
    protected void onStart() {
        super.onStart(); // super call is mandatory as life cycle is managed y the parent
        Log.i("ActivityOne","--onStart--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityOne","--onResume--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityOne","--onPause--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityOne","--onStop--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityOne","--onDestroy--");
    }

    // public method and any name of our choice. But it takes View Object's Reference as Input. View is an API
    public void clickHandler(View view){

        Date date = new Date();
        String today = date.toString();

        Toast.makeText(this,"Today is: "+today,Toast.LENGTH_LONG).show();

    }

}
