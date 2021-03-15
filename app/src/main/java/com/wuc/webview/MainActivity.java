package com.wuc.webview;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.wuc.base.autoservice.ServiceLoaderUitls;
import com.wuc.common.autoservice.IWebViewService;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        IWebViewService webViewService = ServiceLoaderUitls.load(IWebViewService.class);
        if (webViewService != null) {
          // webViewService.startWebViewActivity(MainActivity.this,
          //     "https://www.baidu.com", "百度", true);
          webViewService.startDemoHtml(MainActivity.this);
        }
        // startActivity(new Intent(MainActivity.this, WebViewActivity.class));
      }
    });
  }
}