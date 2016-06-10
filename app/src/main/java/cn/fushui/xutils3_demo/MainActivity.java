package cn.fushui.xutils3_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.fushui.xutils3_demo.fragment.DBFragment;
import cn.fushui.xutils3_demo.fragment.HttpFragment;
import cn.fushui.xutils3_demo.fragment.ImageFragment;
import cn.fushui.xutils3_demo.fragment.InjectFragment;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.id_main_viewPager)
    ViewPager viewPager;

    List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //initData();

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    private void initData() {
        fragmentList = new ArrayList<Fragment>();

        for (int i = 0; i < 4; i++) {
            InjectFragment firstFragment = new InjectFragment();

            fragmentList.add(firstFragment);
        }

    }


    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "注解模块";
                case 1:
                    return "网络模块";
                case 2:
                    return "图片模块";
                case 3:
                    return "数据库模块";
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new InjectFragment();

                case 1:

                    return new HttpFragment();
                case 2:

                    return new ImageFragment();
                case 3:

                    return new DBFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
