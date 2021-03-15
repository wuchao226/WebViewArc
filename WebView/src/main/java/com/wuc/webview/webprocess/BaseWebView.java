package com.wuc.webview.webprocess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.wuc.webview.WebViewCallBack;
import com.wuc.webview.bean.JsParam;
import com.wuc.webview.webprocess.setting.WebViewDefaultSettings;
import com.wuc.webview.webprocess.webchromeclient.WebChromeClientImpl;
import com.wuc.webview.webprocess.webviewclient.WebViewClientImpl;
import java.util.Map;

/**
 * @author : wuchao5
 * @date : 3/10/21 5:22 PM
 * @desciption :
 */
public class BaseWebView extends WebView {

  public static final String TAG = "BaseWebView";

  public BaseWebView(@NonNull Context context) {
    super(context);
    init();
  }

  public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  @SuppressLint("JavascriptInterface")
  private void init() {
    //WebView 初始化时建立 aidl 的连接
    WebViewProcessCommandDispatcher.getInstance().initAidlConnection();
    WebViewDefaultSettings.getInstance().setSettings(this);
    // 通过addJavascriptInterface()将Java对象映射到JS对象
    //参数1：Javascript对象名
    //参数2：Java对象名
    //Javascript对象名 类对象映射到js的test对象
    addJavascriptInterface(this, "xiangxuewebview");
  }

  public void registerWebViewCallBack(WebViewCallBack webViewCallBack) {
    setWebViewClient(new WebViewClientImpl(webViewCallBack));
    setWebChromeClient(new WebChromeClientImpl(webViewCallBack));
  }

  /**
   * 定义JS需要调用的方法
   * 被JS调用的方法必须加入 @JavascriptInterface 注解
   *
   * @param jsParam
   */
  @JavascriptInterface
  public void takeNativeAction(final String jsParam) {
    Log.i(TAG, jsParam);
    if (!TextUtils.isEmpty(jsParam)) {
      final JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
      if (jsParamObject != null) {
        WebViewProcessCommandDispatcher.getInstance()
            .executeCommand(jsParamObject.name, new Gson().toJson(jsParamObject.param), this);
      }
    }
  }

  public void handleCallback(final String callbackname, final String response) {
    if (!TextUtils.isEmpty(callbackname) && !TextUtils.isEmpty(response)) {
      post(new Runnable() {
        @Override
        public void run() {
          String jscode = "javascript:xiangxuejs.callback('" + callbackname + "'," + response + ")";
          Log.e("xxxxxx", jscode);
          evaluateJavascript(jscode, null);
        }
      });
    }
  }
}