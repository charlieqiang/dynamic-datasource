package cn.charlie.dynamicdatasource.mapper;

import cn.charlie.dynamicdatasource.datasource.DS;
import cn.charlie.dynamicdatasource.datasource.DSType;
import cn.charlie.dynamicdatasource.entity.BaseInfo;
import java.util.List;

/**
 * @author charlie
 * @date 4/9/2023 4:03 PM
 **/
public interface PgBaseMapper {

    /**
     * 查询
     *
     * @param baseId id
     * @return 结果集
     */
    @DS(DSType.PG)
    List<BaseInfo> queryByIdInPg(Long baseId);
}
