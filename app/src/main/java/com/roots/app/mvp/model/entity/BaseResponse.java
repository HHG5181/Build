package com.roots.app.mvp.model.entity;

import com.roots.app.mvp.http.api.Api;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @Classname BaseResponse
 * @Description TODO
 * @Date 2020/8/25 18:04
 * @Created by bird
 */
@Getter
@Setter
public class BaseResponse<T> implements Serializable {
    private int code;
    private T data;
    private int status;
    private String msg;

    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (code == Api.RequestSuccess) {
            return true;
        } else {
            return false;
        }
    }

}
