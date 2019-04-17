package com.casic.guideactivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private LinearLayout llPoints;
    private Button btnExperienceNow;

    private int[] imgs = {R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round};

    private int lastPointIndex = 0;
    private Button mGuideActivity_bt_experience_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化 blankj:utilcode 依赖库
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();


    }

    private void initEvent() {


        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                llPoints.getChildAt(lastPointIndex).setEnabled(false);
                //将当前的点选中
                llPoints.getChildAt(i).setEnabled(true);

                if (i==3){
                    btnExperienceNow.setVisibility(View.VISIBLE);
                }else {
                    btnExperienceNow.setVisibility(View.GONE);
                }
                lastPointIndex = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mGuideActivity_bt_experience_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"立即体验被点击了",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initData() {

        ArrayList<ImageView> imageViews = new ArrayList<>();
        ImageView imageView = null;
        View pointView = null;
        LinearLayout.LayoutParams layoutParams = null;
        for (int i = 0; i < imgs.length; i++) {

            //添加图片
            imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgs[i]);

            imageViews.add(imageView);


            //添加指针
            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.point_seleoter);

            //指针的宽度和高度
            layoutParams = new LinearLayout.LayoutParams(ConvertUtils.dp2px(5), ConvertUtils.dp2px(5));

            if (i==0){
                pointView.setEnabled(true);
            } else {
                //指针距离左边的间距
                layoutParams.leftMargin = ConvertUtils.dp2px(5);
                pointView.setEnabled(false);
            }

            llPoints.addView(pointView,layoutParams);

        }
        GuideAdapter guideAdapter = new GuideAdapter(imageViews);
        vp.setAdapter(guideAdapter);
    }

    private void initView() {

        vp = findViewById(R.id.guideActivity_vp);
        llPoints = findViewById(R.id.guideActivity_ll_points);
        btnExperienceNow = findViewById(R.id.guideActivity_bt_experience_now);
        mGuideActivity_bt_experience_now = findViewById(R.id.guideActivity_bt_experience_now);

    }
}
