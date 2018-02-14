package edu.up.cs301.mercer20.facemaker;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private String[] hairStyleNames = {"Bowl Cut", "Afro", "Balding"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> hairAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, hairStyleNames);
        Spinner hairStyles = (Spinner)findViewById(R.id.spinnerHairStyles);
        hairStyles.setAdapter(hairAdapter);

        FaceView myFaceView = findViewById(R.id.surfaceFaceView);
        Face myFace = new Face(myFaceView);
        myFaceView.setFace(myFace);

        //Register instance of listener class with view
        FaceListener myFaceListener = new FaceListener(myFace);
        Spinner hairSpinner = (Spinner)findViewById(R.id.spinnerHairStyles);
        hairSpinner.setOnItemSelectedListener(myFaceListener);

        RadioButton hairID = (RadioButton)findViewById(R.id.radioHair);
        RadioButton eyeID = (RadioButton)findViewById(R.id.radioEyes);
        RadioButton skinID = (RadioButton)findViewById(R.id.radioSkin);

        SeekBar redValue = (SeekBar)findViewById(R.id.seekBarRed);
        SeekBar greenValue = (SeekBar)findViewById(R.id.seekBarGreen);
        SeekBar blueValue = (SeekBar)findViewById(R.id.seekBarBlue);
        myFaceListener.setSeekBar(redValue, greenValue, blueValue);

        myFaceListener.setRadioID(hairID, eyeID, skinID);
        myFaceListener.setSeekBar(redValue, greenValue, blueValue);
        RadioGroup facialFeatures = (RadioGroup)findViewById(R.id.radioGroupFace);
        facialFeatures.setOnCheckedChangeListener(myFaceListener);
    }
}
