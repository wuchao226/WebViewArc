package com.wuc.common.autoservice;

/**
 * @author : wuchao5
 * @date : 3/12/21 4:02 PM
 * @desciption : 用户中心 相关 下沉接口
 */
public interface IUserCenterService {
  /**
   * 是否登录
   */
  boolean isLogined();
  /**
   * 登录事件
   */
  void login();
}
