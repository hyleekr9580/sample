package com.samplejoaTest.Activitys;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.samplejoaTest.Fragments.FragmentSale;
import com.samplejoaTest.Fragments.FragmentSample;
import com.samplejoaTest.Interfaces.SampleJoaInterface;
import com.samplejoaTest.R;
import com.samplejoaTest.event.Event;
import com.samplejoaTest.event.EventData;
import com.squareup.otto.Subscribe;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private String TAG = MainActivity.class.getSimpleName();
    private SampleJoaInterface mSampleJoaInterface;
    private TabLayout mTabLayout;
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mViewPager = (ViewPager) findViewById(R.id.pager);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.addTab(mTabLayout.newTab().setText("할인정보"));
        mTabLayout.addTab(mTabLayout.newTab().setText("샘플정보"));
        mTabLayout.setOnTabSelectedListener(this);

        // Adapter
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        // View 에 Adapter 붙이기
        mViewPager.setAdapter(mFragmentAdapter);

//         ViewPager 와 TabLayout연결
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        EventBus.getDefault().register(this);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Event event) {

        if (event instanceof EventData) {
            EventData eventData = (EventData) event;
            Toast.makeText(MainActivity.this, ""+eventData.getName(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // Tab을 선택했을 때 ViewPager 의 페이지를 이동
        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentSale();
                case 1:
                    return new FragmentSample();
                default:
                    return new FragmentSale();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
    // 종료 알림참
    private void setOutAlertMsg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setMessage("앱을 종료 하시 겠습니까?")        // 메세지 설정
                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    // 확인 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    // 취소 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

        builder.show();    // 알림창 띄우기
    }

    // BACK KEY
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            setOutAlertMsg();
        } else if (keycode == KeyEvent.KEYCODE_MENU) {
            openOptionsMenu();
        }
        return true;
    }


}


