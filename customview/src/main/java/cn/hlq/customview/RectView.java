package cn.hlq.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制矩形 Created by HLQ on 2017/8/21
 */

public class RectView extends View {

    private Paint paint;
    private Paint strokePaint;
    private Paint strokeWPaint;
    private Paint roundPaint;

    private Rect rect;
    private RectF rectF;

    public RectView(Context context) {
        this(context, null);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        strokePaint = new Paint();
        strokeWPaint = new Paint();
        roundPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 三种方式画矩形
        paint.setColor(Color.RED);
        // 左上右下
        canvas.drawRect(20, 20, 300, 300, paint);

        paint.setColor(Color.BLUE);
        rect = new Rect(320, 20, 600, 300);
        canvas.drawRect(rect, paint);

        paint.setColor(Color.BLACK);
        rectF = new RectF();
        rectF.left = 620;
        rectF.top = 20;
        rectF.right = 900;
        rectF.bottom = 300;
        canvas.drawRect(rectF, paint);

        // 设置矩形空心
        strokePaint.setColor(Color.RED);
        strokePaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(20, 320, 300, 600, strokePaint);

        // 设置矩形描边宽度
        strokeWPaint.setColor(Color.RED);
        strokeWPaint.setStyle(Paint.Style.STROKE);
        strokeWPaint.setStrokeWidth(20);
        canvas.drawRect(20, 620, 300, 900, strokeWPaint);

        // 绘制圆角矩形
        roundPaint.setColor(Color.BLUE);
        roundPaint.setStyle(Paint.Style.STROKE);
        roundPaint.setStrokeWidth(2);
        canvas.drawRoundRect(320, 320, 600, 600, 50, 50, roundPaint);

        // 绘制圆角矩形
        roundPaint.setColor(Color.BLUE);
        roundPaint.setStyle(Paint.Style.STROKE);
        roundPaint.setStrokeWidth(2);
        canvas.drawRoundRect(320, 620, 600, 900, 50, 20, roundPaint);

    }
}
