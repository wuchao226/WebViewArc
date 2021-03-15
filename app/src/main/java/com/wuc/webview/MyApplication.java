package com.wuc.webview;

import com.kingja.loadsir.core.LoadSir;
import com.wuc.base.BaseApplication;
import com.wuc.base.loadsir.CustomCallback;
import com.wuc.base.loadsir.EmptyCallback;
import com.wuc.base.loadsir.ErrorCallback;
import com.wuc.base.loadsir.LoadingCallback;
import com.wuc.base.loadsir.TimeoutCallback;

/**
 * @author : wuchao5
 * @date : 3/9/21 11:38 AM
 * @desciption :
 */
public class MyApplication extends BaseApplication {

  @Override public void onCreate() {
    super.onCreate();
    LoadSir.beginBuilder()
        .addCallback(new ErrorCallback())
        .addCallback(new EmptyCallback())
        .addCallback(new LoadingCallback())
        .addCallback(new TimeoutCallback())
        .addCallback(new CustomCallback())
        .setDefaultCallback(LoadingCallback.class)
        .commit();
  }
}