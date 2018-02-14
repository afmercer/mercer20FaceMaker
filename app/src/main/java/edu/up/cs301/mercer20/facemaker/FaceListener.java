package edu.up.cs301.mercer20.facemaker;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

/**
 * Created by mercer20 on 2/13/2018.
 */

public class FaceListener
        implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private Face changeFace;
    private RadioButton radioHair;
    private RadioButton radioEyes;
    private RadioButton radioSkin;
    private SeekBar sbRed;
    private SeekBar sbBlue;
    private SeekBar sbGreen;

    FaceListener(Face initFace) {
        changeFace = initFace;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        String spinnerLabel = parent.getItemAtPosition(pos).toString();
        if(spinnerLabel.equalsIgnoreCase("Bowl Cut")) {
            changeFace.setHairStyle(0);
        }
        else if(spinnerLabel.equalsIgnoreCase("Afro")) {
            changeFace.setHairStyle(1);
        }
        else if(spinnerLabel.equalsIgnoreCase("Balding")) {
            changeFace.setHairStyle(2);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing
    }

    public void setRadioID (RadioButton hair, RadioButton eyes, RadioButton skin) {
        radioHair = hair;
        radioEyes = eyes;
        radioSkin = skin;
    }

    public void setSeekBar (SeekBar red, SeekBar green, SeekBar blue) {
        sbRed = red;
        sbGreen = green;
        sbBlue = blue;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
        int hairColor[] = changeFace.getHairColor();
        int eyeColor[] = changeFace.getEyeColor();
        int skinColor[] = changeFace.getSkinColor();
        if(id == radioHair.getId()) {
            sbRed.setProgress(hairColor[0]);
            sbGreen.setProgress(hairColor[1]);
            sbBlue.setProgress(hairColor[2]);
        }
        else if(id == radioEyes.getId()) {
            sbRed.setProgress(eyeColor[0]);
            sbGreen.setProgress(eyeColor[1]);
            sbBlue.setProgress(eyeColor[2]);
        }
        else if(id == radioSkin.getId()) {
            sbRed.setProgress(skinColor[0]);
            sbGreen.setProgress(skinColor[1]);
            sbBlue.setProgress(skinColor[2]);

        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //do nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //do nothing
    }
}
