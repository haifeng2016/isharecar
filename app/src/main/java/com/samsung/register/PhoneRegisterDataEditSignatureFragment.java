package com.samsung.register;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.R;

public class PhoneRegisterDataEditSignatureFragment extends FragmentBase {

    private Button signaturesave;
    private View view;
    private String signature;
    private EditText editText;
    //定义FragmentManager对象
    FragmentManager fManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phoneregister_dataedit_signature, container,false);

        //设置标题栏
        dataEditActivity.childViewActionBarStyle(R.string.signature);

        Bundle signaturearguments = getArguments();
        String hintText = signaturearguments.getString("signatureHintText");
        editText = (EditText) view.findViewById(R.id.signature);
        editText.setText(hintText);
        editText.setSelection(hintText.length());
        signaturesave = (Button) view.findViewById(R.id.signaturesave);
        signaturesave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signature = editText.getText().toString();
                TextView signatureTextView = (TextView) dataEditActivity.findViewById(R.id.signatureinfo);
                if (signatureTextView != null) {
                    signatureTextView.setText(signature);
                    //update the signature
                    dataEditActivity.contact.setSignature(signature);
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

}