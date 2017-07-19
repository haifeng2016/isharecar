package com.samsung.register;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.samsung.isharecar.R;

import java.util.List;

public class PoiAdapter extends BaseAdapter {

    private Context context;
    private List<PoiInfo> pois;
    private LinearLayout linearLayout;

    PoiAdapter(Context context, List<PoiInfo> pois) {
        this.context = context;
        this.pois = pois;
    }

    @Override
    public int getCount() {
        return pois.size();
    }

    @Override
    public Object getItem(int position) {
        return pois.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_locationpois, parent, false);
            linearLayout = (LinearLayout) view.findViewById(R.id.locationpois_linearlayout);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (position == 0 && linearLayout.getChildCount() < 2) {
            ImageView imageView = new ImageView(context);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(32, 32);
            imageView.setLayoutParams(params);
            imageView.setBackgroundColor(Color.TRANSPARENT);
            imageView.setImageResource(R.drawable.baidumap_ico_poi_on);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            linearLayout.addView(imageView, 0, params);
            holder.locationpoi_name.setTextColor(Color.parseColor("#FF5722"));
        }
        PoiInfo poiInfo = pois.get(position);
        holder.locationpoi_name.setText(poiInfo.name);
        holder.locationpoi_address.setText(poiInfo.address);
        return view;
    }

    class ViewHolder {
        TextView locationpoi_name;
        TextView locationpoi_address;

        public String getName() {
            return (String) locationpoi_name.getText();
        }

        public String getAddress() {
            return (String) locationpoi_address.getText();
        }

        ViewHolder(View view) {
            locationpoi_name = (TextView) view.findViewById(R.id.locationpois_name);
            locationpoi_address = (TextView) view.findViewById(R.id.locationpois_address);
        }
    }

}
