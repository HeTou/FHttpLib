package com.zft.basehttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zft.oklib.FHttp;
import com.zft.oklib.callback.JsonBeanCallBack;
import com.zft.oklib.callback.util.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String base_url = "http://139.199.152.111:8081/APPWeb/fhttp/";
    /**
     * get請求
     */
    private Button mBtnGet;
    /**
     * post請求
     */
    private Button mBtnPost;
    /**
     * 超长文本上传
     */
    private Button mBtnString;
    /**
     * 表单上传
     */
    private Button mBtnForm;
    /**
     * 文件上传
     */
    private Button mBtnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnGet = (Button) findViewById(R.id.btn_get);
        mBtnGet.setOnClickListener(this);
        mBtnPost = (Button) findViewById(R.id.btn_post);
        mBtnPost.setOnClickListener(this);
        mBtnString = (Button) findViewById(R.id.btn_string);
        mBtnString.setOnClickListener(this);
        mBtnForm = (Button) findViewById(R.id.btn_form);
        mBtnForm.setOnClickListener(this);
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mBtnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_get:      //get请求
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
                break;
            case R.id.btn_post:     //post请求
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
                break;
            case R.id.btn_string:   //raw请求
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
                break;
            case R.id.btn_form:     //表单上传
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
                break;
            case R.id.btn_update:

                break;
        }
    }
}
