package net.hwyz.iov.cloud.edd.dictionary.service.application.dto.query;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class DictionaryCategoryQuery {

    private String name;

    private String code;

    private String type;

    private Date beginTime;

    private Date endTime;

}