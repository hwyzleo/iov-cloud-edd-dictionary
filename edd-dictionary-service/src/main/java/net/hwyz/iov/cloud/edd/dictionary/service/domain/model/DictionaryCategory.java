package net.hwyz.iov.cloud.edd.dictionary.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryCategory {

    private Long id;
    private Long pid;
    private String name;
    private String code;
    private String type;
    private Boolean enable;
    private Integer sort;
    private List<DictionaryColumn> columns;

    public boolean isEnabled() {
        return Boolean.TRUE.equals(this.enable);
    }

    public void enable() {
        this.enable = true;
    }

    public void disable() {
        this.enable = false;
    }

    public void updateSort(Integer newSort) {
        if (newSort != null && newSort >= 0) {
            this.sort = newSort;
        }
    }
}