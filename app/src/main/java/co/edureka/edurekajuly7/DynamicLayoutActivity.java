package co.edureka.edurekajuly7;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DynamicLayoutActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    Button button;
    EditText eTxtName;

    void initViews(){
        button = new Button(this);
        button.setText("Click Me");
        button.setBackgroundColor(Color.RED);

        eTxtName = new EditText(this);
        //....

        relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(Color.CYAN);

        RelativeLayout.LayoutParams btnParams = new RelativeLayout.LayoutParams(
          RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        btnParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnParams.addRule(RelativeLayout.CENTER_VERTICAL);

        relativeLayout.addView(button,btnParams);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DynamicLayoutActivity.this,"You Clicked Button",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setContentView(relativeLayout);
    }
}
