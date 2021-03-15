package com.wuc.webview.mainprocess;

import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.wuc.base.BaseApplication;
import com.wuc.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import com.wuc.webview.IWebviewProcessToMainProcessInterface;
import com.wuc.webview.command.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author : wuchao5
 * @date : 3/11/21 4:25 PM
 * @desciption : 命令管理器
 */
public class MainProcessCommandsManager extends IWebviewProcessToMainProcessInterface.Stub {

  private static MainProcessCommandsManager sInstance;
  /**
   * 存储 命令 名称及对应的命令  将定义好的命令加入命令管理器
   */
  private final HashMap<String, Command> mCommands = new HashMap<>();

  private MainProcessCommandsManager() {
    // 通过 AutoService 自定注册 获取命令
    ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
    for (Command command : serviceLoader) {
      if (!mCommands.containsKey(command.name())) {
        mCommands.put(command.name(), command);
      }
    }
  }

  public static MainProcessCommandsManager getInstance() {
    if (sInstance == null) {
      synchronized (MainProcessCommandsManager.class) {
        if (sInstance == null) {
          sInstance = new MainProcessCommandsManager();
        }
      }
    }
    return sInstance;
  }

  @Override
  public void handleWebCommand(String commandName, String jsonParams, ICallbackFromMainprocessToWebViewProcessInterface callback) throws RemoteException {
    executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class), callback);
  }

  public void executeCommand(String commandName, Map params, ICallbackFromMainprocessToWebViewProcessInterface callback) {
    Command command = mCommands.get(commandName);
    if (command != null) {
      command.execute(params, callback);
    }
  }
}