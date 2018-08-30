#底部导航

## 简介

    okhttp3二次封装
    
### 简单用例

###GET请求

```java
          Map<String, String> params = new HashMap<>();
                         params.put("name", "fentao");
                         FHttp.get()
                                 .url(base_url + "get")
                                 .params(params)
                                 .builder()
                                 .asyn(new JsonBeanCallBack<String>() {
         
                                     @Override
                                     public void onError(Call call, Exception e) {
         
                                     }
         
                                     @Override
                                     public void onResponse(String response) {
                                         ToastUtils.showShortMsg(getApplicationContext(), response);
                                     }
                                 });
;
```

###POST请求

```java
    Map<String, String> params2 = new HashMap<>();
                   params2.put("account", "fentao");
                   params2.put("pwd", "25");
                   FHttp.post()
                           .url(base_url + "post")
                           .params(params2)
                           .builder()
                           .asyn(new JsonBeanCallBack<String>() {
                               @Override
                               public void onError(Call call, Exception e) {
                               }
                               @Override
                               public void onResponse(String response) {
                                   ToastUtils.showShortMsg(getApplicationContext(), response);
                               }
                           });
```

###文本上传

```java
     FHttp.post()
                            .url(base_url + "bodyString")
                            .body("你们好，我是文本")
                            .builder()
                            .asyn(new JsonBeanCallBack<String>() {
                                @Override
                                public void onError(Call call, Exception e) {
    
                                }
    
                                @Override
                                public void onResponse(String response) {
    
                                }
    
    
                            });
```

###多文件上传

```java
     Map<String, Object> form = new HashMap<>();
                    form.put("file", new File("/storage/emulated/0/Huawei/MagazineUnlock/magazine-unlock-05-2.3.1088-_224CD60EE312324DB522F84DC87A0FE3.jpg"));
                    FHttp.post()
                            .url(base_url + "formUpload")
                            .body(form, null)
                            .builder()
                            .asyn(new JsonBeanCallBack<String>() {
                                @Override
                                public void onError(Call call, Exception e) {
    
                                }
    
                                @Override
                                public void onResponse(String response) {
                                    ToastUtils.showShortMsg(getApplicationContext(), response);
                                }
                            });
```