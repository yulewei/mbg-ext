package org.mybatis.ext.base;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * MyBatis Generator (MBG) 通用 Mapper 基类
 *
 * @author yulewei
 */
public interface BaseMapper<T, E> {

    long countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(Serializable id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExampleWithBLOBs(E example);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(Serializable id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExampleWithBLOBs(E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKeyWithBLOBs(E example);

    int updateByPrimaryKey(T record);

}
