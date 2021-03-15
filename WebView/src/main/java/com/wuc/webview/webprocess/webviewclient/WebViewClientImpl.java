package com.wuc.webview.webprocess.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.wuc.webview.WebViewCallBack;

/**
 * @author : wuchao5
 * @date : 3/9/21 2:16 PM
 * @desciption : 处理各种通知 & 请求事件
 */
public class WebViewClientImpl extends WebViewClient {
  private static final String TAG = "WebViewClientImpl";
  private WebViewCallBack mCallBack;

  public WebViewClientImpl(WebViewCallBack callBack) {
    super();
    mCallBack = callBack;
  }

  /**
   * 开始载入页面调用的
   */
  @Override
  public void onPageStarted(WebView view, String url, Bitmap favicon) {
    super.onPageStarted(view, url, favicon);
    if (mCallBack != null) {
      mCallBack.onPageStarted(url);
    } else {
      Log.e(TAG, "WebViewCallBack is null");
    }
  }

  /**
   * 在页面加载结束时调用
   */
  @Override
  public void onPageFinished(WebView view, String url) {
    super.onPageFinished(view, url);
    if (mCallBack != null) {
      mCallBack.onPageFinished(url);
    } else {
      Log.e(TAG, "WebViewCallBack is null");
    }
  }

  /**
   * 加载页面的服务器出现错误时（如404）调用
   */
  @Override
  public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
    super.onReceivedError(view, request, error);
    if (mCallBack != null) {
      mCallBack.onError();
    } else {
      Log.e(TAG, "WebViewCallBack is null");
    }
  }
}