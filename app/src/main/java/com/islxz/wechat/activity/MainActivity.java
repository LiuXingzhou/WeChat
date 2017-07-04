package com.islxz.wechat.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.islxz.wechat.R;
import com.islxz.wechat.fragment.ContactsFragment;
import com.islxz.wechat.fragment.DiscoverFragment;
import com.islxz.wechat.fragment.MeFragment;
import com.islxz.wechat.fragment.WeChatFragment;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTitleTv;
    private ImageButton mAbIBtn;
    private FrameLayout mFrameLayout;
    private LinearLayout mT1;
    private ImageView mT1Img;
    private TextView mT1Tv;
    private LinearLayout mT2;
    private ImageView mT2Img;
    private TextView mT2Tv;
    private LinearLayout mT3;
    private ImageView mT3Img;
    private TextView mT3Tv;
    private LinearLayout mT4;
    private ImageView mT4Img;
    private TextView mT4Tv;

    private WeChatFragment mWeChatFragment;
    private ContactsFragment mContactsFragment;
    private DiscoverFragment mDiscoverFragment;
    private MeFragment mMeFragment;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private int item = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindID();
        mFragmentManager = getSupportFragmentManager();
        checkFragment(1);
    }

    private void checkFragment(int arg0) {
        item = arg0;
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mT1Img.setImageResource(R.drawable.icon_wechat);
        mT1Tv.setTextColor(ContextCompat.getColor(this, R.color.hint_text));
        mT2Img.setImageResource(R.drawable.icon_contacts);
        mT2Tv.setTextColor(ContextCompat.getColor(this, R.color.hint_text));
        mT3Img.setImageResource(R.drawable.icon_discover);
        mT3Tv.setTextColor(ContextCompat.getColor(this, R.color.hint_text));
        mT4Img.setImageResource(R.drawable.icon_me);
        mT4Tv.setTextColor(ContextCompat.getColor(this, R.color.hint_text));
        switch (arg0) {
            case 1:
                mAbIBtn.setVisibility(View.VISIBLE);
                mTitleTv.setText("微信");
                mAbIBtn.setImageResource(R.drawable.add);
                mT1Img.setImageResource(R.drawable.icon_wechat_select);
                mT1Tv.setTextColor(ContextCompat.getColor(this, R.color.green));
                if (mWeChatFragment == null)
                    mWeChatFragment = new WeChatFragment();
                mFragmentTransaction.replace(R.id.main_fl, mWeChatFragment);
                break;
            case 2:
                mAbIBtn.setVisibility(View.VISIBLE);
                mTitleTv.setText("通讯录");
                mAbIBtn.setImageResource(R.drawable.add_contacts);
                mT2Img.setImageResource(R.drawable.icon_contacts_select);
                mT2Tv.setTextColor(ContextCompat.getColor(this, R.color.green));
                if (mContactsFragment == null)
                    mContactsFragment = new ContactsFragment();
                mFragmentTransaction.replace(R.id.main_fl, mContactsFragment);
                break;
            case 3:
                mAbIBtn.setVisibility(View.INVISIBLE);
                mTitleTv.setText("发现");
                mT3Img.setImageResource(R.drawable.icon_discover_select);
                mT3Tv.setTextColor(ContextCompat.getColor(this, R.color.green));
                if (mDiscoverFragment == null)
                    mDiscoverFragment = new DiscoverFragment();
                mFragmentTransaction.replace(R.id.main_fl, mDiscoverFragment);
                break;
            case 4:
                mAbIBtn.setVisibility(View.INVISIBLE);
                mTitleTv.setText("我");
                mT4Img.setImageResource(R.drawable.icon_me_select);
                mT4Tv.setTextColor(ContextCompat.getColor(this, R.color.green));
                if (mMeFragment == null)
                    mMeFragment = new MeFragment();
                mFragmentTransaction.replace(R.id.main_fl, mMeFragment);
                break;
        }
        mFragmentTransaction.commit();
    }

    private void bindID() {
        mTitleTv = (TextView) findViewById(R.id.main_ab_tv);
        mAbIBtn = (ImageButton) findViewById(R.id.main_ab_ibtn);
        mAbIBtn.setOnClickListener(this);
        mFrameLayout = (FrameLayout) findViewById(R.id.main_fl);
        mT1 = (LinearLayout) findViewById(R.id.main_t1);
        mT1.setOnClickListener(this);
        mT1Img = (ImageView) findViewById(R.id.main_t1_img);
        mT1Tv = (TextView) findViewById(R.id.main_t1_tv);
        mT2 = (LinearLayout) findViewById(R.id.main_t2);
        mT2.setOnClickListener(this);
        mT2Img = (ImageView) findViewById(R.id.main_t2_img);
        mT2Tv = (TextView) findViewById(R.id.main_t2_tv);
        mT3 = (LinearLayout) findViewById(R.id.main_t3);
        mT3.setOnClickListener(this);
        mT3Img = (ImageView) findViewById(R.id.main_t3_img);
        mT3Tv = (TextView) findViewById(R.id.main_t3_tv);
        mT4 = (LinearLayout) findViewById(R.id.main_t4);
        mT4.setOnClickListener(this);
        mT4Img = (ImageView) findViewById(R.id.main_t4_img);
        mT4Tv = (TextView) findViewById(R.id.main_t4_tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_ab_ibtn:
                if (item == 1) {
                    Toast.makeText(this, "更多菜单", Toast.LENGTH_SHORT).show();
                } else if (item == 2) {
                    Toast.makeText(this, "添加联系人", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.main_t1:
                checkFragment(1);
                break;
            case R.id.main_t2:
                checkFragment(2);
                break;
            case R.id.main_t3:
                checkFragment(3);
                break;
            case R.id.main_t4:
                checkFragment(4);
                break;
        }
    }
}
