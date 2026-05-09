package net.hwyz.iov.cloud.edd.dictionary.api.service;

import net.hwyz.iov.cloud.edd.dictionary.api.fallback.DictionaryServiceFallbackFactory;
import net.hwyz.iov.cloud.edd.dictionary.api.vo.response.DictionaryResponse;
import net.hwyz.iov.cloud.framework.common.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "dictionaryService", value = ServiceNameConstants.EDD_DICTIONARY, path = "/api/service/dictionary/v1", fallbackFactory = DictionaryServiceFallbackFactory.class)
public interface DictionaryService {

    @GetMapping(value = "/{code}")
    DictionaryResponse getDictionary(@PathVariable("code") String code);

}