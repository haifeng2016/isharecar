package com.samsung.register;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.R;

import java.util.ArrayList;

public class PhoneRegisterDataEditJobFragment extends FragmentBase implements View.OnClickListener{

    protected final AdapterView.OnItemClickListener jobListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String title = carrerList.get(position);
            TextView jobTextView = (TextView) dataEditActivity.findViewById(R.id.jobinfo);
            if (jobTextView != null && title != null) {
                jobTextView.setText(title);
                
                //update the occupation
                dataEditActivity.contact.setOccupation(title);
                dataEditActivity.mBaseData.updateUser(dataEditActivity,dataEditActivity.contact);
            }
            dataEditActivity.doPopFragmentAction();
        }
    };

    private ListView listView;
    private View view;
    private ArrayList<String> carrerList;
    protected ListAdapter listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phoneregister_dataedit_job, container,false);

        //设置标题栏
        dataEditActivity.childViewActionBarStyle(R.string.job);
        
        carrerList = new ArrayList<String>();
        carrerList.clear();
        carrerList.add(getString(R.string.job_it));
        carrerList.add(getString(R.string.job_communication));
        carrerList.add(getString(R.string.job_build));
        carrerList.add(getString(R.string.job_finance));
        carrerList.add(getString(R.string.job_goods));
        carrerList.add(getString(R.string.job_machine));
        carrerList.add(getString(R.string.job_law));
        carrerList.add(getString(R.string.job_outsource));
        carrerList.add(getString(R.string.job_media));
        carrerList.add(getString(R.string.job_trade));
        carrerList.add(getString(R.string.job_biology));
        carrerList.add(getString(R.string.job_chemical));
        carrerList.add(getString(R.string.job_energy));
        carrerList.add(getString(R.string.job_government));
        carrerList.add(getString(R.string.job_student));
        carrerList.add(getString(R.string.job_teacher));
        carrerList.add(getString(R.string.job_others));

        listAdapter = new ListAdapter();
        listView = (ListView) view.findViewById(R.id.joblistView);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(jobListener);
        return view;
    }

    protected class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            int count = 0;
            if (carrerList != null) {
                count = carrerList.size();
            }
            return count;
        }

        @Override
        public Object getItem(int position) {
            Object item = null;
            if (carrerList != null) {
                item = carrerList.get(position);
            }
            return item;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View view = convertView;
            if (view == null) {
                LayoutInflater listItemLayoutInflater = (LayoutInflater) dataEditActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = listItemLayoutInflater.inflate(R.layout.listitem_phoneregister_dataedit_job, listView, false);
                viewHolder = new ViewHolder();
                viewHolder.titleTextView = (TextView) view.findViewById(R.id.job);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            String title = carrerList.get(position);
            viewHolder.titleTextView.setText(title);
            return view;
        }

        protected class ViewHolder {
            protected TextView titleTextView;
        }
    }

    @Override
    public void onClick(View v) {
    }
}