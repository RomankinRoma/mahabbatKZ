package kz.reself.notification.pojo;

import lombok.Data;

import java.util.List;

@Data
public class FormData {
    private Long id;
    private String code;
    private String name;
    private String order;
    private String index;
    private String indexName;
    private String periodicCode;
    private String periodic;
    private String periodicId;
    private Long periodicItemId;
    private String termString;
    private Long sectorId;
    private Long indexId;
    private String begDate;
    private String endDate;
    private List<RespType> respTypes;
    private boolean noNormaization;
    private boolean calcTerm;
}
