package kz.reself.notification.service.integration;

import kz.reself.notification.pojo.FormData;
import kz.reself.notification.pojo.FormVersionDTO;

import java.io.IOException;
import java.util.List;

public interface IMetaService {

    void getForms(Long formVersId, String ru) throws IOException;
    List<FormVersionDTO> getFormVers(Long formId) throws IOException;
    FormData getFormName(Long formId, String lang) throws IOException;
}
