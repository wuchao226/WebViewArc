package com.wuc.base.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.wuc.base.R;

/**
 * @author : wuchao5
 * @date : 3/9/21 11:19 AM
 * @desciption :  loadsir 没有数据回调
 */
public class EmptyCallback extends Callback {

  @Override
  protected int onCreateView() {
    return R.layout.layout_empty;
  }

}
