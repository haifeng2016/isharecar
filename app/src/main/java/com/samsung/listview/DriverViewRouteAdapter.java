package com.samsung.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.samsung.data.Route;
import com.samsung.isharecar.Log;
import com.samsung.isharecar.R;

import java.util.List;

public class DriverViewRouteAdapter extends BaseAdapter {

    private Context context;
    private List<Route> driverList;
    Route entry = null;
    private String TAG = "DriverViewRouteAdapter";

    public DriverViewRouteAdapter(Context context, List<Route> driverList) {
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
        entry = driverList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.passenger_view_route_view, null);
        }

        TextView nameUser = (TextView) convertView.findViewById(R.id.namePassenger);
        nameUser.setText(entry.getUserName());
        
        TextView phoneNum = (TextView) convertView.findViewById(R.id.driverPhoneNumber);
        phoneNum.setText(entry.getUserPhone());
        
        TextView goTime = (TextView) convertView.findViewById(R.id.driverGoTime);
        goTime.setText(entry.getTime());
        
        TextView NumPos = (TextView) convertView.findViewById(R.id.driverNumPos);
        NumPos.setText(entry.getNumOfPosition());


        TextView professionUser = (TextView) convertView.findViewById(R.id.homeaddress);
        professionUser.setText(entry.getHomeAddress());

        TextView ageUser = (TextView) convertView.findViewById(R.id.workaddress);
        ageUser.setText(entry.getWorkAddress());
        
        TextView PassWay = (TextView) convertView.findViewById(R.id.passWay);
        PassWay.setText(entry.getPassWay());
        
        TextView cancelButton = (TextView) convertView.findViewById(R.id.cancel);
        if(cancelButton != null){
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "KMA ...click route id:"+(entry.getId()));
                }
            });
        }

        return convertView;
    }
}
