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
    private Paint painter;

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
        // 设置文本大小
        paint.setTextSize(50);

        painter=new Paint();
        painter.setColor(Color.BLUE);
        painter.setTextSize(80);
        // 加粗
        painter.setFakeBoldText(true);
        // 下划线
        painter.setUnderlineText(true);
        // 删除线
        painter.setStrikeThruText(true);
        // x轴缩放1.5倍
        painter.setTextScaleX(1.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制文字
        canvas.drawText("Hello Custom View",100,100,paint);

        // 绘制指定长度文本
        canvas.drawText("Hello,妹子，你好~",6,11,100,200,paint);

        canvas.drawText("春风十里不如你",100,400,painter);
    }
}
