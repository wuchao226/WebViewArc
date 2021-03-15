package com.wuc.common.event;

/**
 * @author : wuchao5
 * @date : 3/12/21 5:21 PM
 * @desciption : 登录 事件
 */
public class LoginEvent {
  public String userName;

  public LoginEvent(String userName) {
    this.userName = userName;
  }
}
