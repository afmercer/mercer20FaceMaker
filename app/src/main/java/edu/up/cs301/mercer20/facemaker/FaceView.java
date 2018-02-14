package edu.up.cs301.mercer20.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import java.util.Random;

/**
 * Created by AdamMercer on 2/10/18.
 */

public class FaceView extends SurfaceView {

    public FaceView(Context context) {
        super(context);
        setWillNotDraw(false);

    }

    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public FaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    private Face myFace = null;

    public void setFace(Face initFace) {
        myFace = initFace;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(myFace == null) {
            return;
        }

        int width = this.getWidth();
        int height = this.getHeight();
        Paint background = new Paint();
        background.setColor(Color.WHITE);
        canvas.drawRect(0.0f, 0.0f, width, height, background);

        myFace.onDraw(canvas);
    }
}
