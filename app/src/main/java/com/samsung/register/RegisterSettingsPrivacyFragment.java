package com.samsung.register;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.R;

public class RegisterSettingsPrivacyFragment extends FragmentBase {

    protected ViewGroup view;
    protected WebView webView;
    protected ViewGroup progressLayout;

    public RegisterSettingsPrivacyFragment() {
        //Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof RegisterSettingsActivity) {
            registerSettingsActivity = (RegisterSettingsActivity) activity;
        }
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registersettings_privacy, container,false);
        //设置标题栏
        titleResId = R.string.privacy_note;
        progressLayout = (ViewGroup) view.findViewById(R.id.progressLayout);
        webView = (WebView) view.findViewById(R.id.mywebview);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                webView.setVisibility(View.GONE);
                progressLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressLayout.setVisibility(View.GONE);
                        webView.setVisibility(View.VISIBLE);
                    }
                }, 200);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        webView.loadUrl("file:///android_asset/privacy.html");
        return view;
    }

}
