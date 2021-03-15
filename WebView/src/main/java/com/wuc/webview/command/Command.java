package com.wuc.webview.command;

import com.wuc.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import java.util.Map;

/**
 * @author : wuchao5
 * @date : 3/12/21 11:21 AM
 * @desciption : WebView 与 JS 的交互
 */
public interface Command {
  /**
   * 命令名字
   */
  String name();
  /**
   * 执行命令
   *
   * @param parameters 命令携带的参数
   */
  void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback);
}
