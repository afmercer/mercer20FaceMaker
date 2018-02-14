package edu.up.cs301.mercer20.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.SeekBar;

import java.util.Random;

/**
 * Created by AdamMercer on 2/12/18.
 */

public class Face {
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


    public Face(FaceView initFaceView ) {
        newFaceView = initFaceView;
        randomize();
    }

    public void randomize() {
        rand = new Random();
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
    }

    public void onDraw(Canvas canvas) {
        Paint skinColor = new Paint();
        skinColor.setColor(Color.rgb(skinColorRed, skinColorGreen, skinColorBlue));
        skinColor.setColor(Color.WHITE);
        canvas.drawRect(20, 20, 500, 500, skinColor);

        drawHead(canvas);
        drawEyes(canvas);
        drawHair(canvas, hairStyle);
    }

    public void setHairStyle(int style) {
        hairStyle = style;
        newFaceView.invalidate();
    }

    public int[] getHairColor() {
        int[] hairColor = {hairColorRed, hairColorGreen, hairColorBlue};
        return hairColor;
    }

    public int[] getEyeColor() {
        int[] eyeColor = {eyeColorRed, eyeColorGreen, eyeColorBlue};
        return eyeColor;
    }

    public int[] getSkinColor() {
        int[] skinColor = {skinColorRed, skinColorGreen, skinColorBlue};
        return skinColor;
    }

    public void drawHead(Canvas canvas) {
        Paint skinColor = new Paint();
        Paint whitePaint = new Paint();
        Paint blackPaint = new Paint();
        skinColor.setColor(Color.rgb(skinColorRed, skinColorGreen, skinColorBlue));
        whitePaint.setColor(Color.WHITE);
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setStrokeWidth(10.0f);
        canvas.drawOval(400.0f, 200.0f, 1150.0f, 1050.0f, skinColor);
        canvas.drawArc(500.0f, 600.0f, 1050.0f, 900.0f, 0, 180, true, whitePaint);
        canvas.drawArc(500.0f, 600.0f, 1050.0f, 900.0f, 0, 180, true, blackPaint);
        blackPaint.setStrokeWidth(3.0f);
        canvas.drawLine(520.0f, 810.0f, 1030.0f, 810.0f, blackPaint);
    }

    public void drawEyes(Canvas canvas) {
        Paint eyeColor = new Paint();
        Paint whitePaint = new Paint();
        Paint blackPaint = new Paint();
        eyeColor.setColor(Color.rgb(eyeColorRed, eyeColorGreen, eyeColorBlue));
        whitePaint.setColor(Color.WHITE);
        blackPaint.setColor(Color.BLACK);

        canvas.drawCircle(600, 515, 50, whitePaint);
        canvas.drawCircle(600, 515, 35, eyeColor);
        canvas.drawCircle(600, 515, 15, blackPaint);

        canvas.drawCircle(950, 515, 50, whitePaint);
        canvas.drawCircle(950, 515, 35, eyeColor);
        canvas.drawCircle(950, 515, 15, blackPaint);
    }

    public void drawHair(Canvas canvas, int style) {
        Paint hairColor = new Paint();
        hairColor.setColor(Color.rgb(hairColorRed, hairColorGreen, hairColorBlue));
        if(style == 0) {
            canvas.drawArc(400, 200, 1150, 1050, 210, 120, false, hairColor);
        }
        else if(style == 1) {
            canvas.drawArc(455, 100, 1095, 700, 180, 180, true, hairColor);
        }
        else if(style == 2) {
            canvas.drawArc(400, 200, 1150, 1050, 195, 60, false, hairColor);
            canvas.drawArc(400, 200, 1150, 1050, 285, 60, false, hairColor);
        }

    }
}
