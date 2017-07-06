package com.islxz.wechat.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.islxz.wechat.R;
import com.islxz.wechat.entity.News;
import com.turing.androidsdk.HttpRequestListener;
import com.turing.androidsdk.TuringManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = MainActivity.class.getSimpleName();

    private TuringManager mTuringManager;

    /**
     * 返回结果，开始说话
     */
    public static final int MSG_SPEECH_START = 0;
    /**
     * 开始识别
     */
    public static final int MSG_RECOGNIZE_RESULT = 1;
    /**
     * 开始识别
     */
    public static final int MSG_RECOGNIZE_START = 2;

    private final String TURING_APIURL = "http://www.tuling123.com/openapi/api/";
    private final String TURING_APIKEY = "4a05b5e04de0487fadeb26b556d97481";
    private final String TURING_SECRET = "ea226f35bc7fdbe2";

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_SPEECH_START:
                    String news = (String) msg.obj;
                    News news1;
                    switch (id) {
                        case 0:
                            news1 = new News(false, "订阅号", R.drawable.avert_dingyuehao,
                                    news);
                            mNewses.add(news1);
                            break;
                        case 1:
                            news1 = new News(false, "遇见、", R.drawable.avert_yujian, news);
                            mNewses.add(news1);
                            break;
                        case 2:
                            news1 = new News(false, "家家悦", R.drawable.avert_jiajiayue, news);
                            mNewses.add(news1);
                            break;
                        case 3:
                            news1 = new News(false, "中国建设银行", R.drawable.avert_jianhang, news);
                            mNewses.add(news1);
                            break;
                        case 4:
                            news1 = new News(false, "山东劳动职业技术学院一卡通", R.drawable.avert_ljyikatong, news);
                            mNewses.add(news1);
                            break;
                    }
                    mBaseAdapter.notifyDataSetChanged();
                    mListView.smoothScrollToPosition(mNewses.size() - 1);
//                    mStatus.setText("开始讲话：" + (String) msg.obj);
//                    mTtsManager.startTTS((String) msg.obj);
                    break;
                case MSG_RECOGNIZE_RESULT:
//                    mStatus.setText("识别结果：" + msg.obj);
                    mTuringManager.requestTuring((String) msg.obj);
                    break;
                case MSG_RECOGNIZE_START:
//                    mStatus.setText("开始识别");
//                    mRecognizerManager.startRecognize();
                    break;
            }
        }

        ;
    };


    private int id;
    private int counts = 0;

    private boolean input = true;
    private boolean ll1 = false;
    private boolean ll2 = false;

    private RelativeLayout mContent;
    private ImageButton mBack;
    private TextView mName;
    private ListView mListView;
    private ImageButton mLeftBtn;
    private EditText mInputEdit;
    private Button mHoldTalkBtn;
    private ImageButton mEmojiBtn;
    private ImageButton mMoreBtn;
    private RelativeLayout mLL1;
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
        mInputEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//获得焦点
                    checkLL(0);
                } else {//失去焦点

                }
            }
        });
        initTuring();
    }

    /**
     * 初始化图灵SDK
     */
    private void initTuring() {
        mTuringManager = new TuringManager(this, TURING_APIKEY, TURING_SECRET);
        mTuringManager.setHttpRequestListener(mHttpRequestListener);
    }

    /**
     * 网络回调请求
     */
    HttpRequestListener mHttpRequestListener = new HttpRequestListener() {
        @Override
        public void onSuccess(String result) {
            if (result != null) {
                try {
                    Log.d(TAG, "result" + result);
                    JSONObject result_obj = new JSONObject(result);
                    if (result_obj.has("text")) {
                        Log.d(TAG, result_obj.get("text").toString());
                        mHandler.obtainMessage(MSG_SPEECH_START,
                                result_obj.get("text")).sendToTarget();
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "JSONException:" + e.getMessage());
                }
            }
        }

        @Override
        public void onFail(int i, String s) {
            Toast.makeText(ChatActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 初始化数据
     */
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
                news1 = new News(false, "遇见、", R.drawable.avert_yujian, "好夢喲");
                news2 = new News(false, "遇见、", R.drawable.avert_yujian, "早呀新兒");
                news3 = new News(true, "风清明", R.drawable.avert_self, "不早了");
                mNewses.add(news1);
                mNewses.add(news2);
                mNewses.add(news3);
                break;
            case 2:
                news1 = new News(false, "家家悦", R.drawable.avert_jiajiayue, "您好，感谢您关注家家悦集团官方微信");
                mNewses.add(news1);
                break;
            case 3:
                news1 = new News(false, "中国建设银行", R.drawable.avert_jianhang, "您好，感谢您关注中国建设银行官方微信");
                mNewses.add(news1);
                break;
            case 4:
                news1 = new News(false, "山东劳动职业技术学院一卡通", R.drawable.avert_ljyikatong,
                        "尊敬的用户\n校园卡余额：43.74元\n去充值");
                mNewses.add(news1);
                break;
        }
    }

    /**
     * 绑定ID
     */
    private void bindID() {
        mContent = (RelativeLayout) findViewById(R.id.chat);
        mBack = (ImageButton) findViewById(R.id.chat_pre);
        mBack.setOnClickListener(this);
        mName = (TextView) findViewById(R.id.chat_name);
        mListView = (ListView) findViewById(R.id.chat_lv);
        mLeftBtn = (ImageButton) findViewById(R.id.chat_tb_left);
        mLeftBtn.setOnClickListener(this);
        mInputEdit = (EditText) findViewById(R.id.chat_tb_input);
        mInputEdit.setOnClickListener(this);
        mHoldTalkBtn = (Button) findViewById(R.id.chat_tb_hold_talk);
        mHoldTalkBtn.setOnClickListener(this);
        mEmojiBtn = (ImageButton) findViewById(R.id.chat_tb_emoji);
        mEmojiBtn.setOnClickListener(this);
        mMoreBtn = (ImageButton) findViewById(R.id.chat_tb_more);
        mMoreBtn.setOnClickListener(this);
        mLL1 = (RelativeLayout) findViewById(R.id.chat_tb_ll1);
        mLL2 = (LinearLayout) findViewById(R.id.chat_tb_ll2);
    }

    /**
     * 监听事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_tb_input:
//                mInputEdit.requestFocus();
//                mInputEdit.findFocus();
                break;
            case R.id.chat_pre:
                finish();
                break;
            case R.id.chat_tb_left:
                checkInput();
                break;
            case R.id.chat_tb_emoji:
                mInputEdit.setFocusable(false);
                checkLL(1);
                mInputEdit.setFocusable(true);
                mInputEdit.setFocusableInTouchMode(true);
                break;
            case R.id.chat_tb_more:
                mInputEdit.setFocusable(false);
                if (counts > 0) {
                    mHandler.obtainMessage(MSG_RECOGNIZE_RESULT, mInputEdit.getText().toString())
                            .sendToTarget();
                    News news = new News(true, "风清明", R.drawable.avert_self, mInputEdit.getText()
                            .toString());
                    mNewses.add(news);
                    mInputEdit.setText("");
                    mBaseAdapter.notifyDataSetChanged();
                    mListView.smoothScrollToPosition(mNewses.size() - 1);
                } else {
                    checkLL(2);
                }
                mInputEdit.setFocusable(true);
                mInputEdit.setFocusableInTouchMode(true);
                break;
        }
    }

    /**
     * 切换文字输入与语音输入
     */
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

    /**
     * 底部弹出框及输入法隐藏显示切换
     *
     * @param arg0
     */
    private void checkLL(int arg0) {
        if (arg0 == 0) {//关闭
            mLL1.setVisibility(View.GONE);
            ll1 = false;
            mLL2.setVisibility(View.GONE);
            ll2 = false;
        } else if (arg0 == 1) {//emoji
            if (ll1) {
                mLL1.setVisibility(View.GONE);
                ll1 = false;
                return;
            }
            if (ll2) {
                if (getSoftInputStatus())
                    hideSofrInput();
                mLL2.setVisibility(View.GONE);
                ll2 = false;
                mLL1.setVisibility(View.VISIBLE);
                ll1 = true;
                return;
            } else {
                if (getSoftInputStatus())
                    hideSofrInput();
                mLL1.setVisibility(View.VISIBLE);
                ll1 = true;
            }
        } else if (arg0 == 2) {//more
            if (ll2) {
                mLL2.setVisibility(View.GONE);
                ll2 = false;
                return;
            }
            if (ll1) {
                if (getSoftInputStatus())
                    hideSofrInput();
                mLL1.setVisibility(View.GONE);
                ll1 = false;
                mLL2.setVisibility(View.VISIBLE);
                ll2 = true;
                return;
            } else {
                if (getSoftInputStatus())
                    hideSofrInput();
                mLL2.setVisibility(View.VISIBLE);
                ll2 = true;
            }
        }
    }

    /**
     * 获取输入法弹出或隐藏状态
     *
     * @return
     */
    private boolean getSoftInputStatus() {
        return ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).isActive();
    }

    /**
     * 隐藏输入法
     */
    private void hideSofrInput() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow
                (this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
