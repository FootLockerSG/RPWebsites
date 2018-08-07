package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class webview extends AppCompatActivity{

    //Step 1
    WebView wb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        //Step 2
        wb = findViewById(R.id.WebView);
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(false);
        webSettings.getBuiltInZoomControls();

        wb.setWebViewClient(new WebViewClient());


        Intent intentReceived = getIntent();
        int category = intentReceived.getIntExtra("category", 0);
        int site = intentReceived.getIntExtra("site", 0);

        if (category == 0) {
            final String[] strRP = getResources().getStringArray(R.array.URL_RP);
            if (site == 0) {
                wb.loadUrl(strRP[0]);
            }
            else {
                wb.loadUrl(strRP[1]);
            }
        }
        else {
            final String[] strSOI = getResources().getStringArray(R.array.URL_SOI);
            if (site == 0) {
                wb.loadUrl(strSOI[0]);
            }
            else {
                wb.loadUrl(strSOI[1]);
            }

        }

    }
}
