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
      WebView.enableSlowWholeDocumentDraw();
    }
    WebSettings mWebSettings = webView.getSettings();
    //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
    mWebSettings.setJavaScriptEnabled(true);
    /** 屏幕缩放操作*/
    // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放, 是前面方法的前提
    mWebSettings.setSupportZoom(true);
    // 设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
    mWebSettings.setBuiltInZoomControls(true);
    // 设置WebView使用内置缩放机制时，是否展现在屏幕缩放控件上，默认true，展现在控件上。
    mWebSettings.setDisplayZoomControls(true);

    // 硬件加速兼容性问题有点多
   /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
      webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }*/

    /**设置自适应屏幕*/
    // 设置WebView是否使用viewport，将图片调整到适合WebView的大小
    mWebSettings.setUseWideViewPort(true);
    // 设置WebView是否使用预览模式加载界面，即缩放至屏幕的大小
    mWebSettings.setLoadWithOverviewMode(true);
    // 设置WebView是否支持多窗口, 如果为true，必须要重写WebChromeClient的onCreateWindow方法
    mWebSettings.setSupportMultipleWindows(true);
    // 设置WebView是否需要设置一个节点获取焦点当WebView#requestFocus(int,android.graphics.Rect)被调用时，默认true
    mWebSettings.setNeedInitialFocus(true);

    /**设置显示字体相关*/
    // 设置WebView加载页面文本内容的编码，默认"UTF-8"。
    mWebSettings.setDefaultTextEncodingName("UTF-8");
    // 设置标准的字体族，默认"sans-serif"。
    // mWebSettings.setStandardFontFamily("sans-serif");
    // 设置混合字体族，默认"monospace"。
    // mWebSettings.setFixedFontFamily("monospace");
    // 设置默认填充字体大小，默认16，取值区间为[1-72]，超过范围，使用其上限值。
    mWebSettings.setDefaultFixedFontSize(16);
    // 设置默认字体大小，默认16，取值区间[1-72]，超过范围，使用其上限值。
    mWebSettings.setDefaultFontSize(16);
    mWebSettings.setDefaultFontSize(16);
    //设置 WebView 支持的最小字体大小，默认为 8
    mWebSettings.setMinimumFontSize(10);
    // 以百分比设置页面的文本缩放。
    mWebSettings.setTextZoom(100);

    /**缓存模式相关*/
    if (isNetworkConnected(webView.getContext())) {
      mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
    } else {
      mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    /**资源加载相关*/
    //原来获取的UA
    String userAgent = mWebSettings.getUserAgentString();
    // userAgent: Mozilla/5.0 (Linux; Android 10; Android SDK built for x86 Build/QSR1.200715.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.185 Mobile Safari/537.36
    // 设置WebView代理字符串，如果String为null或为空，将使用系统默认值
    mWebSettings.setUserAgentString(userAgent + "union_ad");
    // 设置WebView是否以http、https方式访问从网络加载图片资源，默认false
    mWebSettings.setBlockNetworkImage(false);
    // 设置WebView是否从网络加载资源，Application需要设置访问网络权限，否则报异常
    mWebSettings.setBlockNetworkLoads(false);
    // 设置WebView是否加载图片资源，默认true，自动加载图片
    if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
      mWebSettings.setLoadsImagesAutomatically(false);
    } else {
      //4.4以上系统在onPageFinished时再恢复图片加载时,如果存在多张图片引用的是相同的src时，
      //会只有一个image标签得到加载，这样会造成界面不对的情况，所以4.4以上要在加载网页的时候就去主动加载图片
      mWebSettings.setLoadsImagesAutomatically(true);
    }
    // 5.0以上默认禁止了https和http混用
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      // 设置当一个安全站点企图加载来自一个不安全站点资源时WebView的行为
      // Android 5.0以下，默认是MIXED_CONTENT_ALWAYS_ALLOW
      // Android 5.0已上，默认是MIXED_CONTENT_NEVER_ALLOW
      mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
    }

    /**缓存机制相关*/
    // 开启 database storage API 功能
    mWebSettings.setDatabaseEnabled(true);
    // 开启 DOM storage API 功能
    mWebSettings.setDomStorageEnabled(true);
    // 开启Application Caches 功能
    mWebSettings.setAppCacheEnabled(true);
    // 设置Application Caches 缓存目录
    // 这个路径必须是可以让app写入文件的。该方法应该只被调用一次，重复调用会被无视~
    String appCacheDir = webView.getContext().getDir("WUcache", Context.MODE_PRIVATE).getPath();
    Log.i("WebSetting", "WebView cache dir: " + appCacheDir);
    mWebSettings.setDatabasePath(appCacheDir);
    mWebSettings.setAppCachePath(appCacheDir);
    mWebSettings.setAppCacheMaxSize(1024 * 1024 * 80);

    /**访问权限相关*/
    // 设置在WebView内部是否允许访问文件，默认允许访问。
    mWebSettings.setAllowFileAccess(true);
    // 设置在WebView内部是否允许访问ContentProvider，默认允许访问。
    mWebSettings.setAllowContentAccess(true);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      // 设置在WebView内部是否允许通过file url加载的 Js代码读取其他的本地文件
      // Android 4.1前默认允许，4.1后默认禁止
      mWebSettings.setAllowFileAccessFromFileURLs(false);
      // 设置WebView内部是否允许通过 file url 加载的 Javascript 可以访问其他的源(包括http、https等源)
      // Android 4.1前默认允许，4.1后默认禁止 //允许通过 file url 加载的 Javascript 可以访问其他的源，
      // 包括其他的文件和 http，https 等其他的源
      mWebSettings.setAllowUniversalAccessFromFileURLs(false);
    }

    //支持通过JS打开新窗口
    mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    } else {
      mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    }
    // 设置WebView是否应保存表单数据
    mWebSettings.setSaveFormData(false);
    // 设置是否启用地理位置。
    mWebSettings.setGeolocationEnabled(true);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
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