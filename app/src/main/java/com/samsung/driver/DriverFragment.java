package com.samsung.driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

    public class DriverFragment extends Fragment {

    private static View view;
    public MainActivity mainActivity;

    private MainMoudleGridView mainMoudleGridView = null;
    public static final int GRID_VIEW_DRIVER_GO = 1;
    public static final int GRID_VIEW_DRIVER_OFF = 2;
    public static final int GRID_VIEW_OTHER_ORDERS= 3;
    public static final int GRID_VIEW_ROUTE_HISTORY = 4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static final DriverFragment newInstance(String sampleText) {
        DriverFragment f = new DriverFragment();
        Bundle b = new Bundle();
        b.putString("bString", sampleText);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.driver_page,container, false);
        mainActivity = (MainActivity) getActivity();
        mainMoudleGridView = (MainMoudleGridView) view.findViewById(R.id.gridview);

        if (mainMoudleGridView != null) {
            mainMoudleGridView.setAdapter(new MainMoudleGridViewAdapter(this.getActivity()));
        }
        mainMoudleGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                switch (position + 1) {
                case GRID_VIEW_DRIVER_GO:
                    Intent intent = new Intent(mainActivity, DriverGoWorkRegisterActivity.class);
                    intent.putExtra("type", DriverGoWorkRegisterActivity.GoWork);/*1: go work */
                    startActivity(intent);
                    break;
                case GRID_VIEW_DRIVER_OFF:
                    Intent intent1 = new Intent(mainActivity, DriverGoWorkRegisterActivity.class);
                    intent1.putExtra("type", DriverGoWorkRegisterActivity.OffWork);/*2: off work*/
                    startActivity(intent1);
                    break;
                case GRID_VIEW_OTHER_ORDERS:
                    Intent intent2 = new Intent(mainActivity, DriverGoWorkRegisterActivity.class);
                    intent2.putExtra("type", DriverGoWorkRegisterActivity.OtherOrder);/* 3.other orders*/
                    startActivity(intent2);
                    break;
                case GRID_VIEW_ROUTE_HISTORY:
                    Intent intent3 = new Intent(mainActivity, DriverGoWorkRegisterActivity.class);
                    intent3.putExtra("type", DriverGoWorkRegisterActivity.ViewDriver);/*4: view driver histroy*/
                    startActivity(intent3);
                    break;
                default:
                    break;
                }
            }
        });
        return view;
    }
}