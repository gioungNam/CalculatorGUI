package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private View prvButton;
    private Calculator calculator = new Calculator();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<View> allButtons;
        allButtons = ((TableLayout) findViewById(R.id.tableLayout)).getTouchables();
        for (int i = 0; i< allButtons.size(); i++) {
            View oButton =  (View)allButtons.get(i);
            oButton.setOnClickListener(createClickListener());

    }
  }

  private View.OnClickListener createClickListener() {

        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String viewText = "";

                if (v.getId() == R.id.button_del) {
                    ImageButton oButton = (ImageButton)v;
                    viewText = "DEL";
                    setPrvButton(oButton);
                } else {
                    Button oButton = (Button)v;
                    viewText = oButton.getText().toString();
                    setPrvButton(oButton);
                }

                StringBuilder buttonText = new StringBuilder(viewText);
                TextView oTextView = (TextView)findViewById(R.id.textView);
                calculator.setButtonText(buttonText);
                calculator.setTextView(oTextView);
                calculator.doAction();
            }

        };

  }

    /**
     * 이전버튼 setting
     * @param button 버튼객체
     */
  public void setPrvButton(View button)
  {
      if (button.equals(prvButton)) {
          return;
      }
      if (prvButton != null) prvButton.setSelected(false);

       button.setSelected(true);
       prvButton = button;
  }

}
