package com.samsung.driver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsung.isharecar.R;
import com.samsung.passenger.BaseViewHolder;

public class MainMoudleGridViewAdapter extends BaseAdapter {

    private Context mContext;

    public String[] img_text = { "上班接单","下班接单","其他接单","我的订单" };
    public int[] imgs = { R.drawable.gridview1,R.drawable.gridview2,R.drawable.gridview3,R.drawable.gridview4 };

    public MainMoudleGridViewAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return img_text.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.driver_grid_item_layout, parent, false);
        }
        TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
        iv.setBackgroundResource(imgs[position]);
        tv.setText(img_text[position]);
        return convertView;
    }

}
