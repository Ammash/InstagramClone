package com.english.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.TimeZoneFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSave ;
    EditText editName , punchSpeed ,punchPower , kickSpeed , kickPower ;
    TextView txtGetData ;
    Button btnGetAlldata ;
    String allKickBoxer ;
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

        txtGetData = findViewById(R.id.txtGetData);
        btnGetAlldata = findViewById(R.id.btnGetAlldata);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("sEKheQ3U7n", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null){
                            txtGetData.setText(object.get("Name")+" - "+" punchSpeed : "+object.get("punchSpeed"));
                        }
                    }
                });
            }
        });
        btnGetAlldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allKickBoxer = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {
                                    allKickBoxer = allKickBoxer + parseObject.get("Name") + "\n";
                                }
                                FancyToast.makeText(MainActivity.this, allKickBoxer, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            }
                        }
                    }
                });
            }
        });

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
