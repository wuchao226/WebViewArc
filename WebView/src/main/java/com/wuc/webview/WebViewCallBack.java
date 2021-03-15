package com.wuc.webview;

/**
 * @author : wuchao5
 * @date : 3/9/21 2:18 PM
 * @desciption : Web页面打开时 WebViewClient 和 WebChromeClient 事件监听
 */
public interface WebViewCallBack {
  /**
   * WebView 开始载入页面
   */
  void onPageStarted(String url);
  /**
   * WebView 页面加载结束
   */
  void onPageFinished(String url);
  /**
   * WebView 加载错误
   */
  void onError();
  /**
   * 获取Web页中的标题
   */
  void updateTitle(String title);
}
