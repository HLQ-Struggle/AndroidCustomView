package cn.hlq.androidcustomview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.hlq.androidcustomview.activities.CircleActivity;
import cn.hlq.androidcustomview.activities.RectActivity;
import cn.hlq.androidcustomview.activities.ViewGroupActivity;

public class MainActivity extends AppCompatActivity {

    private MainActivity selfActivity = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        // 绘制圆
        findViewById(R.id.btn_circle_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(selfActivity, CircleActivity.class));
            }
        });
        // 绘制矩形
        findViewById(R.id.btn_rect_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(selfActivity, RectActivity.class));
            }
        });
        // 绘制线 扇形 弧度
        findViewById(R.id.btn_hlq_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(selfActivity, ViewGroupActivity.class));
            }
        });
    }
}
