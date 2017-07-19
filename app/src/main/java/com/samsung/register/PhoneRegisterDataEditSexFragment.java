package com.samsung.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.R;

public class PhoneRegisterDataEditSexFragment extends FragmentBase {

    private Button sexsave;
    private View view;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String sexName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phoneregister_dataedit_sex, container,false);

        //设置标题栏
        dataEditActivity.childViewActionBarStyle(R.string.sex);

//      Bundle sexarguments = getArguments();
//      String hintText = sexarguments.getString("sexHintText");
//      radioGroup.check(radioButton.getId());
        radioGroup = (RadioGroup) view.findViewById(R.id.sex);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override 
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //在这个函数里面用来改变选择的radioButton的数值，以及与其值相关的任何操作，详见下文 
                selectRadioBtn();
            } 
        });
        //getViews();
        setListenerForView();
        sexsave = (Button) view.findViewById(R.id.sexsave);
        sexsave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView sexinfo = (TextView) dataEditActivity.findViewById(R.id.sexinfo);
                if (sexinfo != null) {
                    sexinfo.setText(sexName);
                    dataEditActivity.contact.setSex(sexName);
                    dataEditActivity.mBaseData.updateUser(dataEditActivity,dataEditActivity.contact);
                }
                dataEditActivity.doPopFragmentAction();
            }
        });
        return view;
    }

    @Override
    public void onBackPressed() {
        dataEditActivity.doPopFragmentAction();
    }

    private void setListenerForView(){
        selectRadioBtn();
    }

    private void selectRadioBtn(){
        radioButton = (RadioButton)view.findViewById(radioGroup.getCheckedRadioButtonId());
        sexName = radioButton.getText().toString();
    }

}