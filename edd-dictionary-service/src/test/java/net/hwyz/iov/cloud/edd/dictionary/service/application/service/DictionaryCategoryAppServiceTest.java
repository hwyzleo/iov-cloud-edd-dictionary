package net.hwyz.iov.cloud.edd.dictionary.service.application.service;

import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryCategoryCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryColumnCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.application.service.DictionaryCategoryAppService;
import net.hwyz.iov.cloud.edd.dictionary.service.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DictionaryCategoryAppServiceTest extends BaseTest {

    @Autowired
    private DictionaryCategoryAppService dictionaryCategoryAppService;

    @Test
    @Order(1)
    @DisplayName("创建分类")
    public void testCreateCategory() throws Exception {
        List<DictionaryColumnCmd> columns = new ArrayList<>();
        columns.add(DictionaryColumnCmd.builder()
                .name("地区级行政区代码")
                .code("city_code")
                .type("VARCHAR")
                .len(255)
                .nullable(false)
                .uniq(false)
                .valueType(1)
                .valueRange("{'targetTable':'city', 'joinColumn':'code', 'displayColumn':'name'}")
                .build());
        columns.add(DictionaryColumnCmd.builder()
                .name("名称")
                .code("name")
                .type("VARCHAR")
                .len(255)
                .nullable(false)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnCmd.builder()
                .name("代码")
                .code("code")
                .type("VARCHAR")
                .len(255)
                .nullable(false)
                .uniq(true)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnCmd.builder()
                .name("中心经度")
                .code("center_lon")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnCmd.builder()
                .name("中心纬度")
                .code("center_lat")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnCmd.builder()
                .name("最大经度")
                .code("max_lon")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnCmd.builder()
                .name("最大纬度")
                .code("max_lat")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnCmd.builder()
                .name("最小经度")
                .code("min_lon")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnCmd.builder()
                .name("最小纬度")
                .code("min_lat")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        DictionaryCategoryCmd cmd = DictionaryCategoryCmd.builder()
                .name("县级行政区")
                .code("county")
                .type("GLOBAL")
                .columns(columns)
                .build();
        dictionaryCategoryAppService.createCategory(cmd);
    }

}