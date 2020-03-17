package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static boolean isPoint = false;

    private Calculator calculator = new Calculator();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<View> allButtons;
        allButtons = ((TableLayout) findViewById(R.id.tableLayout)).getTouchables();
        for (int i = 0; i< allButtons.size(); i++) {
            Button oButton =  (Button)allButtons.get(i);
            String btnText = oButton.getResources().getResourceEntryName(oButton.getId());
            int[] colors = {Color.parseColor("#FFFFFF"), Color.parseColor("#c2c2c2")};
            if ("ac".equals(btnText)) {
                colors = new int[]{Color.parseColor("#FFC107"), Color.parseColor("#c2c2c2")};

            } else if ("op".equals(btnText)) {
                colors = new int[]{Color.parseColor("#BDBDBD"), Color.parseColor("#c2c2c2")};
            }
                GradientDrawable drawable =(GradientDrawable) ContextCompat.getDrawable(this, R.drawable.button_design);
                drawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                drawable.setColors(colors);
                oButton.setBackground(drawable);
            oButton.setOnClickListener(createClickListener());
    }
  }

  private View.OnClickListener createClickListener() {

        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button oButton = (Button)v;
                StringBuilder buttonText = new StringBuilder(oButton.getText());
                TextView oTextView = (TextView)findViewById(R.id.textView);
                calculator.setButtonText(buttonText);
                calculator.setTextView(oTextView);
                calculator.doAction();
            }

        };

  }


}
