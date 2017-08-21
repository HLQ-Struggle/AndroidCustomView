package cn.hlq.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HLQ on 2017/8/18
 * 来个小目标，画些圆玩玩
 * 步骤如下：
 * 1.继承View，实现构造；
 * 2.为了我们调用一个的同时可以调用其它构造，稍微修改下，super改为this；
 * 3.添加初始化画笔方法，必需；
 * 4.实现onDraw()方法。
 */
public class CircleView extends View {

    private Paint paint; // 画笔
    private Paint antiAliasPaint; // 抗锯齿画笔
    private Paint strokePaint; // 空心圆画笔
    private Paint strokeWPaint; // 空心圆宽度画笔

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 画一个普通圆
        // 实例化画笔
        paint = new Paint();
        // 设置画笔颜色
        paint.setColor(Color.RED);

        antiAliasPaint = new Paint();
        // 设置画笔颜色
        antiAliasPaint.setColor(Color.RED);
        // 开启抗锯齿
        antiAliasPaint.setAntiAlias(true);

        // 来一个空心圆
        strokePaint = new Paint();
        strokePaint.setColor(Color.BLUE);
        strokePaint.setAntiAlias(true);
        // 设置空心圆
        strokePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        // 设置空心圆边框宽度
        strokeWPaint = new Paint();
        strokeWPaint.setColor(Color.BLUE);
        strokeWPaint.setAntiAlias(true);
        strokeWPaint.setStyle(Paint.Style.STROKE);
        // 设置边框宽度
        strokeWPaint.setStrokeWidth(30);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 参数详解：
        // float cx：x轴距离
        // float cy：y轴距离
        // float radius:半径
        // Paint paint:画笔
        canvas.drawCircle(0, 0, 100, paint);

        // 指定x，y均为300
        canvas.drawCircle(300, 300, 100, antiAliasPaint);

        // 指定绘制空心圆
        canvas.drawCircle(700, 300, 100, strokePaint);

        // 指定绘制空心圆并设置边框线宽度
        canvas.drawCircle(700, 700, 100, strokeWPaint);

    }

}
