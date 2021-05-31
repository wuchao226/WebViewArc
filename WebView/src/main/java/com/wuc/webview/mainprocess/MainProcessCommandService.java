package com.wuc.webview.mainprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

/**
 * @author : wuchao5
 * @date : 3/11/21 4:22 PM
 * @desciption :远端服务
 */
public class MainProcessCommandService extends Service {

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return MainProcessCommandsManager.getInstance();
  }
}