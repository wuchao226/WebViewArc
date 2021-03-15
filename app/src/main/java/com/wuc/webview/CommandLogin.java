package com.wuc.webview;

import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.wuc.base.BaseApplication;
import com.wuc.base.LiveDataBus;
import com.wuc.base.autoservice.ServiceLoaderUitls;
import com.wuc.common.autoservice.IUserCenterService;
import com.wuc.common.event.LoginEvent;
import com.wuc.webview.command.Command;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * @author : wuchao5
 * @date : 3/12/21 4:39 PM
 * @desciption :
 */
@AutoService(Command.class)
public class CommandLogin implements Command {

  IUserCenterService iUserCenterService = ServiceLoaderUitls.load(IUserCenterService.class);
  //execute方法是异步的，记录下callback
  private ICallbackFromMainprocessToWebViewProcessInterface callback;
  //记录回调到js的 名称
  String callbacknameFromNativeJs;

  public CommandLogin() {
    EventBus.getDefault().register(this);
    /*LiveDataBus.get().with("loginEvent", LoginEvent.class)
        .observe(this, new Observer<LoginEvent>() {
          @Override
          public void onChanged(LoginEvent loginEvent) {
            Log.d("CommandLogin", loginEvent.userName);
            HashMap map = new HashMap();
            map.put("accountName", loginEvent.userName);
            if (CommandLogin.this.callback != null) {
              try {
                CommandLogin.this.callback.onResult(callbacknameFromNativeJs, new Gson().toJson(map));
              } catch (RemoteException e) {
                e.printStackTrace();
              }
            }
          }
        });*/
  }

  @Override
  public String name() {
    return "login";
  }

  @Override
  public void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
    if (iUserCenterService != null && !iUserCenterService.isLogined()) {
      iUserCenterService.login();
      this.callback = callback;
      this.callbacknameFromNativeJs = parameters.get("callbackname").toString();
    }
  }

  @Subscribe
  public void onMessageEvent(LoginEvent event) {
    Log.d("CommandLogin", event.userName);
    HashMap map = new HashMap();
    map.put("accountName", event.userName);
    if (this.callback != null) {
      try {
        this.callback.onResult(callbacknameFromNativeJs, new Gson().toJson(map));
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }
}