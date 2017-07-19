package com.samsung.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.R;

public class PhoneRegisterDataEditNicknameFragment extends FragmentBase {

    private Button nicknamesave;
    private View view;
    private String nickname;
    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phoneregister_dataedit_nickname, container,false);

        //设置标题栏
        dataEditActivity.childViewActionBarStyle(R.string.nickname);

        Bundle arguments = getArguments();
        String hintText = arguments.getString("HintText");
        editText = (EditText) view.findViewById(R.id.nickname);
        editText.setText(hintText);
        editText.setSelection(hintText.length());
        nicknamesave = (Button) view.findViewById(R.id.nicknamesave);
        nicknamesave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = editText.getText().toString();
                TextView nicknameTextView = (TextView) dataEditActivity.findViewById(R.id.nicknameinfo);
                dataEditActivity.contact.set_name(nickname);
                
                //update the nick name
                dataEditActivity.mBaseData.updateUser(dataEditActivity,dataEditActivity.contact);
                if (nicknameTextView != null) {
                    nicknameTextView.setText(nickname);
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
