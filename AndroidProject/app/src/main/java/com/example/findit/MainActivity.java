package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private class MyTabSpec {
        private ImageView imageView = null;
        private TextView textView = null;
        private int normalImage;
        private int selectImage;
        private Fragment fragment = null;

        private void setSelect(boolean b) {
            if (b) {
                imageView.setImageResource(selectImage);
                textView.setTextColor(
                        Color.parseColor("#1afa29"));
            } else {
                imageView.setImageResource(normalImage);
                textView.setTextColor(
                        Color.parseColor("#707070")
                );
            }
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        public int getNormalImage() {
            return normalImage;
        }

        public void setNormalImage(int normalImage) {
            this.normalImage = normalImage;
        }

        public int getSelectImage() {
            return selectImage;
        }

        public void setSelectImage(int selectImage) {
            this.selectImage = selectImage;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public void setFragment(Fragment fragment) {
            this.fragment = fragment;
        }
    }


    private Map<String, MyTabSpec> map = new HashMap<>();
    private String[] tabStrId = {"广场", "发布", "我的"};
    private Fragment curFragment = null;
    private int tab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.nonetitle);
        setContentView(R.layout.activity_main);


        //初始化tab
        initData();

        setListener();

        changeTab(tabStrId[0]);


    }



    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tab_spec_main_home:
                    tab = 0;

                    changeTab(tabStrId[0]);
                    break;
                case R.id.tab_spec_main_send:
                    tab = 1;

                    changeTab(tabStrId[1]);
                    break;
                case R.id.tab_spec_main_message:
                    Log.e("1","mes");
                    tab = 2;
                    changeTab(tabStrId[2]);
                    break;

            }
        }
    }



    // 根据Tab ID 切换Tab
    private void changeTab(String s) {
        // 1 切换Fragment
        changeFragment(s);

        // 2 切换图标及字体颜色
        changeImage(s);
    }


    private void changeFragment(String s) {
        Log.e("1","mes");
        Fragment fragment = map.get(s).getFragment();
        if (curFragment == fragment) return;

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        if (curFragment != null)
            transaction.hide(curFragment);

        if (!fragment.isAdded()) {

            transaction.replace(R.id.tab_content, fragment);

        }
        // 显示对应Fragment
        transaction.show(fragment);
        curFragment = fragment;

        transaction.commit();
    }


    // 根据Tab ID 切换 Tab显示的图片及字体颜色
    private void changeImage(String s) {
        // 1 所有Tab的图片和字体颜色恢复默认
        for (String key : map.keySet()) {
            map.get(key).setSelect(false);
        }

        // 2 设置选中的Tab的图片和字体颜色
        map.get(s).setSelect(true);
    }

    //初始化监听器
    private void setListener() {

        LinearLayout linearLayouttop1 = findViewById(R.id.tab_spec_main_home);
        LinearLayout linearLayouttop2 = findViewById(R.id.tab_spec_main_send);
        LinearLayout linearLayouttop3 = findViewById(R.id.tab_spec_main_message);

        MyListener listener = new MyListener();
        linearLayouttop1.setOnClickListener(listener);
        linearLayouttop2.setOnClickListener(listener);
        linearLayouttop3.setOnClickListener(listener);

    }
    // 初始化，初始化MyTabSpec对象
    private void initData() {
        // 1 创建MyTabSpec对象
        map.put(tabStrId[0], new MyTabSpec());
        map.put(tabStrId[1], new MyTabSpec());
        map.put(tabStrId[2], new MyTabSpec());


        // 2 设置Fragment
        setFragment();

        // 3 设置ImageView和TextView
        findView();

        // 4 设置图片资源
        setImage();
    }

    // 创建Fragment对象并放入map的MyTabSpec对象中
    private void setFragment() {
        map.get(tabStrId[0]).setFragment(new HomeFragment());
        map.get(tabStrId[1]).setFragment(new SendFragment());
        map.get(tabStrId[2]).setFragment(new MyFragment());

    }

    // 将图片资源放入map的MyTabSpec对象中
    private void setImage() {
//        map.get(tabStrId[0]).setNormalImage(R.drawable.);
//        map.get(tabStrId[0]).setSelectImage(R.drawable.);
        map.get(tabStrId[0]).setNormalImage(R.drawable.cube);
        map.get(tabStrId[0]).setSelectImage(R.drawable.cube);
        map.get(tabStrId[1]).setNormalImage(R.drawable.send);
        map.get(tabStrId[1]).setSelectImage(R.drawable.send);
        map.get(tabStrId[2]).setNormalImage(R.drawable.my);
        map.get(tabStrId[2]).setSelectImage(R.drawable.my);
    }

    // 将ImageView和TextView放入map中的MyTabSpec对象
    private void findView() {
        ImageView ivHome = findViewById(R.id.img_main_home);
        ImageView ivGame = findViewById(R.id.img_main_send);
        ImageView ivTime = findViewById(R.id.img_main_my);

        TextView tvHome = findViewById(R.id.tv_main_home);
        TextView tvGame = findViewById(R.id.tv_main_send);
        TextView tvTime = findViewById(R.id.tv_main_my);


        map.get(tabStrId[0]).setImageView(ivHome);
        map.get(tabStrId[0]).setTextView(tvHome);

        map.get(tabStrId[1]).setImageView(ivGame);
        map.get(tabStrId[1]).setTextView(tvGame);

        map.get(tabStrId[2]).setImageView(ivTime);
        map.get(tabStrId[2]).setTextView(tvTime);



    }

}
