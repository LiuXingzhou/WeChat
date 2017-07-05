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

import com.bumptech.glide.Glide;
import com.islxz.wechat.IndexBar.widget.IndexBar;
import com.islxz.wechat.R;
import com.islxz.wechat.decoration.DividerItemDecoration;
import com.islxz.wechat.decoration.TitleItemDecoration;
import com.islxz.wechat.entity.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3/0003.
 */

public class ContactsFragment extends Fragment {

    private static final String TAG = "zxt";
    private RecyclerView mRv;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mManager;
    private List<Contact> mContacts;

    private TitleItemDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, null);

        mRv = (RecyclerView) view.findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(getActivity()));
        //initDatas();
        initDatas(getResources().getStringArray(R.array.contacts));
        //mDatas = new ArrayList<>();//测试为空或者null的情况 已经通过 2016 09 08

        mRv.setAdapter(mAdapter = new MyAdapter());
        mRv.addItemDecoration(mDecoration = new TitleItemDecoration(getActivity(), mContacts));
        //如果add两个，那么按照先后顺序，依次渲染。
        //mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration
                .VERTICAL_LIST));


        //使用indexBar
        mTvSideBarHint = (TextView) view.findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) view.findViewById(R.id.indexBar);//IndexBar
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setmSourceDatas(mContacts);//设置数据源

        return view;
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(String[] data) {
        mContacts = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            Contact contact = new Contact();
            contact.setImg(pics[i]);
            contact.setName(data[i]);//设置城市名称
            mContacts.add(contact);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_coptact,
                    parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Glide.with(getContext()).load("").placeholder(mContacts.get(position).getImg()).into
                    (holder.img);
            holder.nameTv.setText(mContacts.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        @Override
        public int getItemCount() {
            return mContacts == null ? 0 : mContacts.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView nameTv;
            ImageView img;

            public ViewHolder(View itemView) {
                super(itemView);
                img = (ImageView) itemView.findViewById(R.id.item_contact_img);
                nameTv = (TextView) itemView.findViewById(R.id.item_contact_name);
            }
        }
    }

    private int[] pics = new int[]{R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable
            .avert, R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.avert, R.drawable
            .img_8, R.drawable.img_9, R.drawable.img_10, R.drawable.avert, R.drawable.avert_self, R
            .drawable.img_12, R.drawable.img_13, R.drawable.img_14, R.drawable.img_15, R.drawable
            .img_16, R.drawable.img_17, R.drawable.img_18, R.drawable.img_19, R.drawable.img_20, R
            .drawable.img_21, R.drawable.img_22, R.drawable.img_23, R.drawable.img_24, R.drawable
            .img_25, R.drawable.img_26, R.drawable.img_27, R.drawable.img_28, R.drawable.img_29, R
            .drawable.img_30, R.drawable.img_31, R.drawable.img_32, R.drawable.img_33, R.drawable
            .img_34, R.drawable.img_35, R.drawable.img_36, R.drawable.img_37, R.drawable.img_38, R
            .drawable.img_39, R.drawable.img_40, R.drawable.img_41, R.drawable.img_42, R.drawable
            .img_43, R.drawable.img_44, R.drawable.img_45, R.drawable.img_46, R.drawable.img_47, R
            .drawable.img_48, R.drawable.img_49, R.drawable.img_50, R.drawable.img_51, R.drawable
            .img_52, R.drawable.img_53, R.drawable.img_54, R.drawable.img_55, R.drawable.img_56, R
            .drawable.img_57, R.drawable.img_58, R.drawable.img_59, R.drawable.img_60, R.drawable
            .img_61, R.drawable.img_62, R.drawable.img_63, R.drawable.img_64, R.drawable.img_65, R
            .drawable.img_66, R.drawable.img_67, R.drawable.img_68, R.drawable.img_69, R.drawable
            .img_70, R.drawable.img_71, R.drawable.img_72, R.drawable.img_73, R.drawable.img_74, R
            .drawable.img_75, R.drawable.img_76, R.drawable.img_77, R.drawable.img_78, R.drawable
            .img_79};

}
