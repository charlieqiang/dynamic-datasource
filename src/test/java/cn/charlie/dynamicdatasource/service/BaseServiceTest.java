package cn.charlie.dynamicdatasource.service;

import cn.charlie.dynamicdatasource.entity.BaseInfo;
import cn.charlie.dynamicdatasource.entity.BaseInfoParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author charlie
 * @date 4/9/2023 2:52 PM
 **/
@SpringBootTest
public class BaseServiceTest {
    @Autowired
    private BaseService baseService;

    @Test
    public void testQueryById() {
        Long id = 1089284975455461376L;
        List<BaseInfo> baseInfoList = baseService.queryById(id);

        if (CollectionUtils.isEmpty(baseInfoList)) {
            throw new RuntimeException("查无结果");
        }

        BaseInfo baseInfo = baseInfoList.get(0);
        String baseCode = "2";
        Assertions.assertEquals(baseInfo.getBaseCode(), baseCode);
    }

    @Test
    public void testQueryByIdInPg() {
        Long id = 1089284975455461376L;
        List<BaseInfo> baseInfoList = baseService.queryByIdInPg(id);

        if (CollectionUtils.isEmpty(baseInfoList)) {
            throw new RuntimeException("查无结果");
        }

        BaseInfo baseInfo = baseInfoList.get(0);
        String baseCode = "3";
        Assertions.assertEquals(baseInfo.getBaseCode(), baseCode);
    }

    @Test
    public void testSaveToPg() throws Exception {
        Long id = 1089284975455461312L;
        String code = "6";
        BaseInfoParam baseInfoParam = new BaseInfoParam();
        baseInfoParam.setBaseId(id);
        baseInfoParam.setBaseCode(code);
        BaseInfo baseInfo = baseService.saveToPg(baseInfoParam);
        Assertions.assertEquals(baseInfo.getBaseId(), id);
        Assertions.assertEquals(baseInfo.getBaseCode(), code);
    }
}
