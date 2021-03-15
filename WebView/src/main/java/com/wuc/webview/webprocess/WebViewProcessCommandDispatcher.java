package com.wuc.webview.webprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.wuc.base.BaseApplication;
import com.wuc.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import com.wuc.webview.IWebviewProcessToMainProcessInterface;
import com.wuc.webview.mainprocess.MainProcessCommandService;

/**
 * @author : wuchao5
 * @date : 3/11/21 4:56 PM
 * @desciption : 管理 远端 Service 的链接 和 命令的 分发
 */
public class WebViewProcessCommandDispatcher implements ServiceConnection {

  private static WebViewProcessCommandDispatcher sInstance;

  /**
   * 保存连接后的 Service
   */
  private IWebviewProcessToMainProcessInterface iWebviewProcessToMainProcessInterface;

  private WebViewProcessCommandDispatcher() {
  }

  public static WebViewProcessCommandDispatcher getInstance() {
    if (sInstance == null) {
      synchronized (WebViewProcessCommandDispatcher.class) {
        if (sInstance == null) {
          sInstance = new WebViewProcessCommandDispatcher();
        }
      }
    }
    return sInstance;
  }

  public void initAidlConnection() {
    Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
    BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
  }

  @Override
  public void onServiceConnected(ComponentName name, IBinder service) {
    iWebviewProcessToMainProcessInterface = IWebviewProcessToMainProcessInterface.Stub.asInterface(service);
  }

  @Override
  public void onServiceDisconnected(ComponentName name) {
    iWebviewProcessToMainProcessInterface = null;
    initAidlConnection();
  }

  @Override
  public void onBindingDied(ComponentName name) {
    iWebviewProcessToMainProcessInterface = null;
    initAidlConnection();
  }

  /**
   * 分发命令
   */
  public void executeCommand(String commandName, String params, BaseWebView webView) {
    if (iWebviewProcessToMainProcessInterface != null) {
      try {
        iWebviewProcessToMainProcessInterface.handleWebCommand(commandName, params,
            new ICallbackFromMainprocessToWebViewProcessInterface.Stub() {
              @Override
              public void onResult(String callbackname, String response) throws RemoteException {
                webView.handleCallback(callbackname, response);
              }
            });
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }
}