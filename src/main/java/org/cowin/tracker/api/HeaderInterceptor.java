package org.cowin.tracker.api;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HeaderInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {
    Map<String, Collection<String>> headerMap = new HashMap<>();
    headerMap.put("Host", Collections.singletonList("cdn-api.co-vin.in"));
    headerMap.put("origin", Collections.singletonList("https://www.cowin.gov.in"));
    headerMap.put("referer", Collections.singletonList("https://www.cowin.gov.in/"));
    headerMap.put("User-Agent", Collections.singletonList("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:88.0) Gecko/20100101 Firefox/88.0"));
    headerMap.put("Accept", Collections.singletonList("application/json, text/plain, */*"));
//    headerMap.put("Accept-Encoding", Collections.singletonList("gzip, deflate, br"));
    requestTemplate.headers(headerMap);
  }
}
