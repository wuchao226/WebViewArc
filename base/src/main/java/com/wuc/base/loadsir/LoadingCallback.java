package com.wuc.base.loadsir;

import android.content.Context;
import android.view.View;
import com.kingja.loadsir.callback.Callback;
import com.wuc.base.R;

/**
 * @author : wuchao5
 * @date : 3/9/21 11:21 AM
 * @desciption :  loadsir 请求加载进度回调
 */
public class LoadingCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
