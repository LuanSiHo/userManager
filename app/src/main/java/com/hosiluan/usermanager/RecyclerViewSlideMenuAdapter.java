package com.hosiluan.usermanager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 10/31/2017.
 */

public class RecyclerViewSlideMenuAdapter extends RecyclerView.Adapter<RecyclerViewSlideMenuAdapter.MyViewHolder> {

    private ArrayList<String> mMenuList;
    private Context mContext;
    private SlideMenuAdapterListener mSlideMenuAdapterListener;
    private int mSelectedPosition = 0;

    public RecyclerViewSlideMenuAdapter(ArrayList<String> mMenuList, Context mContext,
                                        SlideMenuAdapterListener mSlideMenuAdapterListener) {
        this.mMenuList = mMenuList;
        this.mContext = mContext;
        this.mSlideMenuAdapterListener = mSlideMenuAdapterListener;
    }

    public void setDefaultSelectedPosition(){
        mSelectedPosition = 0;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.slide_menu_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        TypedArray menuImageArray = mContext.getResources().obtainTypedArray(R.array.menu_image);
        holder.menuItemTextView.setText(mMenuList.get(position));
        holder.menuItemImageView.setImageResource(menuImageArray.getResourceId(position, -1));

        if (position == mSelectedPosition) {
            holder.menuItemTextView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }else {
            holder.menuItemTextView.setTextColor(Color.BLACK);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideMenuAdapterListener.onItemClick(position);
                mSelectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView menuItemTextView;
        ImageView menuItemImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            menuItemImageView = itemView.findViewById(R.id.img_menu_item);
            menuItemTextView = itemView.findViewById(R.id.tv_menu_item);
        }
    }

    public interface SlideMenuAdapterListener {
        void onItemClick(int position);
    }
}
