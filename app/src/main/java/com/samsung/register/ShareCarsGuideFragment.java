package com.samsung.register;

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
import com.samsung.isharecar.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ShareCarsGuideFragment extends FragmentBase {
    
    private ShareCarsGuideActivity shareCarsGuideActivity;
    private ListView listView;

    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof ShareCarsGuideActivity) {
            shareCarsGuideActivity = (ShareCarsGuideActivity) activity;
        }
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sharecarsguide, container,false);
        listView = (ListView) view.findViewById(R.id.driveguidelistView);

        //设置标题栏
        shareCarsGuideActivity.childViewActionBarStyle(R.string.pinche_guide);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> mapRegisterAgreement = new HashMap<String, Object>();
        mapRegisterAgreement.put("sharecarsguideTextView", getString(R.string.register_agreement));
        listItem.add(mapRegisterAgreement);

        HashMap<String, Object> mapCarpoolAgreement = new HashMap<String, Object>();
        mapCarpoolAgreement.put("sharecarsguideTextView", getString(R.string.carpool_agreement));
        listItem.add(mapCarpoolAgreement);

        HashMap<String, Object> mapCarpoolConvention = new HashMap<String, Object>();
        mapCarpoolConvention.put("sharecarsguideTextView", getString(R.string.carpool_convention));
        listItem.add(mapCarpoolConvention);
        
        HashMap<String, Object> mapRawstrategy = new HashMap<String, Object>();
        mapRawstrategy.put("sharecarsguideTextView", getString(R.string.raw_strategy));
        listItem.add(mapRawstrategy);

        HashMap<String, Object> mapIntercityCarpoolGuide = new HashMap<String, Object>();
        mapIntercityCarpoolGuide.put("sharecarsguideTextView", getString(R.string.intercitycarpoolguide));
        listItem.add(mapIntercityCarpoolGuide);

        HashMap<String, Object> mapInsuranceRegulations = new HashMap<String, Object>();
        mapInsuranceRegulations.put("sharecarsguideTextView", getString(R.string.insurance_regulations));
        listItem.add(mapInsuranceRegulations);

        SimpleAdapter simpleAdapter = new SimpleAdapter(shareCarsGuideActivity, listItem,
                R.layout.listitem_sharecarsguide,
                new String[]{
                    "sharecarsguideTextView"
                }, 
                new int[]{
                    R.id.sharecarsguideTextView
                });
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                switch (position) {
                case 0:
                    Fragment agreementfragment = new ShareCarsGuideRegisterAgreementFragment();
                    shareCarsGuideActivity.doPushFramgentAction(agreementfragment);
                    break;
                case 1:
                    Fragment agreementfragment1 = new ShareCarsGuideRegisterAgreementFragment();
                    shareCarsGuideActivity.doPushFramgentAction(agreementfragment1);
                    break;
                case 2:
                    Fragment agreementfragment2 = new ShareCarsGuideRegisterAgreementFragment();
                    shareCarsGuideActivity.doPushFramgentAction(agreementfragment2);
                    break;
                case 3:
                    Fragment agreementfragment3 = new ShareCarsGuideRegisterAgreementFragment();
                    shareCarsGuideActivity.doPushFramgentAction(agreementfragment3);
                    break;
                case 4:
                    Fragment agreementfragment4 = new ShareCarsGuideRegisterAgreementFragment();
                    shareCarsGuideActivity.doPushFramgentAction(agreementfragment4);
                    break;
                case 5:
                    Fragment agreementfragment5 = new ShareCarsGuideRegisterAgreementFragment();
                    shareCarsGuideActivity.doPushFramgentAction(agreementfragment5);
                    break;
                default:
                    break;
                }
            }
        });
        return view;
    }

}
