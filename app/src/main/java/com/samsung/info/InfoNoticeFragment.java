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

public class InfoNoticeFragment extends FragmentBase {

    private MainActivity mainActivity;
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
        View view = inflater.inflate(R.layout.fragment_infonotice, container,false);
        lv = (MyListView) view.findViewById(R.id.infonoticelistView);

        mainActivity.childViewActionBarStyle(R.string.notice);

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> mapSerch = new HashMap<String, Object>();
        mapSerch.put("infonoticeTextView", getString(R.string.infonoticetext1));
        mapSerch.put("infonoticesubTextView", getString(R.string.infonoticesubtext1));
        listItem.add(mapSerch);

        HashMap<String, Object> mapSave = new HashMap<String, Object>();
        mapSave.put("infonoticeTextView", getString(R.string.infonoticetext2));
        mapSave.put("infonoticesubTextView", getString(R.string.infonoticesubtext1));
        listItem.add(mapSave);

        HashMap<String, Object> mapAbout = new HashMap<String, Object>();
        mapAbout.put("infonoticeTextView", getString(R.string.infonoticetext3));
        mapAbout.put("infonoticesubTextView", getString(R.string.infonoticesubtext1));
        listItem.add(mapAbout);
   
        simpleAdapter = new SimpleAdapter(mainActivity, listItem,
                R.layout.listitem_infonotice,
                new String[]{
                    "infonoticeTextView",
                    "infonoticesubTextView"
                },
                new int[]{
                    R.id.infonoticeTextView,
                    R.id.infonoticesubTextView
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
