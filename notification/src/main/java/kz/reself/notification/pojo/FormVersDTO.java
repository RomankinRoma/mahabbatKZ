package kz.reself.notification.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class FormVersDTO implements Serializable {
    private static final long serialVersionUID = -1540856989317684891L;
    private long id;
    private long formId;
    private long dbeg;
    private long dend;
    private Map<String, String> attrs = new HashMap<String, String>();
}

