package edu.up.cs301.mercer20.facemaker;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * This class listens for various actions from views. This class determines what happens
 * when these actions happen.
 *
 * Created by mercer20 on 2/13/2018.
 */

public class FaceListener
        implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    //declare local variables
    private Face changeFace;
    private RadioButton radioHair;
    private RadioButton radioEyes;
    private RadioButton radioSkin;
    private SeekBar sbRed;
    private SeekBar sbBlue;
    private SeekBar sbGreen;
    private Spinner hairStyles;
    private RadioGroup faceFeatures;

    /** Constructor for FaceListener initializes an instance of Face class */
    FaceListener(Face initFace) {
        changeFace = initFace;
    }

    /**
     * Method for listening when an item is selected in a spinner.
     * Changes the hairstyle of face based on selected item from spinner.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        /**
         External Citation
            Date:       2/13/2018
            Problem:    Could not get current selected item of spinner
            Resource:
                https://stackoverflow.com/questions/10331854/
                how-to-get-spinner-selected-item-value-to-string
            Solution: I used an example from this page
         */
        String spinnerLabel = parent.getItemAtPosition(pos).toString();
        if(spinnerLabel.equalsIgnoreCase("Bowl Cut")) {
            changeFace.setHairStyle(0); //change hairstyle to bowl cut
        }
        else if(spinnerLabel.equalsIgnoreCase("Afro")) {
            changeFace.setHairStyle(1); //change hairstyle to afro
        }
        else if(spinnerLabel.equalsIgnoreCase("Balding")) {
            changeFace.setHairStyle(2); //change hairstyle to balding
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing. This event is not needed
    }

    /** Used to assign the hair, eyes, and skin RadioButtons from a different class*/
    public void setRadioID (RadioButton hair, RadioButton eyes, RadioButton skin) {
        radioHair = hair;
        radioEyes = eyes;
        radioSkin = skin;
    }

    /** Used to assign the red, green, and blue SeekBars from a different class*/
    public void setSeekBar (SeekBar red, SeekBar green, SeekBar blue) {
        sbRed = red;
        sbGreen = green;
        sbBlue = blue;
    }

    /**
     * Method for listening when an RadioButton is selected from a RadioGroup.
     * Used to determine what facial feature is being edited.
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
        //retrieve rgb components of the colors of the facial features
        int hairColor[] = changeFace.getHairColor();
        int eyeColor[] = changeFace.getEyeColor();
        int skinColor[] = changeFace.getSkinColor();

        //set SeekBars to reflect color of hair if hair RadioButton is selected
        if(id == radioHair.getId()) {
            sbRed.setProgress(hairColor[0]);
            sbGreen.setProgress(hairColor[1]);
            sbBlue.setProgress(hairColor[2]);
        }
        //set SeekBars to reflect color of eyes if eye RadioButton is selected
        else if(id == radioEyes.getId()) {
            sbRed.setProgress(eyeColor[0]);
            sbGreen.setProgress(eyeColor[1]);
            sbBlue.setProgress(eyeColor[2]);
        }
        //set SeekBars to reflect color of skin if skin RadioButton is selected
        else if(id == radioSkin.getId()) {
            sbRed.setProgress(skinColor[0]);
            sbGreen.setProgress(skinColor[1]);
            sbBlue.setProgress(skinColor[2]);
        }
    }

    /**
     * Method for listening when SeekBars progress is changed.
     * Used to change the rgb components of a specified element
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        //change hair color if hair RadioButton is selected
        if(radioHair.isChecked()) {
            //edit rgb component based on which SeekBar was changed
            if(seekBar.getId() == sbRed.getId()) {
                changeFace.setHairColor(progress, "r");
            }
            else if(seekBar.getId() == sbGreen.getId()) {
                changeFace.setHairColor(progress, "g");
            }
            else if(seekBar.getId() == sbBlue.getId()) {
                changeFace.setHairColor(progress, "b");
            }
        }
        //change eye color if eye RadioButton is selected
        else if(radioEyes.isChecked()) {
            //edit rgb component based on which SeekBar was changed
            if(seekBar.getId() == sbRed.getId()) {
                changeFace.setEyeColor(progress, "r");
            }
            else if(seekBar.getId() == sbGreen.getId()) {
                changeFace.setEyeColor(progress, "g");
            }
            else if(seekBar.getId() == sbBlue.getId()) {
                changeFace.setEyeColor(progress, "b");
            }
        }
        //change skin color if skin RadioButton is selected
        else if(radioSkin.isChecked()) {
            //edit rgb component based on which SeekBar was changed
            if(seekBar.getId() == sbRed.getId()) {
                changeFace.setSkinColor(progress, "r");
            }
            else if(seekBar.getId() == sbGreen.getId()) {
                changeFace.setSkinColor(progress, "g");
            }
            else if(seekBar.getId() == sbBlue.getId()) {
                changeFace.setSkinColor(progress, "b");
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //do nothing. Event is not needed.
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //do nothing. Even is not needed.
    }

    /**
     * Method for listening when a button is clicked.
     * Used to generate a random face.
     */
    @Override
    public void onClick(View view) {
        Button buttonClicked = (Button)view;
        String buttonLabel = (String)buttonClicked.getText();

        //check that correct button was pressed
        if(buttonLabel.equalsIgnoreCase("random face")) {
            changeFace.randomize(); //initialize all attributes to random values
            assignRandom(); //set seekbars and spinner to reflect that change
        }
    }

    /** Used to assign hairstyle spinner from a different class*/
    public void setSpinner (Spinner hair) {
        hairStyles = hair;
    }

    /** Used to assign the RadioGroup from a different class*/
    public void setRadioGroup (RadioGroup features) {
        faceFeatures = features;
    }

    /**
     *  Changes the SeekBars and Spinner to reflect the random face that has been generated
     */
    public void assignRandom () {
        int colorValues[];
        //determine which RadioButton is selected
        int selectedID = faceFeatures.getCheckedRadioButtonId();
        //if hair RadioButton is selected, change SeekBars to reflect hair color
        if(selectedID == radioHair.getId()) {
            colorValues = changeFace.getHairColor();
            sbRed.setProgress(colorValues[0]);
            sbGreen.setProgress(colorValues[1]);
            sbBlue.setProgress(colorValues[2]);
        }
        //if eyes RadioButton is selected, change SeekBars to reflect eye color
        else if(selectedID == radioEyes.getId()) {
            colorValues = changeFace.getEyeColor();
            sbRed.setProgress(colorValues[0]);
            sbGreen.setProgress(colorValues[1]);
            sbBlue.setProgress(colorValues[2]);
        }
        //if skin RadioButton is selected, change SeekBars to reflect skin color
        else if(selectedID == radioSkin.getId()) {
            colorValues = changeFace.getSkinColor();
            sbRed.setProgress(colorValues[0]);
            sbGreen.setProgress(colorValues[1]);
            sbBlue.setProgress(colorValues[2]);
        }
        //change spinner selection to reflect current hairstyle
        hairStyles.setSelection(changeFace.getHairStyle());
    }
}
