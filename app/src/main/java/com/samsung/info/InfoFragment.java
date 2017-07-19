package com.samsung.info;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

import java.util.ArrayList;
import java.util.HashMap;

public class InfoFragment extends FragmentBase {

    protected static final int SCANNING_REQUEST_CODE = 0;
    private MainActivity mainActivity;
    private ListView listView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            if (!(activity instanceof MainActivity)) {
                throw new RuntimeException(activity.toString()
                        + " must implement OnFragmentInteractionListener");
            } else {
                mainActivity = (MainActivity)activity;
            }
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container,false);

        listView = (ListView) view.findViewById(R.id.infolistView);

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> mapSerch = new HashMap<String, Object>();
        mapSerch.put("infoTextView", getString(R.string.broadcast));
        mapSerch.put("infoImageView", R.drawable.broadcast);
        mapSerch.put("dayuhao", R.drawable.dayuhao);
        listItem.add(mapSerch);

        HashMap<String, Object> mapSave = new HashMap<String, Object>();
        mapSave.put("infoTextView", getString(R.string.activity));
        mapSave.put("infoImageView", R.drawable.activity);
        mapSave.put("dayuhao", R.drawable.dayuhao);
        listItem.add(mapSave);

        HashMap<String, Object> mapAbout = new HashMap<String, Object>();
        mapAbout.put("infoTextView", getString(R.string.notice));
        mapAbout.put("infoImageView", R.drawable.notice);
        mapAbout.put("dayuhao", R.drawable.dayuhao);
        listItem.add(mapAbout);

        SimpleAdapter simpleAdapter = new SimpleAdapter(mainActivity, listItem,
                R.layout.listitem_info,
                new String[]{
                "infoTextView",
                "infoImageView",
                "dayuhao"
        }, 
        new int[]{
                R.id.infoTextView,
                R.id.infoImageView,
                R.id.dayuhao
        });
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                mainActivity.setTabHomeViewHide(true);
                mainActivity.setChioceItem(MainActivity.info);
                switch (position) {
                case 0:
                    Fragment fragment = new InfoBroadcastFragment();
                    mainActivity.doPushFramgentAction(fragment);
                    break;
                case 1:
                    Fragment activityfragment = new InfoActivityFragment();
                    mainActivity.doPushFramgentAction(activityfragment);
                    break;
                case 2:
                    Fragment noticefragment = new InfoNoticeFragment();
                    mainActivity.doPushFramgentAction(noticefragment);
                    break;
                default:
                    break;
                }
            }
        });

        return view;
    }

}