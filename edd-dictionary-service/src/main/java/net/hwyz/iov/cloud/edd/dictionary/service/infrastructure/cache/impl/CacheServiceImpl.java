package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.cache.impl;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.cache.CacheService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 缓存服务实现类
 *
 * @author hwyz_leo
 */
@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * Redis Key前缀：数据字典
     */
    private static final String REDIS_KEY_PREFIX_DICTIONARY = "dictionary:code:";

    @Override
    public Optional<List<Map<String, Object>>> getDictionary(String code) {
        String dictionaryJson = redisTemplate.opsForValue().get(REDIS_KEY_PREFIX_DICTIONARY + code);
        if (StrUtil.isNotBlank(dictionaryJson)) {
            return Optional.of(JSONUtil.toBean(dictionaryJson, new TypeReference<>() {
            }, true));
        }
        return Optional.empty();
    }

    @Override
    public void setDictionary(String code, List<Map<String, Object>> dictionary) {
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX_DICTIONARY + code, JSONUtil.parse(dictionary).toJSONString(0));
    }
}
