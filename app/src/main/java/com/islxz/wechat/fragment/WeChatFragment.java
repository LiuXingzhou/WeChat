package com.islxz.wechat.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.islxz.wechat.R;
import com.islxz.wechat.activity.ChatActivity;
import com.islxz.wechat.entity.Avert;
import com.islxz.wechat.util.DensityUtils;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3/0003.
 */

public class WeChatFragment extends Fragment {

    private SwipeMenuRecyclerView mSwipeMenuRecyclerView;

    private List<Avert> mAvertList;
    private MyAdapter mMyAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_we_chat, null);
        bindID(view);
        initData();
        mSwipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeMenuRecyclerView.setSwipeMenuCreator(swipeMuenCreator);
        mSwipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        mMyAdapter = new MyAdapter();
        mSwipeMenuRecyclerView.setAdapter(mMyAdapter);
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
        mSwipeMenuRecyclerView = (SwipeMenuRecyclerView) view.findViewById(R.id.fg_wc_rl);
    }

    /*
    菜单创建器
     */
    private SwipeMenuCreator swipeMuenCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = DensityUtils.dp2px(getActivity(), 66);

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(getActivity())
                        .setBackgroundDrawable(R.drawable.selector_huise)
                        .setText("标为未读")
                        .setTextColor(Color.WHITE)
                        .setWidth(DensityUtils.dp2px(getActivity(), 100))
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。

                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(DensityUtils.dp2px(getActivity(), 70))
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

            }

        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是
         *                        0、1，
         * @param direction
         * 如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView
         *                        #RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int
                direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。


            // TODO 如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {//标为未读
                Toast.makeText(getActivity(), "标为未读", Toast.LENGTH_SHORT).show();
            } else if (menuPosition == 1) {// 删除按钮被点击。
                mAvertList.remove(adapterPosition);
                mMyAdapter.notifyDataSetChanged();
            }
        }

    };

    /*
    适配器
     */
    public class MyAdapter extends SwipeMenuAdapter<MyAdapter.ViewHolder> {

        @Override
        public View onCreateContentView(ViewGroup parent, int viewType) {
            return LayoutInflater.from(getActivity()).inflate(R.layout.item_we_chat, parent, false);
        }

        @Override
        public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
            ViewHolder viewHolder = new ViewHolder(realContentView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().startActivity(new Intent(getActivity(), ChatActivity.class).putExtra
                            ("id", position));
                }
            });
            holder.setData(mAvertList.get(position));
        }

        @Override
        public int getItemCount() {
            return mAvertList == null ? 0 : mAvertList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout rl;
            ImageView img;
            TextView name;
            TextView news;
            TextView date;
            ImageView disture;

            public ViewHolder(View itemView) {
                super(itemView);
                rl = (RelativeLayout) itemView.findViewById(R.id.item_wc_rl);
                img = (ImageView) itemView.findViewById(R.id.item_wc_img);
                name = (TextView) itemView.findViewById(R.id.item_wc_name);
                news = (TextView) itemView.findViewById(R.id.item_wc_news);
                date = (TextView) itemView.findViewById(R.id.item_wc_date);
                disture = (ImageView) itemView.findViewById(R.id.item_wc_disturb);
            }

            public void setData(Avert avert) {
                img.setImageResource(avert.getImg());
                name.setText(avert.getName());
                news.setText(avert.getNews());
                date.setText(avert.getDate());
                if (avert.getDisturb())
                    disture.setVisibility(View.VISIBLE);
            }
        }
    }
}
