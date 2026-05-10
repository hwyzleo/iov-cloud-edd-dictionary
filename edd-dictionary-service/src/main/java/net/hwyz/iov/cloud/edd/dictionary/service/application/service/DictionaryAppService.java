package net.hwyz.iov.cloud.edd.dictionary.service.application.service;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.result.DictionaryResult;
import net.hwyz.iov.cloud.edd.dictionary.service.application.assembler.DictionaryAssembler;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.query.DictionaryQuery;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.Dictionary;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryCategoryRepository;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryColumnRepository;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryRepository;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.cache.CacheService;
import net.hwyz.iov.cloud.framework.common.util.ParamHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictionaryAppService {

    private final CacheService cacheService;
    private final DictionaryRepository dictionaryRepository;
    private final DictionaryCategoryRepository dictionaryCategoryRepository;
    private final DictionaryColumnRepository dictionaryColumnRepository;

    public void createDictionary(DictionaryCmd cmd) {
        Dictionary dictionary = DictionaryAssembler.INSTANCE.toDomain(cmd);
        dictionary.enable();
        dictionary.updateSort(99);
        dictionaryRepository.save(dictionary);
    }

    public DictionaryResult getDictionary(String code) {
        List<Map<String, Object>> cachedItems = cacheService.getDictionary(code).orElse(null);
        if (cachedItems != null) {
            return DictionaryResult.builder()
                    .code(code)
                    .items(cachedItems)
                    .build();
        }

        Dictionary dictionary = dictionaryRepository.findByCode(code).orElse(null);
        if (dictionary == null) {
            return null;
        }

        Map<String, String> columns = new HashMap<>();
        JSONUtil.parseArray(dictionary.getSelectColumn()).forEach(item -> {
            String targetColumn = ((JSONObject) item).getStr("targetColumn");
            String aliasName = ((JSONObject) item).getStr("aliasName");
            if (StrUtil.isBlank(aliasName)) {
                aliasName = targetColumn;
            }
            columns.put(targetColumn, aliasName);
        });

        String whereClause = buildWhereClause(dictionary.getWhereCondition());
        
        List<Map<String, Object>> items = dictionaryCategoryRepository.selectTableListWithWhere(
                dictionary.getCategoryCode(),
                columns,
                whereClause
        );

        cacheService.setDictionary(code, items);

        return DictionaryResult.builder()
                .code(code)
                .name(dictionary.getName())
                .items(items)
                .build();
    }

    public List<Dictionary> search(DictionaryQuery query) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", ParamHelper.fuzzyQueryParam(query.getName()));
        map.put("code", query.getCode());
        map.put("categoryCode", query.getCategoryCode());
        map.put("beginTime", query.getBeginTime());
        map.put("endTime", query.getEndTime());
        return dictionaryRepository.findByMap(map);
    }

    public Dictionary getById(Long id) {
        return dictionaryRepository.findById(id);
    }

    public Boolean checkCodeUnique(Long id, String code) {
        if (ObjUtil.isNull(id)) {
            id = -1L;
        }
        Dictionary dictionary = dictionaryRepository.findByCode(code).orElse(null);
        return !ObjUtil.isNotNull(dictionary) || dictionary.getId().longValue() == id.longValue();
    }

    public void create(DictionaryCmd cmd, String userId) {
        Dictionary dictionary = DictionaryAssembler.INSTANCE.toDomain(cmd);
        dictionary.enable();
        dictionary.updateSort(99);
        dictionaryRepository.save(dictionary);
    }

    public int update(DictionaryCmd cmd, String userId) {
        Dictionary dictionary = DictionaryAssembler.INSTANCE.toDomain(cmd);
        return dictionaryRepository.update(dictionary);
    }

    public int deleteByIds(Long[] ids) {
        return dictionaryRepository.batchDelete(ids);
    }

    public List<Map<String, Object>> queryData(Long dictionaryId) {
        Dictionary dictionary = dictionaryRepository.findById(dictionaryId);
        if (dictionary == null) {
            throw new RuntimeException("字典不存在");
        }

        if (StrUtil.isBlank(dictionary.getSelectColumn())) {
            throw new RuntimeException("字典未配置查询列");
        }

        Map<String, String> columnMap = new HashMap<>();
        // 添加 id 字段
        columnMap.put("id", "id");
        
        JSONUtil.parseArray(dictionary.getSelectColumn()).forEach(item -> {
            JSONObject selectItem = (JSONObject) item;
            String targetColumn = selectItem.getStr("targetColumn");
            String aliasName = selectItem.getStr("aliasName");
            if (StrUtil.isBlank(aliasName)) {
                aliasName = targetColumn;
            }
            columnMap.put(targetColumn, aliasName);
        });

        String whereClause = buildWhereClause(dictionary.getWhereCondition());
        
        return dictionaryCategoryRepository.selectTableListWithWhere(dictionary.getCategoryCode(), columnMap, whereClause);
    }

    private String buildWhereClause(String whereCondition) {
        if (StrUtil.isBlank(whereCondition)) {
            return null;
        }

        try {
            StringBuilder whereClause = new StringBuilder();
            cn.hutool.json.JSONArray conditionsArray = JSONUtil.parseArray(whereCondition);
            
            for (int i = 0; i < conditionsArray.size(); i++) {
                JSONObject condition = conditionsArray.getJSONObject(i);
                String connector = condition.getStr("connector");
                if (StrUtil.isBlank(connector)) {
                    connector = "AND";
                }
                String field = condition.getStr("field");
                String operator = condition.getStr("operator");
                if (StrUtil.isBlank(operator)) {
                    operator = "=";
                }
                Object value = condition.get("value");

                if (StrUtil.isBlank(field)) {
                    continue;
                }

                if (i > 0) {
                    whereClause.append(" ").append(connector).append(" ");
                }

                whereClause.append(field);

                if ("is null".equalsIgnoreCase(operator)) {
                    whereClause.append(" IS NULL");
                } else if ("is not null".equalsIgnoreCase(operator)) {
                    whereClause.append(" IS NOT NULL");
                } else {
                    whereClause.append(" ").append(operator).append(" ");
                    
                    if ("like".equalsIgnoreCase(operator) || "not like".equalsIgnoreCase(operator)) {
                        whereClause.append("'").append(value).append("'");
                    } else {
                        whereClause.append("'").append(value).append("'");
                    }
                }
            }

            return whereClause.toString();
        } catch (Exception e) {
            log.error("解析查询条件失败: {}", whereCondition, e);
            return null;
        }
    }

    public Map<String, Object> getDataById(Long dictionaryId, Long dataId) {
        Dictionary dictionary = dictionaryRepository.findById(dictionaryId);
        if (dictionary == null) {
            throw new RuntimeException("字典不存在");
        }

        DictionaryCategory category = dictionaryCategoryRepository.findByCode(dictionary.getCategoryCode()).orElse(null);
        if (category == null) {
            throw new RuntimeException("字典分类不存在");
        }

        List<DictionaryColumn> columns = dictionaryColumnRepository.findByCategoryId(category.getId());
        Map<String, String> columnMap = new HashMap<>();
        columns.forEach(col -> {
            columnMap.put(col.getCode(), col.getCode()); // 使用原始字段名，不使用别名
        });

        return dictionaryCategoryRepository.selectTableById(dictionary.getCategoryCode(), columnMap, dataId);
    }

    public void createData(Long dictionaryId, Map<String, Object> data) {
        Dictionary dictionary = dictionaryRepository.findById(dictionaryId);
        if (dictionary == null) {
            throw new RuntimeException("字典不存在");
        }
        dictionaryCategoryRepository.insertTableData(dictionary.getCategoryCode(), data);
    }

    public void updateData(Long dictionaryId, Long dataId, Map<String, Object> data) {
        Dictionary dictionary = dictionaryRepository.findById(dictionaryId);
        if (dictionary == null) {
            throw new RuntimeException("字典不存在");
        }
        dictionaryCategoryRepository.updateTableData(dictionary.getCategoryCode(), dataId, data);
    }

    public void deleteData(Long dictionaryId, Long dataId) {
        Dictionary dictionary = dictionaryRepository.findById(dictionaryId);
        if (dictionary == null) {
            throw new RuntimeException("字典不存在");
        }
        dictionaryCategoryRepository.deleteTableData(dictionary.getCategoryCode(), dataId);
    }

}