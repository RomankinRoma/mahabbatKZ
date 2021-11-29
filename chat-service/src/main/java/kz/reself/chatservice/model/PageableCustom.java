package kz.reself.chatservice.model;

import lombok.Data;

@Data
public class PageableCustom {
    private Integer page;
    private Integer size;
    private Long total;
    private Object content;
}
