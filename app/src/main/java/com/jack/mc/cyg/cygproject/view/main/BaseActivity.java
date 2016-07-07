package com.jack.mc.cyg.cygproject.view.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:Created by JackCheng
 * Email:17764576259@163.com
 * Time:2016/1/20 19:54
 * Copyright:1.0
 */
public class BaseActivity extends Activity {

    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    //根据输入框中的内容是否为空来判断按钮是否显示
    public void buttonShowOrNot(EditText editText, Button button) {
        String strContent = editText.getText().toString();
        if (!strContent.isEmpty()) {
            showView(button);
        } else {
            hideView(button);
        }
    }

    //隐藏控件
    public void hideView(View v) {
        v.setVisibility(v.INVISIBLE);
    }

    //显示控件
    public void showView(View v) {
        v.setVisibility(v.VISIBLE);
    }

    //让按钮不可点击
    public void disableButton(Button button) {
        button.setEnabled(false);
    }

    //让按钮不可点击
    public void enableButton(Button button) {
        button.setEnabled(true);
    }

    /**
     * 根据EditText文本内容是否为空判断是否disable按钮
     * 如果有一个EditText文本内容为空，则disable按钮
     */
    public void enableButtonOrNot(final Button button, final EditText... editText) {
        int length = editText.length; // 表示传入的EditText的个数
        final boolean[] flag = new boolean[length]; // 表示各个edittext文本内容是否为空，false表示空，true表示非空
        for (int i = 0; i < length; i++) {
            final int finalI = i;
            editText[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (editText[finalI].getText().toString().length() == 0) {
                        flag[finalI] = false;
                    } else {
                        flag[finalI] = true;
                    }
                    if (allNotEmpty(flag)) {
                        enableButton(button);
                    } else {
                        disableButton(button);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    /**
     * 判断是不是所有的标记表示的都是非空
     * 如果有一个表示的为空，则返回false
     * 否则，返回true
     */
    private boolean allNotEmpty(boolean[] flag) {
        for (int i = 0; i < flag.length; i++) {
            if (false == flag[i])
                return false;
        }
        return true;
    }

    /**
     * 触屏事件
     * 为隐藏软键盘做判断
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            if (null != getCurrentFocus() && null != getCurrentFocus().getWindowToken()) {
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 隐藏软键盘
     */
    protected void hideKeyboard() {
        if (null != getCurrentFocus() && null != getCurrentFocus().getWindowToken()) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //判断键盘是否弹出
    public boolean isKeyboard() {
        if (getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
            return true;
        }
        return false;
    }

    //获取屏幕宽度
    public int getScreenWidth() {
        return this.getWindowManager().getDefaultDisplay().getWidth();
    }

    //获取屏幕高度
    public int getScreenHeight() {
        return this.getWindowManager().getDefaultDisplay().getHeight();
    }

    // MD5加密，32位
    public static String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    //判断手机格式是否正确,false代表格式正确
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(17[6-8])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
