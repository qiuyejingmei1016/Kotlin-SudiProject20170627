package com.ysr.library.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by ysr on 2017/7/17 12:00.
 * 邮箱 ysr200808@163.com
 */

@SuppressLint("AppCompatCustomView")
public class TimeTextView extends TextView {
    private Random random;
    private int rgb;

    public TimeTextView(Context context) {
        super(context);
        initView();
    }

    public TimeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @SuppressLint("NewApi")
    public TimeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    public TimeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        random = new Random();
        //定义颜色---这里纯粹为了好玩--大家定义的时候可以在自定义控件外边定义，将颜色传递进来
        rgb = Color.rgb(100 + random.nextInt(155), 100 + random.nextInt(155),
                random.nextInt(100 + 155));
    }



    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(rgb);
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL_AND_STROKE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2,
                paint);
        super.onDraw(canvas);
    }
}
