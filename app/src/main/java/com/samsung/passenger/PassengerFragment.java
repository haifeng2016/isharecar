package com.samsung.passenger;

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

    public class PassengerFragment extends Fragment {

    private static View view;
    public MainActivity mMainActivity;

    private MainMoudleGridView mainMoudleGridView = null;
    public static final int GRID_VIEW_PASSENGER_GO = 1;
    public static final int GRID_VIEW_PASSENGER_OFF = 2;
    public static final int GRID_VIEW_OTHER_ORDERS= 3;
    public static final int GRID_VIEW_ROUTE_HISTORY = 4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static final PassengerFragment newInstance(String sampleText) {
        PassengerFragment f = new PassengerFragment();
        Bundle b = new Bundle();
        b.putString("bString", sampleText);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.passenger_page,container, false);
        mMainActivity = (MainActivity) getActivity();

        mainMoudleGridView = (MainMoudleGridView) view.findViewById(R.id.gridview);

        if (mainMoudleGridView != null) {
            mainMoudleGridView.setAdapter(new MainMoudleGridViewAdapter(this.getActivity()));
        }

        mainMoudleGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                switch (position + 1) {
                case GRID_VIEW_PASSENGER_GO:
                    Intent intent = new Intent(mMainActivity, GoWorkRegisterActivity.class);
                    intent.putExtra("type", GoWorkRegisterActivity.GoWork);/*1: go work */
                    startActivity(intent);
                    break;
                case GRID_VIEW_PASSENGER_OFF:
                    Intent intent1 = new Intent(mMainActivity, GoWorkRegisterActivity.class);
                    intent1.putExtra("type", GoWorkRegisterActivity.OffWork);/*2: off work*/
                    startActivity(intent1);
                    break;
                case GRID_VIEW_OTHER_ORDERS:
                    Intent intent2 = new Intent(mMainActivity, GoWorkRegisterActivity.class);
                    intent2.putExtra("type", GoWorkRegisterActivity.OtherOrder);/* 3.other orders*/
                    startActivity(intent2);
                    break;
                case GRID_VIEW_ROUTE_HISTORY:
                    Intent intent3 = new Intent(mMainActivity, GoWorkRegisterActivity.class);
                    intent3.putExtra("type", GoWorkRegisterActivity.ViewDriver);/*4: view driver histroy*/
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
