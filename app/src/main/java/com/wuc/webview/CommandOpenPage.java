package com.wuc.webview;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import com.google.auto.service.AutoService;
import com.wuc.base.BaseApplication;
import com.wuc.webview.command.Command;
import java.util.Map;

/**
 * @author : wuchao5
 * @date : 3/12/21 11:30 AM
 * @desciption :
 */
@AutoService(Command.class)
public class CommandOpenPage implements Command {
  @Override public String name() {
    return "openPage";
  }

  @Override public void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
    String target_class = String.valueOf(parameters.get("target_class"));
    if (!TextUtils.isEmpty(target_class)) {
      Intent intent = new Intent();
      intent.setComponent(new ComponentName(BaseApplication.sApplication, target_class));
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      BaseApplication.sApplication.startActivity(intent);
    }
  }
}