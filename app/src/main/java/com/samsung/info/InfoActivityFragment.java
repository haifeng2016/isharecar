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

public class InfoActivityFragment extends FragmentBase {
    
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
        View view = inflater.inflate(R.layout.fragment_infoactivity, container,false);
        lv = (MyListView) view.findViewById(R.id.infoactivitylistView);
        
        mainActivity.childViewActionBarStyle(R.string.activity);
  
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> mapSerch = new HashMap<String, Object>();
        mapSerch.put("infoactivityImageView", R.drawable.infoactivityimageview1);
        mapSerch.put("infoactivityTextView", getString(R.string.infoactivitytextview1));
        listItem.add(mapSerch);

        HashMap<String, Object> mapSave = new HashMap<String, Object>();
        mapSave.put("infoactivityImageView", R.drawable.infoactivityimageview2);
        mapSave.put("infoactivityTextView", getString(R.string.infoactivitytextview2));
        listItem.add(mapSave);

        HashMap<String, Object> mapAbout = new HashMap<String, Object>();
        mapAbout.put("infoactivityImageView", R.drawable.infoactivityimageview3);
        mapAbout.put("infoactivityTextView", getString(R.string.infoactivitytextview3));
        listItem.add(mapAbout);
        
        HashMap<String, Object> mapactivity = new HashMap<String, Object>();
        mapactivity.put("infoactivityImageView", R.drawable.infoactivityimageview4);
        mapactivity.put("infoactivityTextView", getString(R.string.infoactivitytextview4));
        listItem.add(mapactivity);
        
        HashMap<String, Object> mapSerch1 = new HashMap<String, Object>();
        mapSerch1.put("infoactivityImageView", R.drawable.infoactivityimageview5);
        mapSerch1.put("infoactivityTextView", getString(R.string.infoactivitytextview1));
        listItem.add(mapSerch1);

        HashMap<String, Object> mapSave1 = new HashMap<String, Object>();
        mapSave1.put("infoactivityImageView", R.drawable.infoactivityimageview6);
        mapSave1.put("infoactivityTextView", getString(R.string.infoactivitytextview2));
        listItem.add(mapSave1);

        HashMap<String, Object> mapAbout1 = new HashMap<String, Object>();
        mapAbout1.put("infoactivityImageView", R.drawable.infoactivityimageview7);
        mapAbout1.put("infoactivityTextView", getString(R.string.infoactivitytextview3));
        listItem.add(mapAbout1);
        
        HashMap<String, Object> mapactivity1 = new HashMap<String, Object>();
        mapactivity1.put("infoactivityImageView", R.drawable.infoactivityimageview8);
        mapactivity1.put("infoactivityTextView", getString(R.string.infoactivitytextview4));
        listItem.add(mapactivity1);

        simpleAdapter = new SimpleAdapter(mainActivity, listItem,
                R.layout.listitem_infoactivity,
                new String[]{
                    "infoactivityImageView", 
                    "infoactivityTextView"
                }, 
                new int[]{
                    R.id.infoactivityImageView,
                    R.id.infoactivityTextView
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