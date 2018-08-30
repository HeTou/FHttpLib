package com.zft.oklib;

import android.content.Context;

import com.zft.oklib.log.FLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 作者:zft
 * 日期:2018/5/16 0016.
 */
public class HttpTestManager {
    private static final boolean TEST = true;
    private static Map<String, String> httpResponse = new HashMap<>();

    public static void init(Context context) {
        httpResponse.clear();
        try {
            InputStream open = context.getAssets().open("httpresponse.json");
            InputStreamReader isr = new InputStreamReader(open);
            BufferedReader br = new BufferedReader(isr);
            String line;
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
//            开始解析
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                String api = obj.getString("api");
                String data = obj.getString("data");
                FLog.d(api, data);
                httpResponse.put(api, data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String testInterceptor(String url) {
        Set<String> strings = httpResponse.keySet();
        if (strings.contains(url)) {
            return httpResponse.get(url);
        }
        return null;
    }
}
