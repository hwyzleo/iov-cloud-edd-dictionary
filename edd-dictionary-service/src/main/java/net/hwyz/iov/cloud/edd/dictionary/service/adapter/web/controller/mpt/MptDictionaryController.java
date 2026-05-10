package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.controller.mpt;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.assembler.MptDictionaryAssembler;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.request.DictionaryRequest;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response.DictionaryResponse;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.query.DictionaryQuery;
import net.hwyz.iov.cloud.edd.dictionary.service.application.service.DictionaryAppService;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.Dictionary;
import net.hwyz.iov.cloud.framework.audit.annotation.Log;
import net.hwyz.iov.cloud.framework.audit.enums.BusinessType;
import net.hwyz.iov.cloud.framework.common.bean.ApiResponse;
import net.hwyz.iov.cloud.framework.common.bean.PageResult;
import net.hwyz.iov.cloud.framework.security.annotation.RequiresPermissions;
import net.hwyz.iov.cloud.framework.security.util.SecurityUtils;
import net.hwyz.iov.cloud.framework.web.context.SecurityContextHolder;
import net.hwyz.iov.cloud.framework.web.controller.BaseController;
import net.hwyz.iov.cloud.framework.web.util.PageUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/mpt/dictionary/v1")
public class MptDictionaryController extends BaseController {

    private final DictionaryAppService dictionaryAppService;

    @RequiresPermissions("system:dictionary:list")
    @GetMapping(value = "/list")
    public ApiResponse<PageResult<DictionaryResponse>> list(DictionaryRequest request) {
        log.info("管理后台用户[{}]分页查询数据字典信息", SecurityContextHolder.getUserName());
        startPage();
        DictionaryQuery query = DictionaryQuery.builder()
                .name(request.getName())
                .code(request.getCode())
                .categoryCode(request.getCategoryCode())
                .beginTime(getBeginTime(request))
                .endTime(getEndTime(request))
                .build();
        List<Dictionary> dictionaryList = dictionaryAppService.search(query);
        return ApiResponse.ok(getPageResult(PageUtil.convert(dictionaryList, MptDictionaryAssembler.INSTANCE::fromDomain)));
    }

    @Log(title = "数据字典管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dictionary:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, DictionaryRequest request) {
        log.info("管理后台用户[{}]导出数据字典信息", SecurityContextHolder.getUserName());
    }

    @RequiresPermissions("system:dictionary:query")
    @GetMapping(value = "/{id}")
    public ApiResponse<DictionaryResponse> getInfo(@PathVariable Long id) {
        log.info("管理后台用户[{}]根据数据字典ID[{}]获取数据字典信息", SecurityContextHolder.getUserName(), id);
        return ApiResponse.ok(MptDictionaryAssembler.INSTANCE.fromDomain(dictionaryAppService.getById(id)));
    }

    @Log(title = "数据字典管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dictionary:add")
    @PostMapping
    public ApiResponse<Void> add(@Validated @RequestBody DictionaryRequest request) {
        log.info("管理后台用户[{}]新增数据字典信息[{}]", SecurityContextHolder.getUserName(), request.getCode());
        if (!dictionaryAppService.checkCodeUnique(request.getId(), request.getCode())) {
            return ApiResponse.fail("新增数据字典'" + request.getCode() + "'失败，字典代码已存在");
        }
        dictionaryAppService.create(MptDictionaryAssembler.INSTANCE.toCmd(request), SecurityUtils.getUserId().toString());
        return ApiResponse.ok();
    }

    @Log(title = "数据字典管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dictionary:edit")
    @PutMapping
    public ApiResponse<Void> edit(@Validated @RequestBody DictionaryRequest request) {
        log.info("管理后台用户[{}]修改保存数据字典信息[{}]", SecurityContextHolder.getUserName(), request.getCode());
        if (!dictionaryAppService.checkCodeUnique(request.getId(), request.getCode())) {
            return ApiResponse.fail("修改保存数据字典'" + request.getCode() + "'失败，字典代码已存在");
        }
        dictionaryAppService.update(MptDictionaryAssembler.INSTANCE.toCmd(request), SecurityUtils.getUserId().toString());
        return ApiResponse.ok();
    }

    @Log(title = "数据字典管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dictionary:remove")
    @DeleteMapping("/{ids}")
    public ApiResponse<Void> remove(@PathVariable Long[] ids) {
        log.info("管理后台用户[{}]删除数据字典信息[{}]", SecurityContextHolder.getUserName(), ids);
        return dictionaryAppService.deleteByIds(ids) > 0 ? ApiResponse.ok() : ApiResponse.fail("删除失败");
    }

    @RequiresPermissions("system:dictionary:query")
    @GetMapping("/{id}/data")
    public ApiResponse<List<Map<String, Object>>> getData(@PathVariable Long id) {
        log.info("管理后台用户[{}]查询字典[{}]数据", SecurityContextHolder.getUserName(), id);
        try {
            List<Map<String, Object>> data = dictionaryAppService.queryData(id);
            return ApiResponse.ok(data);
        } catch (Exception e) {
            log.error("查询字典数据失败", e);
            return ApiResponse.fail(e.getMessage());
        }
    }

    @RequiresPermissions("system:dictionaryData:query")
    @GetMapping("/{id}/data/{dataId}")
    public ApiResponse<Map<String, Object>> getDataInfo(@PathVariable Long id, @PathVariable Long dataId) {
        log.info("管理后台用户[{}]查询字典[{}]数据详情[{}]", SecurityContextHolder.getUserName(), id, dataId);
        try {
            Map<String, Object> data = dictionaryAppService.getDataById(id, dataId);
            return ApiResponse.ok(data);
        } catch (Exception e) {
            log.error("查询字典数据详情失败", e);
            return ApiResponse.fail(e.getMessage());
        }
    }

    @Log(title = "字典数据管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dictionaryData:add")
    @PostMapping("/{id}/data")
    public ApiResponse<Void> addData(@PathVariable Long id, @RequestBody Map<String, Object> data) {
        log.info("管理后台用户[{}]新增字典[{}]数据", SecurityContextHolder.getUserName(), id);
        try {
            dictionaryAppService.createData(id, data);
            return ApiResponse.ok();
        } catch (Exception e) {
            log.error("新增字典数据失败", e);
            return ApiResponse.fail(e.getMessage());
        }
    }

    @Log(title = "字典数据管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dictionaryData:edit")
    @PutMapping("/{id}/data/{dataId}")
    public ApiResponse<Void> updateData(@PathVariable Long id, @PathVariable Long dataId, @RequestBody Map<String, Object> data) {
        log.info("管理后台用户[{}]修改字典[{}]数据[{}]", SecurityContextHolder.getUserName(), id, dataId);
        try {
            dictionaryAppService.updateData(id, dataId, data);
            return ApiResponse.ok();
        } catch (Exception e) {
            log.error("修改字典数据失败", e);
            return ApiResponse.fail(e.getMessage());
        }
    }

    @Log(title = "字典数据管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dictionaryData:remove")
    @DeleteMapping("/{id}/data/{dataId}")
    public ApiResponse<Void> removeData(@PathVariable Long id, @PathVariable Long dataId) {
        log.info("管理后台用户[{}]删除字典[{}]数据[{}]", SecurityContextHolder.getUserName(), id, dataId);
        try {
            dictionaryAppService.deleteData(id, dataId);
            return ApiResponse.ok();
        } catch (Exception e) {
            log.error("删除字典数据失败", e);
            return ApiResponse.fail(e.getMessage());
        }
    }

}