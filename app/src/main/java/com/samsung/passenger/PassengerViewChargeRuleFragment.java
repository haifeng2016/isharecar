package com.samsung.passenger;

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

public class PassengerViewChargeRuleFragment extends FragmentBase{

    protected ViewGroup view;
    protected WebView webView;
    protected ViewGroup progressLayout;

    public PassengerViewChargeRuleFragment() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.passenger_charge_rule, container, false);
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
        webView.loadUrl("file:///android_asset/chargerule.html");
        return view;
    }

}
