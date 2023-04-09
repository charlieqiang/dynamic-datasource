package cn.charlie.dynamicdatasource.service;

import cn.charlie.dynamicdatasource.entity.BaseInfo;

import java.util.List;

/**
 * @author charlie
 * @date 4/9/2023 2:45 PM
 **/
public interface BaseService {

    /**
     * 查询
     *
     * @param baseId id
     * @return 结果集
     */
    List<BaseInfo> queryById(Long baseId);

    /**
     * 查询
     *
     * @param baseId id
     * @return 结果集
     */
    List<BaseInfo> queryByIdInPg(Long baseId);
}
