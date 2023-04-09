package cn.charlie.dynamicdatasource.mapper;

import cn.charlie.dynamicdatasource.entity.BaseInfo;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * @author charlie
 * @date 3/23/2023 8:08 PM
 **/
public interface BaseMapper {

    /**
     * 查询
     *
     * @param baseId id
     * @return 结果集
     */
    List<BaseInfo> queryById(Long baseId);
}
