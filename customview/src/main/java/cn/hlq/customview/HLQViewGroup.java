package cn.hlq.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 包含绘制 线 扇形 弧形 Created by HLQ on 2017/8/21
 */

public class HLQViewGroup extends View {

    private Paint paint;

    // 多个坐标集合 一组四个坐标 分别为起始x轴，起始y轴，结束x轴，结束y轴
    private float[] pts = {
            100, 200, 100, 600,
            100, 400, 300, 400,
            300, 200, 300, 600};

    private float[] pts1 = {
            400, 100, 400, 200,
            400,100,600,100,
            400,200,600,200};

    private RectF rectF;

    public HLQViewGroup(Context context) {
        this(context, null);
    }

    public HLQViewGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HLQViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画单条线
        canvas.drawLine(100, 50, 500, 50, paint);

        // 绘制几条线
        canvas.drawLines(pts, paint);

        // 参数说明
        // pts：绘制直线的端点数组，每条直线占用4个数据。
        // offset：跳过的数据个数，这些数据将不参与绘制过程。
        // count：实际参与绘制的数据个数。
        // paint：绘制直线所使用的画笔。
        canvas.drawLines(pts1,0,12,paint);

        // 绘制弧线 false为弧线 true为扇形
        rectF=new RectF();
        rectF.left=20;
        rectF.top=600;
        rectF.right=220;
        rectF.bottom=800;
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,0,90,false,paint);

        rectF=new RectF();
        rectF.left=20;
        rectF.top=800;
        rectF.right=220;
        rectF.bottom=1000;
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,0,90,true,paint);

        // 绘制点
        canvas.drawPoint(600,500,paint);

    }
}
