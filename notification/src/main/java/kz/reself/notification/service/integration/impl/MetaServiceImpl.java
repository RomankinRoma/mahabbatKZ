package kz.reself.notification.service.integration.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.reself.notification.pojo.FormData;
import kz.reself.notification.pojo.FormVersDTO;
import kz.reself.notification.pojo.FormVersionDTO;
import kz.reself.notification.pojo.templates.RestClientTemplate;
import kz.reself.notification.service.integration.IMetaService;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//MICROSERVICE COMMUNICATION WITH JSON PARSE
public class MetaServiceImpl implements IMetaService {

    @Value("${integration-service.metadata}")
    private String metadata;

    @Override
    public void getForms(Long formVersId, String lang) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String uri = formVersId + "/" + lang;
        RestClientTemplate restClientTemplate = new RestClientTemplate(metadata + "/meta/formVers/atr/id/");
        String result = restClientTemplate.get(uri);
        JsonNode jsonRoot = mapper.readTree(result);
        JsonNode period = jsonRoot.get("obj");
//        JsonNode attrs = period.get("attrs");
        //can get as map only if json in key:value format
        Map<String, String> keyValueFormatJson = mapper.convertValue(period, new TypeReference<Map<String, String>>() {});
        keyValueFormatJson.get("0001");

        //else you should create pojo
        FormVersDTO res = mapper.readValue(period.toString(), FormVersDTO.class);
        System.out.println("RESSSSSSSSSSSSSSS--------> "+res.getAttrs().get("0004"));
//        return mapper.readValue(attrs.toString(), JsonObject.class).toString();
    }

    @Override
    public List<FormVersionDTO> getFormVers(Long formId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String uri = formId.toString();
        RestClientTemplate restClientTemplate = new RestClientTemplate(metadata + "/meta/form/formVers/");
        String result = restClientTemplate.get(uri);
        JsonNode jsonRoot = mapper.readTree(result);
        //to get json with list/map/...
        return mapper.convertValue(jsonRoot, new TypeReference<List<FormVersionDTO>>() {});
    }

    @Override
    public FormData getFormName(Long formId, String lang) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String uri = formId.toString() + "/" + lang;
        RestClientTemplate restClientTemplate = new RestClientTemplate(metadata + "/meta/form/id/");
        String result = restClientTemplate.get(uri);
        JsonNode jsonRoot = mapper.readTree(result);
        JsonNode form = jsonRoot.get("obj");
        return mapper.readValue(form.toString(), FormData.class);
    }
}
