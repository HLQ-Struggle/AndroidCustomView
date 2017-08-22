package cn.hlq.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制文字 Created by HLQ on 2017/8/22
 */

public class TextView extends View {

    private Paint paint;

    public TextView(Context context) {
        this(context,null);
    }

    public TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint=new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 设置文本大小
        paint.setTextSize(50);
        // 绘制文字
        canvas.drawText("Hello Custom View",100,100,paint);

        // 绘制指定长度文本
        canvas.drawText("Hello,妹子，你好~",6,11,100,200,paint);


    }
}
