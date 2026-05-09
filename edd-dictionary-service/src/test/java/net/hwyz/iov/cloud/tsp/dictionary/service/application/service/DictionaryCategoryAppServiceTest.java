package net.hwyz.iov.cloud.tsp.dictionary.service.application.service;

import net.hwyz.iov.cloud.edd.dictionary.api.vo.request.DictionaryCategoryRequest;
import net.hwyz.iov.cloud.edd.dictionary.api.vo.request.DictionaryColumnRequest;
import net.hwyz.iov.cloud.edd.dictionary.service.application.service.DictionaryCategoryAppService;
import net.hwyz.iov.cloud.tsp.dictionary.service.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典分类应用服务测试类
 *
 * @author hwyz_leo
 */
public class DictionaryCategoryAppServiceTest extends BaseTest {

    @Autowired
    private DictionaryCategoryAppService dictionaryCategoryAppService;

    @Test
    @Order(1)
    @DisplayName("创建分类")
    public void testCreateCategory() throws Exception {
        List<DictionaryColumnRequest> columns = new ArrayList<>();
        columns.add(DictionaryColumnRequest.builder()
                .name("地区级行政区代码")
                .code("city_code")
                .type("VARCHAR")
                .len(255)
                .nullable(false)
                .uniq(false)
                .valueType(1)
                .valueRange("{'targetTable':'city', 'joinColumn':'code', 'displayColumn':'name'}")
                .build());
        columns.add(DictionaryColumnRequest.builder()
                .name("名称")
                .code("name")
                .type("VARCHAR")
                .len(255)
                .nullable(false)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnRequest.builder()
                .name("代码")
                .code("code")
                .type("VARCHAR")
                .len(255)
                .nullable(false)
                .uniq(true)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnRequest.builder()
                .name("中心经度")
                .code("center_lon")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnRequest.builder()
                .name("中心纬度")
                .code("center_lat")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnRequest.builder()
                .name("最大经度")
                .code("max_lon")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnRequest.builder()
                .name("最大纬度")
                .code("max_lat")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnRequest.builder()
                .name("最小经度")
                .code("min_lon")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        columns.add(DictionaryColumnRequest.builder()
                .name("最小纬度")
                .code("min_lat")
                .type("VARCHAR")
                .len(255)
                .nullable(true)
                .uniq(false)
                .valueType(0)
                .build());
        DictionaryCategoryRequest request = DictionaryCategoryRequest.builder()
                .name("县级行政区")
                .code("county")
                .type("GLOBAL")
                .columns(columns)
                .build();
        dictionaryCategoryAppService.createCategory(request);
    }

}
