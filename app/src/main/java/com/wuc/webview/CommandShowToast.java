package com.wuc.webview;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.wuc.base.BaseApplication;
import com.wuc.webview.command.Command;
import java.util.Map;

/**
 * @author : wuchao5
 * @date : 3/12/21 12:01 PM
 * @desciption :
 */
@AutoService(Command.class)
public class CommandShowToast implements Command {
  @Override public String name() {
    return "showToast";
  }

  @Override public void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
    // 不能直接在子线程中弹出Toast
    Handler handler = new Handler(Looper.getMainLooper());
    handler.post(new Runnable() {
      @Override public void run() {
        Toast.makeText(BaseApplication.sApplication, String.valueOf(parameters.get("message")), Toast.LENGTH_SHORT).show();
      }
    });
  }
}