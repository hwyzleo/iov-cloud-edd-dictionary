package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.controller.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.api.vo.response.DictionaryResponse;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.assembler.DictionaryWebAssembler;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.result.DictionaryResult;
import net.hwyz.iov.cloud.edd.dictionary.service.application.service.DictionaryAppService;
import net.hwyz.iov.cloud.framework.web.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/service/dictionary/v1")
public class DictionaryServiceController extends BaseController {

    private final DictionaryAppService dictionaryAppService;

    @GetMapping(value = "/{code}")
    public DictionaryResponse getDictionary(@PathVariable("code") String code) {
        log.info("外部服务根据字典代码[{}]获取数据字典", code);
        DictionaryResult result = dictionaryAppService.getDictionary(code);
        return DictionaryWebAssembler.INSTANCE.toApiResponse(result);
    }
}