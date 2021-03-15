// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.wuc.webview;

// 回调，把数据传递到 js
interface ICallbackFromMainprocessToWebViewProcessInterface {
    /**
     * callbackname ：回调的名称
     * response：回调的内容
     */
   void onResult(String callbackname, String response);
}