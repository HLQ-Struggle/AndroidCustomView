package cn.hlq.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import cn.hlq.customview.bean.PieCharBean;
import cn.hlq.customview.util.MathUtil;

/**
 * 饼图 Created by HLQ on 2017/8/22
 * 实现步骤如下：
 * 1. 绘制扇形并组合成圆形；
 * 2. 绘制中间短线；
 * 3. 绘制外侧文本
 */
public class PieChartView extends View {

    private Paint mPaint;
    private Paint mLinePaint;
    private Path mPath;

    private int mWidth; // 屏幕宽
    private int mHeight; // 屏幕高
    private int mRadius; // 半径
    private int mPosition; // 被点击的扇形区域对应的位置
    private float mTotalValue; // 总占比

    private List<PieCharBean> mPieCharList; // 数据源

    private float[] mStartAngles; // 起始角度集合
    private RectF mRectF; // 定义扇形的外接矩形
    private RectF mTouchmRectF; // 被触摸扇形的外接矩形

    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true); // 开启抗锯齿

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(3);
        mLinePaint.setTextSize(30);

        mPath = new Path();

        mRectF = new RectF(); // 实例化扇形的外接矩形
        mTouchmRectF = new RectF(); // 实例化被触摸扇形的外接矩形
    }

    /**
     * 当自定义控件的尺寸已经确定好调用
     *
     * @param w    宽度
     * @param h    高度
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        // 防止绘制后View超出屏幕大小，首先获取屏幕宽高的较小值
        int min = Math.min(w, h);// 获取到直径
        mRadius = (int) (min * 0.7f / 2); // 获取半径

        mRectF.left = -mRadius; // 左
        mRectF.top = -mRadius; // 上
        mRectF.right = mRadius; // 右
        mRectF.bottom = mRadius; // 下

        mTouchmRectF.left = -mRadius-15; // 左
        mTouchmRectF.top = -mRadius-15; // 上
        mTouchmRectF.right = mRadius+15; // 右
        mTouchmRectF.bottom = mRadius+15; // 下
    }

    /**
     * 设置数据源
     *
     * @param pieCharList
     */
    public void setPieChartData(List<PieCharBean> pieCharList) {
        this.mPieCharList = pieCharList;
        for(PieCharBean pieCharBean:mPieCharList){
            mTotalValue+=pieCharBean.value;
        }
        mStartAngles=new float[pieCharList.size()];
    }

    /**
     * 绘制扇形
     *
     * @param canvas
     */
    private void drawPieChart(Canvas canvas) {
        float startAngle = 0;
        for (int i = 0; i < mPieCharList.size(); i++) {
            // 移动到0，0点
            mPath.moveTo(0,0);
            PieCharBean pieCharBean=mPieCharList.get(i);
            // 绘制当前扇形区域颜色
            mPaint.setColor(mPieCharList.get(i).color);
            // 默认绘制扇形
            float sweepAngle=pieCharBean.value/mTotalValue*360-1;
            if(i==mPosition){
                mPath.arcTo(mTouchmRectF,startAngle,sweepAngle);
            }else{
                mPath.arcTo(mRectF,startAngle,sweepAngle);
            }
            // 绘制扇形
            canvas.drawPath(mPath,mPaint);
            // 将角度转换为弧度
            double angdeg= Math.toRadians(startAngle+sweepAngle/2);
            float startX= (float) (mRadius*Math.cos(angdeg));
            float startY=(float) (mRadius*Math.sin(angdeg));
            float endX= (float) ((mRadius+30)*Math.cos(angdeg));
            float endY=(float) ((mRadius+30)*Math.sin(angdeg));
            // 绘制线条
            canvas.drawLine(startX,startY,endX,endY,mLinePaint);
            // 存储每次开始角度
            mStartAngles[i]=startAngle;
            // 每个扇形区域的起始点都是上一个扇形区域的终点
            startAngle+=sweepAngle+1;
            // 绘制扇形后 要对Path进行重置操作 这样可以清除上一次画笔的缓存
            mPath.reset();
            // 绘制文本
            String percent=String.format("%.1f",pieCharBean.value/mTotalValue*100);
            percent=percent+"%";
            if (startAngle%360.0f>=90.0f&&startAngle%360.0f<=270.0f){
                float textWidth=mLinePaint.measureText(percent);
                canvas.drawText(percent,endX-textWidth,endY,mLinePaint);
            }else{
                canvas.drawText(percent,endX,endY,mLinePaint);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        // 移动坐标系到屏幕中心点 宽高各取一半
        canvas.translate(mWidth / 2, mHeight / 2);
        drawPieChart(canvas);
        canvas.restore();
    }

    /**
     * 用户与视图交互时触发
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取用户当前对屏幕的操作
        int action=event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                // 获取点击区域
                // 获取用户点击的位置距当前视图的左边缘距离
                float x=event.getX();
                // 获取用户点击的位置距当前视图的上边缘距离
                float y=event.getY();
                // 将点击的xy坐标转化为以饼图为圆心的坐标
                x=x-mWidth/2;
                y=y-mHeight/2;
                // 获取点击的角度
                float touchAngle= MathUtil.getTouchAngle(x,y);
                // 获取触摸的半径
                float toucheRadius= (float) Math.sqrt(x*x+y*y);
                // 判断触摸的店距离饼状图<对应的圆心
                if(toucheRadius<mRadius){ // 标识当前点击区域为有效区域
                    // 查找触摸角度是否位于起始角度集合中
                    // binarySearch:参数2在参数1对应的集合中的索引
                    // {1，2，3}
                    // 搜索1，返回值在集合中对应的索引为0
                    // 未找到 则返回 -(搜索值附近的大于搜索值得正确值的索引值+1)
                    // 搜索1.2 返回-1(1+1) -2
                    int searchResult=Arrays.binarySearch(mStartAngles,touchAngle);
                    // 防止点击边缘位置 增加判断
                    if(searchResult<0){
                        mPosition =-searchResult-1-1;
                    }else{
                        mPosition =searchResult;
                    }
                    // 重绘
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }
}
