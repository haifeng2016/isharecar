package com.samsung.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.samsung.data.Route;
import com.samsung.isharecar.R;

import java.util.List;

public class PassengerViewRouteAdapter extends BaseAdapter{

    private Context context;
    private List<Route> driverList;

    public PassengerViewRouteAdapter(Context context, List<Route> driverList) {
        this.context = context;
        this.driverList = driverList;
    }
    
    public int getCount() {
        return driverList.size();
    }

    public Object getItem(int position) {
        return driverList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Route entry = driverList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.passenger_view_route_passenger, null);
        }
        
        TextView goTime = (TextView) convertView.findViewById(R.id.driverGoTime);
        goTime.setText(entry.getTime());

        TextView professionUser = (TextView) convertView.findViewById(R.id.homeaddress);
        professionUser.setText(entry.getHomeAddress());

        TextView ageUser = (TextView) convertView.findViewById(R.id.workaddress);
        ageUser.setText(entry.getWorkAddress());

        return convertView;
    }
    
    public void onClick(){
        
    }
    
}
