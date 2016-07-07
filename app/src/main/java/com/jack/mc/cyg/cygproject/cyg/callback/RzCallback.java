package com.jack.mc.cyg.cygproject.cyg.callback;

/**
 * 回调机制
 */
public interface RzCallback<T> {

    /**
     * 成功
     * @param data 数据
     */
    void onSuccess(T data);

    /**
     * 错误
     * @param failure 失败信息
     */
    void onFailure(String failure);
}
