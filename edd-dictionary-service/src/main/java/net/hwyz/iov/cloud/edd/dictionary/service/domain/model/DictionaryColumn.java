package net.hwyz.iov.cloud.edd.dictionary.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryColumn {

    private Long id;
    private Long categoryId;
    private String name;
    private String code;
    private String type;
    private Integer len;
    private Boolean nullable;
    private Boolean uniq;
    private Integer valueType;
    private String valueRange;
    private Integer sort;

    public boolean isUnique() {
        return Boolean.TRUE.equals(this.uniq);
    }

    public boolean isNullable() {
        return Boolean.TRUE.equals(this.nullable);
    }
}