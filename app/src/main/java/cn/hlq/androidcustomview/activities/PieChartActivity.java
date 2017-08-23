package cn.hlq.androidcustomview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.hlq.androidcustomview.R;
import cn.hlq.customview.PieChartView;
import cn.hlq.customview.bean.PieCharBean;

public class PieChartActivity extends AppCompatActivity {

    private PieChartView pieChartView;

    private int[] colors={0xFFCCFF00,0xFF6495ED,0xFFE32636,0xFF800000,0xFF808000,0xFFFF8C69,0xFF808080,0xFFE68800,0xFF7CFC00};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        initView();
    }

    /**
     * 模拟获取数据源
     * @return
     */
    private List<PieCharBean> getPieCharData(){
        List<PieCharBean> pieCharList=new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            pieCharList.add(new PieCharBean(i+1,colors[i]));
        }
        return pieCharList;
    }

    private void initView() {
        pieChartView= (PieChartView) findViewById(R.id.id_pie_chart);
        pieChartView.setPieChartData(getPieCharData());
    }
}
