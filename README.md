# WebViewArc
使用 AutoService 实现组件化，完成可扩展的 WebView 模块


### 使用方式

在 app 壳 加入 实现 Command 的命令即可
```
@AutoService(Command.class)
public class CommandOpenPage implements Command {
  @Override public String name() {
    return "openPage";
  }

  @Override public void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
    String target_class = String.valueOf(parameters.get("target_class"));
    if (!TextUtils.isEmpty(target_class)) {
      Intent intent = new Intent();
      intent.setComponent(new ComponentName(BaseApplication.sApplication, target_class));
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      BaseApplication.sApplication.startActivity(intent);
    }
  }
}
```

```
@AutoService(Command.class)
public class CommandShowToast implements Command {
  @Override public String name() {
    return "showToast";
  }

  @Override public void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
    // 不能直接在子线程中弹出Toast
    Handler handler = new Handler(Looper.getMainLooper());
    handler.post(new Runnable() {
      @Override public void run() {
        Toast.makeText(BaseApplication.sApplication, String.valueOf(parameters.get("message")), Toast.LENGTH_SHORT).show();
      }
    });
  }
}
```
