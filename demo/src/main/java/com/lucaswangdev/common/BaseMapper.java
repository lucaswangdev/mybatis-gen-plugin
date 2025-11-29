package com.lucaswangdev.common;

import java.util.List;

public interface BaseMapper<P, D> {
    /**
     * 根据主键查询单条记录
     * @param param
     * @return
     */
    D getSingleById(P param);

    /**
     * 根据主键查询单条记录
     * @param id
     * @return
     */
    D getById(Object id);

    /**
     * 新增记录
     * @param entity
     * @return
     */
    Long insert(D entity);

    /**
     * 批量新增记录
     * @param entity
     */
    void batchInsert(List<D> entity);

    /**
     * 更新记录
     * @param entity
     * @return
     */
    Long update(D entity);

    /**
     * 保存记录，如果存在，则更新记录
     * @param entity
     */
    void save(D entity);

    /**
     * 删除记录
     * @param id
     * @return
     */
    Long deleteById(Object id);

    /**
     * 根据条件查询单条记录
     * @param param
     * @return
     */
    D querySingleByParam(P param);


    /**
     * 根据条件查询记录
     * @param param
     * @return
     */
    List<D> queryListByParam(P param);


    /**
     * 根据条件查询记录数
     * @param param
     * @return
     */
    Long queryCountByParam(P param);
}