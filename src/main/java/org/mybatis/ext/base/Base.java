package org.mybatis.ext.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public abstract class Base implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}