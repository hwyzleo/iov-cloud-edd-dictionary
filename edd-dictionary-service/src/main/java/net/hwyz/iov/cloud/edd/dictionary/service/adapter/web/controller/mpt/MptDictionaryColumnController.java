package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.controller.mpt;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.assembler.MptDictionaryColumnAssembler;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.request.DictionaryColumnRequest;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response.DictionaryColumnResponse;
import net.hwyz.iov.cloud.edd.dictionary.service.application.service.DictionaryColumnAppService;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/mpt/dictionaryCategory/v1/{categoryId}/column")
public class MptDictionaryColumnController extends BaseController {

    private final DictionaryColumnAppService dictionaryColumnAppService;

    @RequiresPermissions("system:dictionaryColumn:list")
    @GetMapping(value = "/list")
    public ApiResponse<PageResult<DictionaryColumnResponse>> list(@PathVariable Long categoryId) {
        log.info("管理后台用户[{}]查询字典分类[{}]字段信息", SecurityContextHolder.getUserName(), categoryId);
        startPage();
        List<DictionaryColumn> columnList = dictionaryColumnAppService.findByCategoryId(categoryId);
        return ApiResponse.ok(getPageResult(PageUtil.convert(columnList, MptDictionaryColumnAssembler.INSTANCE::fromDomain)));
    }

    @RequiresPermissions("system:dictionaryColumn:query")
    @GetMapping(value = "/{id}")
    public ApiResponse<DictionaryColumnResponse> getInfo(@PathVariable Long categoryId, @PathVariable Long id) {
        log.info("管理后台用户[{}]根据字段ID[{}]获取字段信息", SecurityContextHolder.getUserName(), id);
        return ApiResponse.ok(MptDictionaryColumnAssembler.INSTANCE.fromDomain(dictionaryColumnAppService.getById(id)));
    }

    @Log(title = "字典字段管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dictionaryColumn:add")
    @PostMapping
    public ApiResponse<Void> add(@PathVariable Long categoryId, @Validated @RequestBody DictionaryColumnRequest request) {
        log.info("管理后台用户[{}]新增字典分类[{}]字段信息[{}]", SecurityContextHolder.getUserName(), categoryId, request.getCode());
        request.setCategoryId(categoryId);
        dictionaryColumnAppService.create(MptDictionaryColumnAssembler.INSTANCE.toCmd(request), SecurityUtils.getUserId().toString());
        return ApiResponse.ok();
    }

    @Log(title = "字典字段管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dictionaryColumn:edit")
    @PutMapping
    public ApiResponse<Void> edit(@PathVariable Long categoryId, @Validated @RequestBody DictionaryColumnRequest request) {
        log.info("管理后台用户[{}]修改保存字典分类[{}]字段信息[{}]", SecurityContextHolder.getUserName(), categoryId, request.getCode());
        request.setCategoryId(categoryId);
        dictionaryColumnAppService.update(MptDictionaryColumnAssembler.INSTANCE.toCmd(request), SecurityUtils.getUserId().toString());
        return ApiResponse.ok();
    }

    @Log(title = "字典字段管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dictionaryColumn:remove")
    @DeleteMapping("/{ids}")
    public ApiResponse<Void> remove(@PathVariable Long categoryId, @PathVariable Long[] ids) {
        log.info("管理后台用户[{}]删除字典分类[{}]字段信息[{}]", SecurityContextHolder.getUserName(), categoryId, ids);
        return dictionaryColumnAppService.deleteByIds(ids) > 0 ? ApiResponse.ok() : ApiResponse.fail("删除失败");
    }

    @Log(title = "字典字段管理", businessType = BusinessType.OTHER)
    @RequiresPermissions("system:dictionaryColumn:generate")
    @PostMapping("/generateTable")
    public ApiResponse<Void> generateTable(@PathVariable Long categoryId) {
        log.info("管理后台用户[{}]生成字典分类[{}]数据表", SecurityContextHolder.getUserName(), categoryId);
        try {
            dictionaryColumnAppService.generateTable(categoryId);
            return ApiResponse.ok();
        } catch (Exception e) {
            log.error("生成数据表失败", e);
            return ApiResponse.fail(e.getMessage());
        }
    }

}