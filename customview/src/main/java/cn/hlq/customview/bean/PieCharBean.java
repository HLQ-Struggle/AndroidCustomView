package cn.hlq.customview.bean;

/**
 * 饼图实体类 Created by HLQ on 2017/8/23
 */
public class PieCharBean {

    public float value; // 绘制扇形区域的占比 个数相加*360°
    public int color; // 绘制扇形区域颜色

    public PieCharBean(float value, int color) {
        this.value = value;
        this.color = color;
    }
}
