package edu.up.cs301.mercer20.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * This is a subclass of SurfaceView. It calls methods to paint a face on
 * a surface view.
 *
 * @author Adam Mercer
 */

public class FaceView extends SurfaceView {
    /** required constructor for SurfaceView*/
    public FaceView(Context context) {
        super(context);
        setWillNotDraw(false);

    }

    /** required constructor for SurfaceView*/
    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    /** required constructor for SurfaceView*/
    public FaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    /**
     External Citation
        Date:       2/13/2018
        Problem:    Was not able to call draw method of Face class in MyFaceView

        Resource:
                    Professor Nuxoll's office hours
        Solution:   Made a method that creates an instance of Face class
     *
     */

    /** initialize instance of face*/
    private Face myFace = null;
    public void setFace(Face initFace) {
        myFace = initFace;
    }

    @Override
    public void onDraw(Canvas canvas) {
        //if instance of face is not initialized, do not draw
        if(myFace == null) {
            return;
        }

        //draw white background that covers the surface view
        int width = this.getWidth();
        int height = this.getHeight();
        Paint background = new Paint();
        background.setColor(Color.WHITE);
        canvas.drawRect(0.0f, 0.0f, width, height, background);

        //draw face using onDraw method of Face class
        myFace.onDraw(canvas);
    }
}
