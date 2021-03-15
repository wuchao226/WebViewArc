package com.wuc.base.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.wuc.base.R;

/**
 * @author : wuchao5
 * @date : 3/9/21 11:21 AM
 * @desciption : lottie 加载为空数据动画回调
 */
public class LottieEmptyCallback extends Callback {

  @Override
  protected int onCreateView() {
    return R.layout.layout_lottie_empty;
  }

}
