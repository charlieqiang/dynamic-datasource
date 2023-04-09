package cn.charlie.dynamicdatasource.service;

import cn.charlie.dynamicdatasource.entity.BaseInfo;
import cn.charlie.dynamicdatasource.mapper.BaseMapper;
import cn.charlie.dynamicdatasource.mapper.PgBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author charlie
 * @date 4/9/2023 2:45 PM
 **/
@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private PgBaseMapper pgBaseMapper;
    @Autowired
    private BaseMapper baseMapper;

    @Override
    public List<BaseInfo> queryById(Long baseId) {
        if (ObjectUtils.isEmpty(baseId)) {
            throw new RuntimeException("参数不能为空");
        }
        return baseMapper.queryById(baseId);
    }

    @Override
    public List<BaseInfo> queryByIdInPg(Long baseId) {
        if (ObjectUtils.isEmpty(baseId)) {
            throw new RuntimeException("参数不能为空");
        }
        return pgBaseMapper.queryByIdInPg(baseId);
    }
}
