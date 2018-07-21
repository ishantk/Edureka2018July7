package co.edureka.edurekajuly7;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MyMediaPlayerActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnPlayStop;
    boolean isPlaying;

    String songToPlay;

    MediaPlayer mediaPlayer;

    String url;

    void initViews(){
        btnPlayStop = findViewById(R.id.buttonPlayStop);
        btnPlayStop.setOnClickListener(this);

        url = "https://somedomain.com/audios/song.mp3";

        songToPlay = Environment.getExternalStorageDirectory().getPath()+"/Music/Nights.mp3";
        mediaPlayer = new MediaPlayer();

        // Default Value | Means Song is not played !!
        isPlaying = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_media_player);
        initViews();
    }

    void playSong(){

        try {

            mediaPlayer.setDataSource(songToPlay);
            //mediaPlayer.setDataSource(this, Uri.parse(url)); // Stream Music from Server
            mediaPlayer.prepare();
            mediaPlayer.start();

            //mediaPlayer.seekTo(2000*60);
            //mediaPlayer.pause();

            Log.i("Music","==Music Started==");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void stopSong(){
        mediaPlayer.stop();
        mediaPlayer.release();
        Log.i("Music","==Music Stopped==");
    }

    @Override
    public void onClick(View v) {

        if(!isPlaying){
           playSong();
           btnPlayStop.setText("Stop Music");
           isPlaying = true;
        }else{
            stopSong();
            btnPlayStop.setText("Play Music");
            isPlaying = false;
        }

    }
}
