package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.cache;


import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 缓存服务接口
 *
 * @author hwyz_leo
 */
public interface CacheService {

    /**
     * 获取数据字典
     *
     * @param code 数据字典代码
     * @return 数据字典
     */
    Optional<List<Map<String, Object>>> getDictionary(String code);

    /**
     * 设置数据字典
     *
     * @param code       数据字典代码
     * @param dictionary 数据字典
     */
    void setDictionary(String code, List<Map<String, Object>> dictionary);

}
