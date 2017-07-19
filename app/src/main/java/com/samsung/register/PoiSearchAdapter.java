package com.samsung.register;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.utils.DistanceUtil;
import com.samsung.isharecar.R;

import java.util.List;

public class PoiSearchAdapter extends BaseAdapter {

    private Context context;
    private List<PoiInfo> poiInfos;
    private final LatLng locationLatLng;

    public PoiSearchAdapter(Context context, List<PoiInfo> poiInfos, LatLng locationLatLng) {
        this.context = context;
        this.poiInfos = poiInfos;
        this.locationLatLng = locationLatLng;
    }

    @Override
    public int getCount() {
        if(poiInfos == null) 
            return 0;
        else
            return poiInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return poiInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_poisearch_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
//          if (position == 0 && linearLayout.getChildCount() < 2) {
//                ImageView imageView = new ImageView(context);
//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(32, 32);
//                imageView.setLayoutParams(params);
//                imageView.setBackgroundColor(Color.TRANSPARENT);
//                //imageView.setImageResource(R.mipmap.baidumap_ico_poi_on);
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                linearLayout.addView(imageView, 0, params);
//                holder.poisearch_name.setTextColor(Color.parseColor("#FF5722"));
//            }
          
            PoiInfo poiInfo = poiInfos.get(position);
            holder.poisearch_name.setText(poiInfo.name);
            holder.poisearch_address.setText(poiInfo.address);
               holder.poisearch_distance.setText((int)DistanceUtil.getDistance(locationLatLng, poiInfo.location)+"m");
        return view;
    }

    class ViewHolder {
        TextView poisearch_name;
        TextView poisearch_address;
        TextView poisearch_distance;
        
        public String getName() {
            return (String) poisearch_name.getText();
        }

        public String getAddress() {
            return (String) poisearch_address.getText();
        }
        public String getDistance() {
            return (String) poisearch_distance.getText();
        }

        public ViewHolder(View view) {
            poisearch_name = (TextView) view.findViewById(R.id.poisearch_name);
            poisearch_address = (TextView) view.findViewById(R.id.poisearch_address);
            poisearch_distance = (TextView) view.findViewById(R.id.poisearch_distance);
        }
    }
}
