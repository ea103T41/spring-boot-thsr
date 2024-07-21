package com.euphy.learn.tdx;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class TdxClientHelper {

    private static final Logger logger = LoggerFactory.getLogger(TdxClientHelper.class);

    @Value("${tdx.token.url}")
    private String tokenUrl;

    @Value("${tdx.thsr.url}")
    private String thsrUrl;

    @Value("${tdx.token.client.id}")
    private String clientId;

    @Value("${tdx.token.client.secret}")
    private String clientSecret;

    private final ObjectMapper objectMapper;

    @Autowired
    public TdxClientHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String fetchData(String fetchType) throws IOException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "client_credentials"));
        params.add(new BasicNameValuePair("client_id", clientId));
        params.add(new BasicNameValuePair("client_secret", clientSecret));

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String tokenInfo = postJsonString(tokenUrl, params);
        JsonNode tokenElem = objectMapper.readTree(tokenInfo);
        String accessToken = tokenElem.get("access_token").asText();

        Map<String,String> headers = new HashMap<>();
        headers.put("authorization", String.format("Bearer %s", accessToken));
        headers.put("Accept-Encoding", "gzip");
        String resultJson = getJsonString(thsrUrl + fetchType, headers);
        logger.debug("resultJson: {}", resultJson);
        return resultJson;
    }

    private static String getJsonString(String tdxUrl, Map<String, String> headers) {
        HttpGet httpGet = new HttpGet(tdxUrl);
        if (Objects.nonNull(headers)) headers.forEach(httpGet::addHeader);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {
            logger.debug("ResponseStatus: {}", response.getStatusLine().getStatusCode());
            return EntityUtils.toString(response.getEntity());
        }
        catch (IOException e) {
            logger.error("getJsonString error", e);
        }
        return null;
    }

    private static String postJsonString(String url, List<NameValuePair> params) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {
            logger.debug("ResponseStatus: {}", response.getStatusLine().getStatusCode());
            return EntityUtils.toString(response.getEntity());
        }
        catch (IOException e) {
            logger.error("postJsonString error", e);
        }
        return null;
    }
}
