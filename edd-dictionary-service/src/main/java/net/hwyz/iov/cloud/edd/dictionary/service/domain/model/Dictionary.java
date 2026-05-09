package net.hwyz.iov.cloud.edd.dictionary.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary {

    private Long id;
    private String name;
    private String code;
    private String categoryCode;
    private String selectColumn;
    private String whereCondition;
    private Boolean enable;
    private Integer sort;

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