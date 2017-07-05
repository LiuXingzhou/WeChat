package com.islxz.wechat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.islxz.wechat.R;
import com.islxz.wechat.entity.News;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private int id;
    private int counts = 0;

    private boolean input = true;
    private boolean ll2 = false;

    private ImageButton mBack;
    private TextView mName;
    private ListView mListView;
    private ImageButton mLeftBtn;
    private EditText mInputEdit;
    private Button mHoldTalkBtn;
    private ImageButton mEmojiBtn;
    private ImageButton mMoreBtn;
    private LinearLayout mLL2;

    private List<News> mNewses;
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        id = this.getIntent().getIntExtra("id", -1);
        bindID();
        initData();
        mBaseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mNewses.size();
            }

            @Override
            public Object getItem(int position) {
                return mNewses.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(ChatActivity.this).inflate(R.layout.item_chat, null);
                LinearLayout other = (LinearLayout) view.findViewById(R.id.item_c_other);
                ImageView oImg = (ImageView) view.findViewById(R.id.item_c_other_img);
                TextView oTv = (TextView) view.findViewById(R.id.item_c_other_news);
                RelativeLayout self = (RelativeLayout) view.findViewById(R.id.item_c_self);
                ImageView sImg = (ImageView) view.findViewById(R.id.item_c_self_img);
                TextView sTv = (TextView) view.findViewById(R.id.item_c_self_news);
                if (mNewses.get(position).isSelf()) {
                    self.setVisibility(View.VISIBLE);
                    sImg.setImageResource(mNewses.get(position).getImg());
                    sTv.setText(mNewses.get(position).getNews());
                } else {
                    other.setVisibility(View.VISIBLE);
                    oImg.setImageResource(mNewses.get(position).getImg());
                    oTv.setText(mNewses.get(position).getNews());
                }
                return view;
            }
        };
        mName.setText(mNewses.get(0).getName());
        mListView.setAdapter(mBaseAdapter);
        mInputEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                counts = start + count;
                if (start + count <= 0) {
                    mMoreBtn.setImageResource(R.drawable.chat_more);
                } else if (start + count > 0) {
                    mMoreBtn.setImageResource(R.drawable.icon_send);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initData() {
        mNewses = new ArrayList<>();
        News news1;
        News news2;
        News news3;
        switch (id) {
            case 0:
                news1 = new News(false, "订阅号", R.drawable.avert_dingyuehao, "您好，感谢您关注家家悦集团官方微信");
                news2 = new News(true, "风清明", R.drawable.avert_self, "早啊");
                news3 = new News(false, "订阅号", R.drawable.avert_dingyuehao, "早上好");
                mNewses.add(news1);
                mNewses.add(news2);
                mNewses.add(news3);
                break;
            case 1:
                news1 = new News(false, "遇见、", R.drawable.avert_yujian, "您好，感谢您关注家家悦集团官方微信");
                news2 = new News(true, "风清明", R.drawable.avert_self, "早啊");
                news3 = new News(false, "遇见、", R.drawable.avert_yujian, "早上好");
                mNewses.add(news1);
                mNewses.add(news2);
                mNewses.add(news3);
                break;
            case 2:
                news1 = new News(false, "家家悦", R.drawable.avert_jiajiayue, "您好，感谢您关注家家悦集团官方微信");
                news2 = new News(true, "风清明", R.drawable.avert_self, "早啊");
                news3 = new News(false, "家家悦", R.drawable.avert_jiajiayue, "早上好");
                mNewses.add(news1);
                mNewses.add(news2);
                mNewses.add(news3);
                break;
            case 3:
                news1 = new News(false, "中国建设银行", R.drawable.avert_jianhang, "您好，感谢您关注家家悦集团官方微信");
                news2 = new News(true, "风清明", R.drawable.avert_self, "早啊");
                news3 = new News(false, "中国建设银行", R.drawable.avert_jianhang, "早上好");
                mNewses.add(news1);
                mNewses.add(news2);
                mNewses.add(news3);
                break;
            case 4:
                news1 = new News(false, "山东劳动职业技术学院一卡通", R.drawable.avert_ljyikatong,
                        "尊敬的用户\n校园卡余额：43.74元\n去充值");
                mNewses.add(news1);
                break;
        }
    }

    private void bindID() {
        mBack = (ImageButton) findViewById(R.id.chat_pre);
        mBack.setOnClickListener(this);
        mName = (TextView) findViewById(R.id.chat_name);
        mListView = (ListView) findViewById(R.id.chat_lv);
        mLeftBtn = (ImageButton) findViewById(R.id.chat_tb_left);
        mLeftBtn.setOnClickListener(this);
        mInputEdit = (EditText) findViewById(R.id.chat_tb_input);
        mHoldTalkBtn = (Button) findViewById(R.id.chat_tb_hold_talk);
        mHoldTalkBtn.setOnClickListener(this);
        mEmojiBtn = (ImageButton) findViewById(R.id.chat_tb_emoji);
        mMoreBtn = (ImageButton) findViewById(R.id.chat_tb_more);
        mMoreBtn.setOnClickListener(this);
        mLL2 = (LinearLayout) findViewById(R.id.chat_tb_ll2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_pre:
                finish();
                break;
            case R.id.chat_tb_left:
                checkInput();
                break;
            case R.id.chat_tb_emoji:
                break;
            case R.id.chat_tb_more:
                if (counts > 0) {
                    News news = new News(true, "风清明", R.drawable.avert_self, mInputEdit.getText()
                            .toString());
                    mNewses.add(news);
                    mInputEdit.setText("");
                    mBaseAdapter.notifyDataSetChanged();
                    mListView.smoothScrollToPosition(mNewses.size() - 1);
                } else
                    checkLL2();
                break;
        }
    }

    private void checkInput() {
        if (input) {
            mInputEdit.setVisibility(View.GONE);
            mHoldTalkBtn.setVisibility(View.VISIBLE);
            mLeftBtn.setImageResource(R.drawable.chat_edit);
            input = false;
        } else {
            mHoldTalkBtn.setVisibility(View.GONE);
            mInputEdit.setVisibility(View.VISIBLE);
            mLeftBtn.setImageResource(R.drawable.chat_talk);
            input = true;
        }
    }

    private void checkLL2() {
        if (ll2) {
            mLL2.setVisibility(View.VISIBLE);
            ll2 = false;
        } else {
            mLL2.setVisibility(View.GONE);
            ll2 = true;
        }
    }

}
