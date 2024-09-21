package org.mybatis.ext.base;

import java.io.Serializable;

/**
 * MyBatis Generator (MBG) 通用 Mapper 基类
 *
 * @author yulewei
 */
public interface SimpleBaseMapper<T> {

    int deleteByPrimaryKey(Serializable id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Serializable id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
