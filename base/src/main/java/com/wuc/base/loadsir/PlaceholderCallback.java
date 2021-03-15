package com.wuc.base.loadsir;

import android.content.Context;
import android.view.View;
import com.kingja.loadsir.callback.Callback;
import com.wuc.base.R;

/**
 * @author : wuchao5
 * @date : 3/9/21 11:21 AM
 * @desciption :  loadsir 请求占位符加载进度回调
 */
public class PlaceholderCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_placeholder;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
