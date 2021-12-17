package kz.reself.notification.pojo.templates;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
public class RestClientTemplate {

    private String server;
    private RestTemplate restTemplate;
    private HttpStatus httpStatus;

    public RestClientTemplate(String server) {
        this.server = server;
        this.restTemplate = new RestTemplate();
    }

    public String get(Long uri, HttpHeaders httpHeaders) {
        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);

        ResponseEntity<String> result = restTemplate
                .exchange(server + uri, HttpMethod.GET, entity, String.class);

        this.setHttpStatus(result.getStatusCode());

        return result.getBody();
    }

    public String get(String uri, HttpHeaders httpHeaders) {
        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);

        ResponseEntity<String> result = restTemplate
                .exchange(server + uri, HttpMethod.GET, entity, String.class);

        this.setHttpStatus(result.getStatusCode());

        return result.getBody();
    }

    public String get() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Accept", "*/*");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);

        ResponseEntity<String> result = restTemplate
                .exchange(server, HttpMethod.GET, entity, String.class);

        this.setHttpStatus(result.getStatusCode());

        return result.getBody();
    }

    public String get(String uri) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Accept", "*/*");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);

        ResponseEntity<String> result = restTemplate
                .exchange(server + uri, HttpMethod.GET, entity, String.class);

        this.setHttpStatus(result.getStatusCode());

        return result.getBody();
    }

    public String post(String url, HttpHeaders httpHeaders, String body) {
        String returnString = "";
        try {
            String fullUrl = server + url;
            HttpEntity<String> entity = new HttpEntity<String>(body, httpHeaders);
            ResponseEntity<String> result = restTemplate.postForEntity(fullUrl, entity, String.class);
            setHttpStatus(result.getStatusCode());

            returnString = result.getBody();

        } catch (RestClientException e) {
            if (e instanceof HttpStatusCodeException) {
                returnString = ((HttpStatusCodeException) e).getResponseBodyAsString();
            }
        }
        return returnString;
    }
}

