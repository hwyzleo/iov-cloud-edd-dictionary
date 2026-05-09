package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.controller.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.service.application.service.DictionaryAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 数据字典相关服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/service/dictionary/v1")
public class DictionaryServiceController {

    private final DictionaryAppService dictionaryAppService;

    /**
     * 根据字典代码获取数据字典
     *
     * @param code 字典代码
     * @return 数据字典
     */
    @GetMapping(value = "/{code}")
    public List<Map<String, Object>> getDictionaryMap(@PathVariable("code") String code) {
        log.info("外部服务根据字典代码[{}]获取数据字典", code);
        return dictionaryAppService.getDictionary(code);
    }
}
