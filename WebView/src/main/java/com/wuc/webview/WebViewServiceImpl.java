package com.wuc.webview;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.google.auto.service.AutoService;
import com.wuc.common.autoservice.IWebViewService;
import com.wuc.webview.utils.Constants;

/**
 * @author : wuchao5
 * @date : 3/8/21 4:38 PM
 * @desciption : 下沉接口 IWebViewService 实现类
 */
@AutoService(IWebViewService.class)
public class WebViewServiceImpl implements IWebViewService {
  /**
   * 启动 WebViewActivity
   *
   * @param isShowActionBar WebView是否显示标题栏
   */
  @Override
  public void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar) {
    if (context != null) {
      Intent intent = new Intent(context, WebViewActivity.class);
      intent.putExtra(Constants.TITLE, title);
      intent.putExtra(Constants.URL, url);
      intent.putExtra(Constants.IS_SHOW_ACTION_BAR, isShowActionBar);
      context.startActivity(intent);
    }
  }

  /**
   * 获取 WebViewFragment
   *
   * @param canNativeRefresh WebView是否能够下拉刷新
   */
  @Override
  public Fragment getWebViewFragment(String url, boolean canNativeRefresh) {
    return WebViewFragment.newInstance(url, canNativeRefresh);
  }

  @Override
  public void startDemoHtml(Context context) {
    Intent intent = new Intent(context, WebViewActivity.class);
    intent.putExtra(Constants.TITLE, "本地Demo测试页");
    intent.putExtra(Constants.URL, Constants.ANDROID_ASSET_URI + "demo.html");
    context.startActivity(intent);
  }
}