package com.wuc.common.autoservice;

import android.content.Context;
import androidx.fragment.app.Fragment;

/**
 * @author : wuchao5
 * @date : 3/8/21 4:36 PM
 * @desciption : WebView 相关 下沉接口
 */
public interface IWebViewService {
  /**
   * 启动 WebViewActivity
   *
   * @param isShowActionBar WebView是否显示标题栏
   */
  void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar);
  /**
   * 获取 WebViewFragment
   * @param canNativeRefresh WebView是否能够下拉刷新
   */
  Fragment getWebViewFragment(String url, boolean canNativeRefresh);

  void startDemoHtml(Context context);
}
