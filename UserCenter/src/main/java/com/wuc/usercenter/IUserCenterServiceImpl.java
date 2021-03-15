package com.wuc.usercenter;

import android.content.Intent;
import com.google.auto.service.AutoService;
import com.wuc.base.BaseApplication;
import com.wuc.common.autoservice.IUserCenterService;

/**
 * @author : wuchao5
 * @date : 3/12/21 4:36 PM
 * @desciption :
 */
@AutoService(IUserCenterService.class)
public class IUserCenterServiceImpl implements IUserCenterService {
  @Override
  public boolean isLogined() {
    return false;
  }

  @Override
  public void login() {
    Intent intent = new Intent(BaseApplication.sApplication, LoginActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    BaseApplication.sApplication.startActivity(intent);
  }
}