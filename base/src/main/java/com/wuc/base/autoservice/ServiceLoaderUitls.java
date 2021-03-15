package com.wuc.base.autoservice;

import android.util.Log;
import java.util.ServiceLoader;

/**
 * @author : wuchao5
 * @date : 3/8/21 5:20 PM
 * @desciption :
 */
public class ServiceLoaderUitls {

  private ServiceLoaderUitls() {
  }

  public static <T> T load(Class<T> tClass) {
    try {
      return ServiceLoader.load(tClass).iterator().next();
    } catch (Exception e) {
      Log.e("ServiceLoaderUitls", e.toString());
      return null;
    }
  }
}