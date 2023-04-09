package cn.charlie.dynamicdatasource.service;

import cn.charlie.dynamicdatasource.datasource.DS;
import cn.charlie.dynamicdatasource.datasource.DSType;
import cn.charlie.dynamicdatasource.entity.BaseInfo;
import cn.charlie.dynamicdatasource.entity.BaseInfoParam;
import cn.charlie.dynamicdatasource.mapper.BaseMapper;
import cn.charlie.dynamicdatasource.mapper.PgBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    @DS(DSType.PG)
    @Transactional(rollbackFor = Exception.class)
    public BaseInfo saveToPg(BaseInfoParam baseInfoParam) throws Exception {
        if (ObjectUtils.isEmpty(baseInfoParam)) {
            throw new RuntimeException("参数不等为空");
        }
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setBaseId(baseInfoParam.getBaseId());
        baseInfo.setBaseCode(baseInfoParam.getBaseCode());
        int affectRows = pgBaseMapper.saveToPg(baseInfo);
        if (affectRows <= 0) {
            throw new RuntimeException("保存失败");
        }
        int i = 1 / 0;
        return baseInfo;
    }
}
