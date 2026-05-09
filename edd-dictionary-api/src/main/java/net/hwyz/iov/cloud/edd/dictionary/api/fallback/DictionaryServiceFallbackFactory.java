package net.hwyz.iov.cloud.edd.dictionary.api.fallback;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.api.service.DictionaryService;
import net.hwyz.iov.cloud.edd.dictionary.api.vo.response.DictionaryResponse;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DictionaryServiceFallbackFactory implements FallbackFactory<DictionaryService> {

    @Override
    public DictionaryService create(Throwable throwable) {
        return new DictionaryService() {
            @Override
            public DictionaryResponse getDictionary(String code) {
                log.error("数据字典服务根据字典代码[{}]获取数据字典调用失败", code, throwable);
                return null;
            }
        };
    }
}