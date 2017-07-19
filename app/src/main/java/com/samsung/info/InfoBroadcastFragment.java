package com.samsung.info;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.samsung.info.MyListView.OnRefreshListener;
import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

import java.util.ArrayList;
import java.util.HashMap;

public class InfoBroadcastFragment extends FragmentBase {

    private MainActivity mainActivity;
    //private ListView listView;
    private MyListView lv;
    private SimpleAdapter simpleAdapter;

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
        View view = inflater.inflate(R.layout.fragment_infobroadcast, container,false);
        lv = (MyListView) view.findViewById(R.id.infobroadcastlistView);
        
        mainActivity.childViewActionBarStyle(R.string.broadcast);

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> mapSerch = new HashMap<String, Object>();
        mapSerch.put("infobroadcastTextView", getString(R.string.infobroadcasttext1));
        mapSerch.put("infobroadcastsubTextView", getString(R.string.infobroadcastsubtext1));
        listItem.add(mapSerch);

        HashMap<String, Object> mapSave = new HashMap<String, Object>();
        mapSave.put("infobroadcastTextView", getString(R.string.infobroadcasttext2));
        mapSave.put("infobroadcastsubTextView", getString(R.string.infobroadcastsubtext2));
        listItem.add(mapSave);

        HashMap<String, Object> mapAbout = new HashMap<String, Object>();
        mapAbout.put("infobroadcastTextView", getString(R.string.infobroadcasttext3));
        mapAbout.put("infobroadcastsubTextView", getString(R.string.infobroadcastsubtext3));
        listItem.add(mapAbout);

        HashMap<String, Object> mapSerch1 = new HashMap<String, Object>();
        mapSerch1.put("infobroadcastTextView", getString(R.string.infobroadcasttext4));
        mapSerch1.put("infobroadcastsubTextView", getString(R.string.infobroadcastsubtext4));
        listItem.add(mapSerch1);

        HashMap<String, Object> mapSave1 = new HashMap<String, Object>();
        mapSave1.put("infobroadcastTextView", getString(R.string.infobroadcasttext5));
        mapSave1.put("infobroadcastsubTextView", getString(R.string.infobroadcastsubtext5));
        listItem.add(mapSave1);

        HashMap<String, Object> mapAbout1 = new HashMap<String, Object>();
        mapAbout1.put("infobroadcastTextView", getString(R.string.infobroadcasttext6));
        mapAbout1.put("infobroadcastsubTextView", getString(R.string.infobroadcastsubtext6));
        listItem.add(mapAbout1);

        HashMap<String, Object> mapSerch2 = new HashMap<String, Object>();
        mapSerch2.put("infobroadcastTextView", getString(R.string.infobroadcasttext7));
        mapSerch2.put("infobroadcastsubTextView", getString(R.string.infobroadcastsubtext7));
        listItem.add(mapSerch2);

        HashMap<String, Object> mapSave2 = new HashMap<String, Object>();
        mapSave2.put("infobroadcastTextView", getString(R.string.infobroadcasttext8));
        mapSave2.put("infobroadcastsubTextView", getString(R.string.infobroadcastsubtext8));
        listItem.add(mapSave2);

        HashMap<String, Object> mapAbout2 = new HashMap<String, Object>();
        mapAbout2.put("infobroadcastTextView", getString(R.string.infobroadcasttext9));
        mapAbout2.put("infobroadcastsubTextView", getString(R.string.infobroadcastsubtext9));
        listItem.add(mapAbout2);

        simpleAdapter = new SimpleAdapter(mainActivity, listItem,
                R.layout.listitem_infobroadcast,
                new String[]{
                "infobroadcastTextView",
                "infobroadcastsubTextView"
        },
        new int[]{
                R.id.infobroadcastTextView,
                R.id.infobroadcastsubTextView
        });

        lv.setAdapter(simpleAdapter);
        lv.setonRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        simpleAdapter.notifyDataSetChanged();
                        lv.onRefreshComplete();
                    }
                }.execute(null, null, null);
            }
        });

        return view;
    }

}