package com.english.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.TimeZoneFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSave ;
    EditText editName , punchSpeed ,punchPower , kickSpeed , kickPower ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        editName = findViewById(R.id.Name);
        punchSpeed = findViewById(R.id.punchSpeed);
        punchPower = findViewById(R.id.punchPower);
        kickSpeed = findViewById(R.id.kickSpeed);
        kickPower = findViewById(R.id.kickPower);
    }

    @Override
    public void onClick(View view) {
        try{
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("Name",editName.getText().toString());
            kickBoxer.put("punchSpeed",Integer.parseInt(punchSpeed.getText().toString()));
            kickBoxer.put("punchPower",Integer.parseInt(punchPower.getText().toString()));
            kickBoxer.put("kickSpeed",Integer.parseInt(kickSpeed.getText().toString()));
            kickBoxer.put("kickPower",Integer.parseInt(kickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null){
                        FancyToast.makeText(MainActivity.this,kickBoxer.get("Name")+" Hello World !",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();                }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
