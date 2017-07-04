package com.islxz.wechat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.islxz.wechat.R;
import com.islxz.wechat.entity.Avert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3/0003.
 */

public class WeChatFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private List<Avert> mAvertList;
    private MyAdapter mMyAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_we_chat, null);
        bindID(view);
        initData();
        mMyAdapter = new MyAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mMyAdapter);
        return view;
    }

    private void initData() {
        mAvertList = new ArrayList<>();

        Avert avert1 = new Avert();
        avert1.setImg(R.drawable.avert_dingyuehao);
        avert1.setName("订阅号");
        avert1.setNews("讲故事|盗墓笔记重启·第二十八章");
        avert1.setDate("昨天");
        avert1.setDisturb(false);
        mAvertList.add(avert1);

        Avert avert2 = new Avert();
        avert2.setImg(R.drawable.avert_yujian);
        avert2.setName("遇见");
        avert2.setNews("[动画表情]");
        avert2.setDate("下午1:05");
        avert2.setDisturb(false);
        mAvertList.add(avert2);

        Avert avert3 = new Avert();
        avert3.setImg(R.drawable.avert_jiajiayue);
        avert3.setName("家家悦");
        avert3.setNews("激情7月疯狂夏日，冰霜低价！");
        avert3.setDate("下午5:14");
        avert3.setDisturb(true);
        mAvertList.add(avert3);

        Avert avert4 = new Avert();
        avert4.setImg(R.drawable.avert_jianhang);
        avert4.setName("中国建设银行");
        avert4.setNews("请回复序号选择您需要的服务：[1]理财产品推荐");
        avert4.setDate("下午12:35");
        avert4.setDisturb(true);
        mAvertList.add(avert4);

        Avert avert5 = new Avert();
        avert5.setImg(R.drawable.avert_ljyikatong);
        avert5.setName("山东劳动职业技术学院一卡通");
        avert5.setNews("尊敬的用户 校园卡余额：26.24元 去充值");
        avert5.setDate("下午5:40");
        avert5.setDisturb(false);
        mAvertList.add(avert5);
    }

    private void bindID(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fg_wc_rl);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_we_chat, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.img.setImageResource(mAvertList.get(position).getImg());
            holder.name.setText(mAvertList.get(position).getName());
            holder.news.setText(mAvertList.get(position).getNews());
            holder.date.setText(mAvertList.get(position).getDate());
            if (!mAvertList.get(position).getDisturb())
                holder.disture.setVisibility(View.INVISIBLE);
        }

        @Override
        public int getItemCount() {
            return mAvertList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView name;
            TextView news;
            TextView date;
            ImageView disture;

            public ViewHolder(View itemView) {
                super(itemView);
                img = (ImageView) itemView.findViewById(R.id.item_wc_img);
                name = (TextView) itemView.findViewById(R.id.item_wc_name);
                news = (TextView) itemView.findViewById(R.id.item_wc_news);
                date = (TextView) itemView.findViewById(R.id.item_wc_date);
                disture = (ImageView) itemView.findViewById(R.id.item_wc_disturb);
            }
        }
    }
}
