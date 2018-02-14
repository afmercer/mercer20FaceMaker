package edu.up.cs301.mercer20.facemaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * This is the main class for the program. It registers an instance of listener classes
 * with the corresponding view.
 *
 * @author Adam Mercer
 */

public class MainActivity extends AppCompatActivity {

    private String[] hairStyleNames = {"Bowl Cut", "Afro", "Balding"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add hairstyle options to spinner
        ArrayAdapter<String> hairAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, hairStyleNames);
        Spinner hairStyles = (Spinner)findViewById(R.id.spinnerHairStyles);
        hairStyles.setAdapter(hairAdapter);

        //create an instance of myFaceView and myFace
        FaceView myFaceView = (FaceView)findViewById(R.id.surfaceFaceView);
        Face myFace = new Face(myFaceView);
        myFaceView.setFace(myFace);

        //Register instance of listener class with spinner
        FaceListener myFaceListener = new FaceListener(myFace);
        hairStyles.setOnItemSelectedListener(myFaceListener);

        //initialize RadioButtons for use in FaceListener class
        RadioButton hairID = (RadioButton)findViewById(R.id.radioHair);
        RadioButton eyeID = (RadioButton)findViewById(R.id.radioEyes);
        RadioButton skinID = (RadioButton)findViewById(R.id.radioSkin);
        myFaceListener.setRadioID(hairID, eyeID, skinID);

        //initialize SeekBars for use in FaceListener class
        SeekBar redValue = (SeekBar)findViewById(R.id.seekBarRed);
        SeekBar greenValue = (SeekBar)findViewById(R.id.seekBarGreen);
        SeekBar blueValue = (SeekBar)findViewById(R.id.seekBarBlue);
        myFaceListener.setSeekBar(redValue, greenValue, blueValue);

        //Register instance of listener class with RadioGroup
        RadioGroup facialFeatures = (RadioGroup)findViewById(R.id.radioGroupFace);
        facialFeatures.setOnCheckedChangeListener(myFaceListener);

        //Register instance of listener class with spinner
        redValue.setOnSeekBarChangeListener(myFaceListener);
        greenValue.setOnSeekBarChangeListener(myFaceListener);
        blueValue.setOnSeekBarChangeListener(myFaceListener);

        //Register instance of listener class with button
        Button butRandomFace = (Button)findViewById(R.id.buttonRandom);
        butRandomFace.setOnClickListener(myFaceListener);

        hairID.setChecked(true);
        myFaceListener.setSpinner(hairStyles);//initialize spinner for use in FaceListener class
        myFaceListener.setRadioGroup(facialFeatures);//initialize RadioGroup for use in FaceListener class
        //set SeekBars, Spinner, and RadioGroup to accurately reflect face upon startup
        myFaceListener.assignRandom();
    }
}
