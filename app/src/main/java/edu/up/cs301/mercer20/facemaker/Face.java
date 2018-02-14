package edu.up.cs301.mercer20.facemaker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;

/**
 * This class draws the various features of a face, such as the head, hair, and eyes
 * according to specified values
 *
 * @author Adam Mercer
 */

public class Face {
    //declare local variables
    private int skinColorRed;
    private int skinColorGreen;
    private int skinColorBlue;
    private int eyeColorRed;
    private int eyeColorGreen;
    private int eyeColorBlue;
    private int hairColorRed;
    private int hairColorGreen;
    private int hairColorBlue;
    private int hairStyle;
    private Random rand;
    private FaceView newFaceView;

    /** constructor for Face class. Initializes and instance of FaceView class*/
    public Face(FaceView initFaceView ) {
        newFaceView = initFaceView;
        randomize();
    }

    /**
     * randomizes the color of hair, eyes, and skin, by initializing random red, green, and
     * blue values for each rgb component of the color
     */
    public void randomize() {
        rand = new Random(); //used for generating random numbers

        hairStyle = rand.nextInt(3);
        skinColorRed = rand.nextInt(255);
        skinColorBlue = rand.nextInt(255);
        skinColorGreen = rand.nextInt(255);
        eyeColorRed = rand.nextInt(255);
        eyeColorGreen = rand.nextInt(255);
        eyeColorBlue = rand.nextInt(255);
        hairColorRed = rand.nextInt(255);
        hairColorBlue = rand.nextInt(255);
        hairColorGreen = rand.nextInt(255);

        newFaceView.invalidate(); //redraw face after attributes have been randomized
    }

    /** draws a face on a given canvas*/
    public void onDraw(Canvas canvas) {
        //call helper methods to draw head, eyes, and hair of face
        drawHead(canvas);
        drawEyes(canvas);
        drawHair(canvas, hairStyle);
    }

    /**
     * Used to initialize the hairstyle of the face from a different class.
     *
     * @param style integer that specifies the hairstyle of the face. 0 for "bowl cut",
     *              1 for "afro" and 2 for "balding".
     */
    public void setHairStyle(int style) {
        hairStyle = style;
        newFaceView.invalidate(); //redraw face after hairstyle has been changed
    }

    /** Used to retrieve the current hairstyle from a different class*/
    public int getHairStyle() {
        return hairStyle;
    }

    /** Used to retrieve the current hair color from a different class*/
    public int[] getHairColor() {
        //initializes an array with the red, green, and blue component of hair color
        int[] hairColor = {hairColorRed, hairColorGreen, hairColorBlue};
        return hairColor;
    }

    /** Used to retrieve the current eye color from a different class*/
    public int[] getEyeColor() {
        int[] eyeColor = {eyeColorRed, eyeColorGreen, eyeColorBlue};
        return eyeColor;
    }

    /** Used to retrieve the current skin color from a different class*/
    public int[] getSkinColor() {
        int[] skinColor = {skinColorRed, skinColorGreen, skinColorBlue};
        return skinColor;
    }

    /**
     * Used to initialize the red, green, OR blue component of hair color from a different class.
     *
     * @param colorValue integer that specifies the new color component's value
     * @param rgb        string that identifies the color component to be changed. "r" if red
     *                   component to be changed, "g" for green, and "b" for blue.
     */
    public void setHairColor(int colorValue, String rgb) {
        //check if color component to be changed is red, green, or blue
        if(rgb.equals("r")) {
            hairColorRed = colorValue;
        }
        else if(rgb.equals("g")) {
            hairColorGreen = colorValue;
        }
        else if(rgb.equals("b")) {
            hairColorBlue = colorValue;
        }
        newFaceView.invalidate(); //redraw face after color component has been changed
    }

    /**
     * Used to initialize the red, green, OR blue component of eye color from a different class.
     *
     * @param colorValue integer that specifies the new color component's value
     * @param rgb        string that identifies the color component to be changed. "r" if red
     *                   component to be changed, "g" for green, and "b" for blue.
     */
    public void setEyeColor(int colorValue, String rgb) {
        if(rgb.equals("r")) {
            eyeColorRed = colorValue;
        }
        else if(rgb.equals("g")) {
            eyeColorGreen = colorValue;
        }
        else if(rgb.equals("b")) {
            eyeColorBlue = colorValue;
        }
        newFaceView.invalidate(); //redraw after color component is changed
    }

    /**
     * Used to initialize the red, green, OR blue component of skin color from a different class.
     *
     * @param colorValue integer that specifies the new color component's value
     * @param rgb        string that identifies the color component to be changed. "r" if red
     *                   component to be changed, "g" for green, and "b" for blue.
     */
    public void setSkinColor(int colorValue, String rgb) {
        if(rgb.equals("r")) {
            skinColorRed = colorValue;
        }
        else if(rgb.equals("g")) {
            skinColorGreen = colorValue;
        }
        else if(rgb.equals("b")) {
            skinColorBlue = colorValue;
        }
        newFaceView.invalidate(); //redraw after skin color is changed
    }

    /** Draws the head of the face on a given canvas. */
    public void drawHead(Canvas canvas) {
        Paint skinColor = new Paint();
        Paint whitePaint = new Paint();
        Paint blackPaint = new Paint();
        //initialize paint for skin color based on given color components of skin color
        skinColor.setColor(Color.rgb(skinColorRed, skinColorGreen, skinColorBlue));
        whitePaint.setColor(Color.WHITE);
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setStrokeWidth(10.0f);
        //draw head of the face
        canvas.drawOval(400.0f, 200.0f, 1150.0f, 1050.0f, skinColor);
        //draw teeth of the mouth for the face
        canvas.drawArc(500.0f, 600.0f, 1050.0f, 900.0f, 0, 180, true, whitePaint);
        //draw outline around mouth
        canvas.drawArc(500.0f, 600.0f, 1050.0f, 900.0f, 0, 180, true, blackPaint);
        //draw line to separate upper and lower teeth
        blackPaint.setStrokeWidth(3.0f);
        canvas.drawLine(520.0f, 810.0f, 1030.0f, 810.0f, blackPaint);
    }

    /** Draws the eyes of the face on a given canvas. */
    public void drawEyes(Canvas canvas) {
        Paint eyeColor = new Paint();
        Paint whitePaint = new Paint();
        Paint blackPaint = new Paint();
        //initialize paint for eyes based on given color components of eye color
        eyeColor.setColor(Color.rgb(eyeColorRed, eyeColorGreen, eyeColorBlue));
        whitePaint.setColor(Color.WHITE);
        blackPaint.setColor(Color.BLACK);

        //draw left eye
        canvas.drawCircle(600, 515, 50, whitePaint); //draw white part of eye
        canvas.drawCircle(600, 515, 35, eyeColor);  //draw iris
        canvas.drawCircle(600, 515, 15, blackPaint); //draw pupil

        //draw right eye
        canvas.drawCircle(950, 515, 50, whitePaint);
        canvas.drawCircle(950, 515, 35, eyeColor);
        canvas.drawCircle(950, 515, 15, blackPaint);
    }

    /** Draws the hair of the face on a given canvas. */
    public void drawHair(Canvas canvas, int style) {
        Paint hairColor = new Paint();
        //initialize paint for hair based on given color components of hair color
        hairColor.setColor(Color.rgb(hairColorRed, hairColorGreen, hairColorBlue));
        //determine which hairstyle to draw
        if(style == 0) {
            /**
             External Resource
                Date:       2/13/208
                Problem:    Could not get drawArc to work the way I wanted it to
                Resource:   https://developer.android.com/reference/android/graphics/
                            Canvas.html#drawArc(float, float, float, float, float, float, boolean,
                            android.graphics.Paint)
                Solution:   Used this description of drawArc and set useCenter boolean to false
             */

            //draw bowl cut
            canvas.drawArc(400, 200, 1150, 1050, 210, 120, false, hairColor);
        }
        else if(style == 1) {
            //draw afro
            canvas.drawArc(455, 100, 1095, 700, 180, 180, true, hairColor);
        }
        else if(style == 2) {
            //draw balding style hair
            canvas.drawArc(400, 200, 1150, 1050, 195, 60, false, hairColor);
            canvas.drawArc(400, 200, 1150, 1050, 285, 60, false, hairColor);
        }

    }
}
