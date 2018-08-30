package com.zft.oklib.log;

import android.support.annotation.NonNull;
import android.util.Log;

import com.zft.oklib.log.config.LogConfig;
import com.zft.oklib.log.i.IPrinter;
import com.zft.oklib.log.i.IResolver;

import org.json.JSONException;

import javax.xml.transform.TransformerException;

/**
 * 作者:zft
 * 日期:2018/2/5 0005.
 */
public class FPrinter implements IPrinter {
    private final String TAG = "FtLog";
    /**
     * 样式
     */
    private static final char TOP_LEFT_CORNER = '┏';
    private static final char BOTTOM_LEFT_CORNER = '┗';
    private static final char MIDDLE_CORNER = '┠';
    private static final char HORIZONTAL_DOUBLE_LINE = '┃';
    private static final String DOUBLE_DIVIDER = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
    private static final String SINGLE_DIVIDER = "──────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER;
    private static final String NEW_LINE = "\r\n";
    public static String LINE_SEPARATOR = System.getProperty("line.separator");
    private FResolver fResolver;

    private LogConfig config = new LogConfig();


    @Override
    public void init(LogConfig config) {
        this.config = config;
    }

    @Override
    public void v(String... msg) {
        StringBuilder sb = new StringBuilder();
        for (String s : msg) {
            sb.append(s).append("\r\n");
        }
        Log.v(config.getTag(), packMessage(sb.toString()));
    }


    @Override
    public void d(String... msg) {
        StringBuilder sb = new StringBuilder();
        for (String s : msg) {
            sb.append(s).append("\r\n");
        }
        Log.d(config.getTag(), packMessage(sb.toString()));
    }

    @Override
    public void i(String... msg) {
        StringBuilder sb = new StringBuilder();
        for (String s : msg) {
            sb.append(s).append("\r\n");
        }
        Log.i(config.getTag(), packMessage(sb.toString()));
    }

    @Override
    public void w(String... msg) {
        StringBuilder sb = new StringBuilder();
        for (String s : msg) {
            sb.append(s).append("\r\n");
        }
        Log.w(config.getTag(), packMessage(sb.toString()));
    }

    @Override
    public void e(String... msg) {
        StringBuilder sb = new StringBuilder();
        for (String s : msg) {
            sb.append(s).append("\r\n");
        }
        Log.e(config.getTag(), packMessage(sb.toString()));
    }

    @Override
    public void json(String... msg) throws JSONException {
        StringBuilder sb = new StringBuilder();
        for (String j : msg) {
            String json = getResolver().json(j);
            sb.append(json).append(NEW_LINE);
        }
        packMessageAndSend(sb.toString());
    }

    @Override
    public void xml(String... msg) throws TransformerException {
        StringBuilder sb = new StringBuilder();
        for (String j : msg) {
            String json = getResolver().xml(j);
            sb.append(json).append(NEW_LINE);
        }
        packMessageAndSend(sb.toString());
    }

    public IResolver getResolver() {
        fResolver = new FResolver();
        return fResolver;
    }

    /***
     * 包装消息

     * @param msg
     */
    public void packMessageAndSend(String msg) {
        StringBuilder sb = new StringBuilder();
        String[] msgs = msg.split(LINE_SEPARATOR);
        sb.append(TOP_BORDER).append(NEW_LINE);
        if (config.isShowThreadInfo()) {
            //      打印线程信息
            sb.append(HORIZONTAL_DOUBLE_LINE).append("[Thread]->" + Thread.currentThread().getName()).append(NEW_LINE);
            sb.append(MIDDLE_BORDER).append(NEW_LINE);
            //      打印位置信息
            sb.append(HORIZONTAL_DOUBLE_LINE).append(getResolver().getStackInfo()).append(NEW_LINE);
            sb.append(MIDDLE_BORDER).append(NEW_LINE);
        }
//      打印内容
        for (int i = 0; i < msgs.length; i++) {
            String line = msgs[i];
            sb.append(HORIZONTAL_DOUBLE_LINE).append(line).append(NEW_LINE);
            if (sb.length() > 3000) {
                Log.d(config.getTag(), sb.toString());
                sb.delete(0, sb.length());
            }
        }
        sb.append(BOTTOM_BORDER);
        Log.d(config.getTag(), sb.toString());
    }


    @NonNull
    private String packMessage(String s) {
        String msg;
        if (config.isShowThreadInfo()) {
//            msg = s + "\t\t\t\t[Thread]->" + Thread.currentThread().getName() + " [Stack]->" + getResolver().getStackInfo();
            msg = String.format("[%s | %s]  %s", Thread.currentThread().getName(), getResolver().getStackInfo(), s);
        } else {
            msg = s;
        }
        return msg;
    }
}
