package net.hwyz.iov.cloud.edd.dictionary.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryCategoryCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.application.assembler.DictionaryAssembler;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictionaryCategoryAppService {

    private final DictionaryCategoryRepository dictionaryCategoryRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createCategory(DictionaryCategoryCmd cmd) {
        DictionaryCategory category = DictionaryAssembler.INSTANCE.toDomain(cmd);
        category.enable();
        category.updateSort(99);

        List<DictionaryColumn> columns = DictionaryAssembler.INSTANCE.toDomainList(cmd.getColumns());
        List<String> uniqueColumns = new ArrayList<>();
        columns.stream()
                .filter(DictionaryColumn::isUnique)
                .forEach(column -> uniqueColumns.add(column.getCode()));

        dictionaryCategoryRepository.saveWithColumns(category, columns, uniqueColumns);
        dictionaryCategoryRepository.createTable(cmd.getCode(), cmd.getName(), columns, uniqueColumns);
    }
}