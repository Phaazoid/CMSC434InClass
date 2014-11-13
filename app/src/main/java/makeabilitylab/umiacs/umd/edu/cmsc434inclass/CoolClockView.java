package makeabilitylab.umiacs.umd.edu.cmsc434inclass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * Created by apple on 11/12/14.
 */
public class CoolClockView extends View {

    private Bitmap _qrBitmap;
    private Paint _paint = new Paint();
    private double _angleHour = 0;
    private double _angleMinute = 0;
    private double _angleSecond = 0;
    private float _distHour;
    private float _distMinute;
    private float _distSecond;
    private float _radiusHour = 20;
    private float _radiusMinute = 15;
    private float _radiusSecond = 10;

    public CoolClockView(Context context) {
        super(context);
        init(null, 0);
    }

    public CoolClockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public CoolClockView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init(attributeSet, defStyle);
    }

    private void init(AttributeSet attributeSet, int defStyle) {
        _paint.setAntiAlias(true);
        Log.d(this.VIEW_LOG_TAG, "Initialized CoolClockView");
    }

    public void updateTime(Calendar calendar) {
        _angleHour = Math.toRadians(-90+calendar.get(Calendar.HOUR)*30);
        _angleMinute = Math.toRadians(-90+calendar.get(Calendar.MINUTE)*6);
        _angleSecond = Math.toRadians(-90+calendar.get(Calendar.SECOND)*6 +
                calendar.get(Calendar.MILLISECOND)*30.0/1000.0);
        _distHour = getWidth()/2-getLeftPaddingOffset();
        _distMinute = getWidth()/3;
        _distSecond = getWidth()/4;
        invalidate();
    }



    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = getWidth()/2;
        int cy = getHeight()/2;
        //Log.d(this.VIEW_LOG_TAG,"center x: " + cx + "; center y: " + cy);
        //Log.d(this.VIEW_LOG_TAG,"disth : " + _distHour + "; distm: " + _distMinute + "; dists: " + _distSecond);
        //canvas.drawBitmap(_qrBitmap, cx-_qrBitmap.getWidth()/2, cy-_qrBitmap.getHeight()/2, _paint);

        //Draw middle circle
        _paint.setStyle(Paint.Style.FILL);
        _paint.setColor(Color.CYAN);
        canvas.drawCircle(cx, cy, 10, _paint);

        //Draw hour
        _paint.setColor(Color.RED);
        _paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx, cy, _distHour, _paint);
        _paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx+_distHour*(float)Math.cos(_angleHour),
                cy+_distHour*(float)Math.sin(_angleHour), _radiusHour, _paint);

        //Draw minute
        _paint.setColor(Color.BLUE);
        _paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx, cy, _distMinute, _paint);
        _paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx+_distMinute*(float)Math.cos(_angleMinute),
                cy+_distMinute*(float)Math.sin(_angleMinute), _radiusMinute, _paint);

        //Draw second
        _paint.setColor(Color.GREEN);
        _paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx, cy, _distSecond, _paint);
        _paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx+_distSecond*(float)Math.cos(_angleSecond),
                cy+_distSecond*(float)Math.sin(_angleSecond), _radiusSecond, _paint);
    }
}
