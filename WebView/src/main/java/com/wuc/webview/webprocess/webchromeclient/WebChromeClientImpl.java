package com.wuc.webview.webprocess.webchromeclient;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.wuc.webview.WebViewCallBack;

/**
 * @author : wuchao5
 * @date : 3/9/21 4:43 PM
 * @desciption : 辅助 WebView 处理 Javascript 的对话框,网站图标,网站标题等等
 */
public class WebChromeClientImpl extends WebChromeClient {
  private static final String TAG = "WebChromeClientImpl";

  private WebViewCallBack mCallBack;

  public WebChromeClientImpl(WebViewCallBack mCallBack) {
    this.mCallBack = mCallBack;
  }

  /**
   * 获取Web页中的标题
   */
  @Override
  public void onReceivedTitle(WebView view, String title) {
    super.onReceivedTitle(view, title);
    if (mCallBack != null) {
      mCallBack.updateTitle(title);
    } else {
      Log.e(TAG, "WebViewCallBack is null");
    }
  }
}