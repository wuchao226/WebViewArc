package com.wuc.webview;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wuc.base.loadsir.ErrorCallback;
import com.wuc.base.loadsir.LoadingCallback;
import com.wuc.webview.databinding.FragmentWebviewBinding;
import com.wuc.webview.utils.Constants;

/**
 * @author : wuchao5
 * @date : 3/8/21 7:34 PM
 * @desciption : 返回一个统一事件处理的 Fragment 页面。
 */
public class WebViewFragment extends Fragment implements WebViewCallBack, OnRefreshListener {

  private String mUrl;
  private LoadService mLoadService;
  private FragmentWebviewBinding mBinding;
  /**
   * WebView 是否能够下拉刷新
   */
  private boolean mCanNativeRefresh;
  /**
   * WebView 是否加载错误
   */
  private boolean mIsError = false;
  private static final String TAG = "WebViewFragment";

  public WebViewFragment() {
  }

  public static WebViewFragment newInstance(String url, boolean canNativeRefresh) {
    WebViewFragment fragment = new WebViewFragment();
    Bundle args = new Bundle();
    args.putString(Constants.URL, url);
    args.putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getArguments();
    if (bundle != null) {
      mUrl = bundle.getString(Constants.URL);
      mCanNativeRefresh = bundle.getBoolean(Constants.CAN_NATIVE_REFRESH);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_webview, container, false);
    mBinding.webview.registerWebViewCallBack(this);
    mBinding.webview.loadUrl(mUrl);
    // 注册布局View
    mLoadService = LoadSir.getDefault().register(mBinding.smartRefreshLayout, new Callback.OnReloadListener() {
      @Override
      public void onReload(View v) {
        // 重新加载逻辑
        Log.e(TAG, "onReload");
        mLoadService.showCallback(LoadingCallback.class);
        mBinding.webview.reload();
      }
    });

    mBinding.smartRefreshLayout.setEnableRefresh(mCanNativeRefresh);
    mBinding.smartRefreshLayout.setEnableLoadMore(false);
    mBinding.smartRefreshLayout.setOnRefreshListener(this);
    // 返回LoadSir生成的LoadLayout
    return mLoadService.getLoadLayout();
  }

  /**
   * WebView 开始载入页面
   */
  @Override
  public void onPageStarted(String url) {
    Log.e(TAG, "onPageStarted");
    if (mLoadService != null) {
      mLoadService.showCallback(LoadingCallback.class);
    }
  }

  /**
   * WebView 页面加载结束
   */
  @Override
  public void onPageFinished(String url) {
    if (mIsError) {
      mBinding.smartRefreshLayout.setEnableRefresh(true);
    } else {
      mBinding.smartRefreshLayout.setEnableRefresh(mCanNativeRefresh);
    }
    Log.e(TAG, "pageFinished");
    mBinding.smartRefreshLayout.finishRefresh();
    if (mLoadService != null) {
      if (mIsError) {
        mLoadService.showCallback(ErrorCallback.class);
      } else {
        mLoadService.showSuccess();
      }
    }
    mIsError = false;
  }

  /**
   * WebView 加载错误
   */
  @Override
  public void onError() {
    Log.e(TAG, "onError");
    mIsError = true;
    mBinding.smartRefreshLayout.finishRefresh();
  }

  /**
   * 获取Web页中的标题
   */
  @Override
  public void updateTitle(String title) {
    if (getActivity() instanceof WebViewActivity) {
      ((WebViewActivity) getActivity()).updateTitle(title);
    }
  }

  /**
   * 下拉刷新
   */
  @Override
  public void onRefresh(@NonNull RefreshLayout refreshLayout) {
    mBinding.webview.reload();
  }
}