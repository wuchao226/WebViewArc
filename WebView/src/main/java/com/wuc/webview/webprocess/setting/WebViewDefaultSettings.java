package com.wuc.webview.webprocess.setting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.wuc.webview.BuildConfig;

/**
 * @author : wuchao5
 * @date : 3/10/21 3:03 PM
 * @desciption : WebView 默认设置
 */
public class WebViewDefaultSettings {

  private WebViewDefaultSettings() {
  }

  public static WebViewDefaultSettings getInstance() {
    return Holder.instance;
  }

  public static final class Holder {
    private static final WebViewDefaultSettings instance = new WebViewDefaultSettings();
  }

  @SuppressLint("SetJavaScriptEnabled")
  public void setSettings(WebView webView) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      webView.enableSlowWholeDocumentDraw();
    }
    WebSettings mWebSettings = webView.getSettings();
    //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
    mWebSettings.setJavaScriptEnabled(true);
    //缩放操作
    mWebSettings.setSupportZoom(true);//支持缩放，默认为true。是下面那个的前提。
    mWebSettings.setBuiltInZoomControls(false);//设置内置的缩放控件。若为false，则该WebView不可缩放
    if (isNetworkConnected(webView.getContext())) {
      mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
    } else {
      mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
    }

    // 硬件加速兼容性问题有点多
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    //            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    //        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    //        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
    //            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    //        }

    mWebSettings.setTextZoom(100);
    mWebSettings.setDatabaseEnabled(true);
    mWebSettings.setAppCacheEnabled(true);
    //支持自动加载图片
    mWebSettings.setLoadsImagesAutomatically(true);
    mWebSettings.setSupportMultipleWindows(false);
    mWebSettings.setBlockNetworkImage(false);//是否阻塞加载网络图片  协议http or https
    mWebSettings.setAllowFileAccess(true); //允许加载本地文件html  file协议
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      mWebSettings.setAllowFileAccessFromFileURLs(false); //通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
      mWebSettings.setAllowUniversalAccessFromFileURLs(false);//允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
    }
    //支持通过JS打开新窗口
    mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    } else {
      mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    }
    mWebSettings.setSavePassword(false);
    mWebSettings.setSaveFormData(false);
    //设置自适应屏幕，两者合用
    mWebSettings.setUseWideViewPort(true);//将图片调整到适合webview的大小
    mWebSettings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
    mWebSettings.setDomStorageEnabled(true);
    mWebSettings.setNeedInitialFocus(true);
    mWebSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    mWebSettings.setDefaultFontSize(16);
    mWebSettings.setMinimumFontSize(10);//设置 WebView 支持的最小字体大小，默认为 8
    mWebSettings.setGeolocationEnabled(true);

    String appCacheDir = webView.getContext().getDir("cache", Context.MODE_PRIVATE).getPath();
    Log.i("WebSetting", "WebView cache dir: " + appCacheDir);
    mWebSettings.setDatabasePath(appCacheDir);
    mWebSettings.setAppCachePath(appCacheDir);
    mWebSettings.setAppCacheMaxSize(1024 * 1024 * 80);

    // 用户可以自己设置useragent
    // mWebSettings.setUserAgentString("webprogress/build you agent info");

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      webView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
    }
  }

  private static boolean isNetworkConnected(Context context) {
    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
    if (networkInfo != null) {
      boolean a = networkInfo.isConnected();
      return a;
    } else {
      return false;
    }
  }
}