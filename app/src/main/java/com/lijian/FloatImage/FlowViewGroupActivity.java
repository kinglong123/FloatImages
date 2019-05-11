package com.lijian.FloatImage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lanjl on 2019/5/11.
 */
public class FlowViewGroupActivity extends AppCompatActivity {
    private FlowViewGroup mFlowViewGroup;
    private String[] mTexts = new String[]{"BatteryView.txt", "为自定义View",
            " 参考attrs.xml", " 定义自定义View属性", " 参考fragment_04.xml",
            " 使用自定义view，并传入属性值", " 两张图片为资源", "一张为view背景（白圈）",
            "一张为一个圆形图片", "用于遮盖XFerMode","形成圆形波浪效果"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.flowlayout);

        initView();
        initDatas();
    }

    private void initView() {
        mFlowViewGroup = (FlowViewGroup) findViewById(R.id.flowlayout);
    }
    private void initDatas() {
        TextView tv;
        for (int i = 0; i < mTexts.length; i++) {
            tv = (TextView) LayoutInflater.from(this)
                    .inflate(R.layout.item_flow, mFlowViewGroup, false);

//            tv =new TextView(getApplicationContext());
            tv.setText(mTexts[i]);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FlowViewGroupActivity.this, "" + ((TextView) v).getText(),
                            Toast.LENGTH_SHORT).show();
                }
            });
            mFlowViewGroup.addView(tv);
        }
    }

}
