package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.controller.mpt;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.assembler.MptDictionaryCategoryAssembler;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.request.DictionaryCategoryRequest;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response.DictionaryCategoryResponse;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.query.DictionaryCategoryQuery;
import net.hwyz.iov.cloud.edd.dictionary.service.application.service.DictionaryCategoryAppService;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
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
@RequestMapping(value = "/api/mpt/dictionaryCategory/v1")
public class MptDictionaryCategoryController extends BaseController {

    private final DictionaryCategoryAppService dictionaryCategoryAppService;

    @RequiresPermissions("system:dictionaryCategory:list")
    @GetMapping(value = "/list")
    public ApiResponse<PageResult<DictionaryCategoryResponse>> list(DictionaryCategoryRequest request) {
        log.info("管理后台用户[{}]分页查询数据字典分类信息", SecurityContextHolder.getUserName());
        startPage();
        DictionaryCategoryQuery query = DictionaryCategoryQuery.builder()
                .name(request.getName())
                .code(request.getCode())
                .type(request.getType())
                .beginTime(getBeginTime(request))
                .endTime(getEndTime(request))
                .build();
        List<DictionaryCategory> categoryList = dictionaryCategoryAppService.search(query);
        return ApiResponse.ok(getPageResult(PageUtil.convert(categoryList, MptDictionaryCategoryAssembler.INSTANCE::fromDomain)));
    }

    @Log(title = "数据字典分类管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dictionaryCategory:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, DictionaryCategoryRequest request) {
        log.info("管理后台用户[{}]导出数据字典分类信息", SecurityContextHolder.getUserName());
    }

    @RequiresPermissions("system:dictionaryCategory:query")
    @GetMapping(value = "/{id}")
    public ApiResponse<DictionaryCategoryResponse> getInfo(@PathVariable Long id) {
        log.info("管理后台用户[{}]根据数据字典分类ID[{}]获取数据字典分类信息", SecurityContextHolder.getUserName(), id);
        return ApiResponse.ok(MptDictionaryCategoryAssembler.INSTANCE.fromDomain(dictionaryCategoryAppService.getById(id)));
    }

    @RequiresPermissions("system:dictionaryCategory:query")
    @GetMapping(value = "/code/{code}")
    public ApiResponse<DictionaryCategoryResponse> getInfoByCode(@PathVariable String code) {
        log.info("管理后台用户[{}]根据数据字典分类代码[{}]获取数据字典分类信息", SecurityContextHolder.getUserName(), code);
        return ApiResponse.ok(MptDictionaryCategoryAssembler.INSTANCE.fromDomain(dictionaryCategoryAppService.getByCode(code)));
    }

    @Log(title = "数据字典分类管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dictionaryCategory:add")
    @PostMapping
    public ApiResponse<Void> add(@Validated @RequestBody DictionaryCategoryRequest request) {
        log.info("管理后台用户[{}]新增数据字典分类信息[{}]", SecurityContextHolder.getUserName(), request.getCode());
        if (!dictionaryCategoryAppService.checkCodeUnique(request.getId(), request.getCode())) {
            return ApiResponse.fail("新增数据字典分类'" + request.getCode() + "'失败，分类代码已存在");
        }
        dictionaryCategoryAppService.create(MptDictionaryCategoryAssembler.INSTANCE.toCmd(request), SecurityUtils.getUserId().toString());
        return ApiResponse.ok();
    }

    @Log(title = "数据字典分类管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dictionaryCategory:edit")
    @PutMapping
    public ApiResponse<Void> edit(@Validated @RequestBody DictionaryCategoryRequest request) {
        log.info("管理后台用户[{}]修改保存数据字典分类信息[{}]", SecurityContextHolder.getUserName(), request.getCode());
        if (!dictionaryCategoryAppService.checkCodeUnique(request.getId(), request.getCode())) {
            return ApiResponse.fail("修改保存数据字典分类'" + request.getCode() + "'失败，分类代码已存在");
        }
        dictionaryCategoryAppService.update(MptDictionaryCategoryAssembler.INSTANCE.toCmd(request), SecurityUtils.getUserId().toString());
        return ApiResponse.ok();
    }

    @Log(title = "数据字典分类管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dictionaryCategory:remove")
    @DeleteMapping("/{ids}")
    public ApiResponse<Void> remove(@PathVariable Long[] ids) {
        log.info("管理后台用户[{}]删除数据字典分类信息[{}]", SecurityContextHolder.getUserName(), ids);
        return dictionaryCategoryAppService.deleteByIds(ids) > 0 ? ApiResponse.ok() : ApiResponse.fail("删除失败");
    }

}