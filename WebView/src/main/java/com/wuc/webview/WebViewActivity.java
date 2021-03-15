package com.wuc.webview;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.wuc.webview.databinding.ActivityWebviewBinding;
import com.wuc.webview.utils.Constants;

public class WebViewActivity extends AppCompatActivity {

  private ActivityWebviewBinding mBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
    mBinding.title.setText(getIntent().getStringExtra(Constants.TITLE));
    // mBinding.webview.getSettings().setJavaScriptEnabled(true);
    // mBinding.webview.loadUrl(getIntent().getStringExtra(Constants.URL));
    mBinding.actionBar.setVisibility(getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR, true) ? View.VISIBLE : View.GONE);
    mBinding.back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        WebViewActivity.this.finish();
      }
    });

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    WebViewFragment fragment = WebViewFragment.newInstance(getIntent().getStringExtra(Constants.URL), true);
    transaction.replace(R.id.web_view_fragment, fragment).commit();
  }

  /**
   * 获取Web页中的标题
   */
  public void updateTitle(String title) {
    mBinding.title.setText(title);
  }
}