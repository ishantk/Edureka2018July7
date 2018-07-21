package co.edureka.edurekajuly7;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);

        Button button = findViewById(R.id.button);

        //Animation animation = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        //Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        //Animation animation = AnimationUtils.loadAnimation(this,R.anim.translate_anim);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        button.startAnimation(animation);

        ImageView imageView = findViewById(R.id.imageView);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();

    }
}
