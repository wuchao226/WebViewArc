package com.wuc.base.loadsir;

import android.content.Context;
import android.view.View;
import com.kingja.loadsir.callback.Callback;
import com.wuc.base.R;

/**
 * @author : wuchao5
 * @date : 3/9/21 11:18 AM
 * @desciption : lottie 加载动画回调
 */
public class LottieLoadingCallback extends Callback {

  @Override
  protected int onCreateView() {
    return R.layout.layout_lottie_loading;
  }

  @Override
  protected boolean onReloadEvent(Context context, View view) {
    return true;
  }
}
