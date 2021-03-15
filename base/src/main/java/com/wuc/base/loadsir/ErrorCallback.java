package com.wuc.base.loadsir;


import com.kingja.loadsir.callback.Callback;
import com.wuc.base.R;

/**
 * @author : wuchao5
 * @date : 3/9/21 11:19 AM
 * @desciption : loadsir 请求错误回调
 */
public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_error;
    }
}
