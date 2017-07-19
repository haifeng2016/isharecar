package com.samsung.isharecar;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MyWebView extends WebView {

    protected static final String _HTML_TEMPLATE = "<html><head><meta name=\"viewport\" content=\"width=device-width;initial-scale=1.0;maximum-scale=1.0;minimum-scale=1.0;user-scalable=no;target-densitydpi=medium-dpi;\"/><style>html,body{margin:0;padding:0;border:0;}body{font-family:SECRobotoLight;line-height:1.5;font-size:16px;background:transparent;color:#313131;word-wrap:break-word;-webkit-text-size-adjust:none;}h1,h2,h3,h4,h5,h6{font-weight:normal;}img{border:0;max-width:100%}img.icon{width:23px;height:23px;position:relative;top:5px}table{width:auto;border-collapse:collapse;border-spacing:0;}.image-div,.image-max{font-size:14px;text-align:center}.box_warning{margin:16px;padding:18px;background-color:#eaeaea;border:1px solid gray}.path{background-color:#eee;font-weight:700}.image-div{margin:10px;padding:10px}.image-max{margin:2px;padding:2px}i{color:#fff}q:before{content:\"[\"}q:after{content:\"]\"}body,html{margin:0;padding:0;border:0}body{font-family:SECRobotoLight;line-height:1.5;font-size:17px;background:transparent;-color:#6b6f72;word-wrap:break-word;-webkit-text-size-adjust:none}h1,h2,h3,h4,h5,h6{font-weight:400}img{border:1px;max-width:100%}img.icon{width:23px;height:23px;position:relative;top:5px}table{width:auto;border-collapse:collapse;border-spacing:0}.image_1{width:220px;border:1px solid gray;margin-bottom:5px}.image_1_3{width:220px;border:1px solid gray;margin-bottom:3px}.image_2,.image_3{border:1px solid gray;margin-bottom:5px}.image_2{width:120px}.image_3{width:80px}.image_f,.image_f_no_border{width:320px;margin-bottom:5px}.image_f{border:1px solid gray}*{-webkit-user-select: none;}</style></head><body>{body}</body></html>";
    protected static final String _MIME_TEXT_HTML = "text/html";
    protected static final String _ENCODING_UTF_8 = "utf-8";

    public MyWebView(Context context) {
        super(context.getApplicationContext());
        initialize();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context.getApplicationContext(), attrs);
        initialize();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context.getApplicationContext(), attrs, defStyleAttr);
        initialize();
    }

    protected void initialize() {
        if (isInEditMode()) {
            return;
        }

        setBackgroundColor(0);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);

        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(false);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setBlockNetworkImage(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setGeolocationEnabled(false);
        settings.setNeedInitialFocus(false);
        settings.setSaveFormData(false);
    }

    public void loadPartialData(String basePath, String partialData) {
        String baseURL = "file://" + basePath + "/";
        String html = _HTML_TEMPLATE.replace("{body}", partialData);
        loadDataWithBaseURL(baseURL, html, _MIME_TEXT_HTML, _ENCODING_UTF_8, null);
    }

    @Override
    public void destroy() {
        removeAllViews();
        super.destroy();
    }
}
