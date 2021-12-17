package kz.reself.notification.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class FormVersionDTO {
    private Long id;
    private Long formId;
    private Date dbeg;
    private Date dend;
    private Date dateCreate;
    private Date dateUpdate;
    private String userUpdate;
    private String name;
}
