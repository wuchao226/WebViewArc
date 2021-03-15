// IWebviewProcessToMainProcessInterface.aidl
package com.wuc.webview;
import com.wuc.webview.ICallbackFromMainprocessToWebViewProcessInterface;

// Webview 进程 到  Main 进程 接口文件
interface IWebviewProcessToMainProcessInterface {
   void handleWebCommand(String commandName, String jsonParams, in ICallbackFromMainprocessToWebViewProcessInterface callback);
}