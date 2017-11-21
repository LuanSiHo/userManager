package com.hosiluan.usermanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hosiluan.usermanager.R;
import com.hosiluan.usermanager.model.User;

import java.util.ArrayList;

/**
 * Created by Ho Si Luan on 10/29/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<User> mUserList;
    private AdapterListener adapterListener;


    public RecyclerViewAdapter(Context mContext, ArrayList<User> mUserList,
                               AdapterListener adapterListener) {
        this.mContext = mContext;
        this.mUserList = mUserList;
        this.adapterListener = adapterListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = mUserList.get(position);

        holder.usernameTextView.setText(user.getmLastName() + " " + user.getmFirstName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onItemClickEvent(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView usernameTextView, deleteTextView;
        RelativeLayout viewBackground, viewForeground;

        public ViewHolder(View itemView) {
            super(itemView);
            usernameTextView = (TextView) itemView.findViewById(R.id.tv_user_name);
            viewForeground = (RelativeLayout) itemView.findViewById(R.id.view_foreground);

        }
    }

    interface AdapterListener{
        void onItemClickEvent(User user);
    }
}
