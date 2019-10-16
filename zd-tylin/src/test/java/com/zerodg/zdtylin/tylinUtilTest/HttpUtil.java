package com.zerodg.zdtylin.tylinUtilTest;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@Slf4j
public class HttpUtil {

//    static final Logger log = LoggerFactory.getLogger(CustomerTest.class);
    private static final RequestConfig requestConfig = RequestConfig.custom()
            // 比 Fluent API 可以多配置一个 timeout
            .setConnectionRequestTimeout(1000)
            .setConnectTimeout(1000*10)
//            .setConnectTimeout(10)
            .setSocketTimeout(1000*15)
            .build();

    public static void httpFinallyClose(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse ){

        if (httpResponse != null) {
            try {
                httpResponse.close();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static String httpGetResponse(String authToken, String interfaceUrl)throws Exception{
        String responContent = null;
        CloseableHttpClient httpClient = null;

        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClientBuilder.create().build();

            URIBuilder uriBuilder = new URIBuilder(interfaceUrl);

            //get
            HttpGet request = new HttpGet(uriBuilder.build().toString());

            //使用默认配置
//            request.setConfig(requestConfig);

            request.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.111 Safari/537.36");
            request.setHeader("authorization", "Basic " + authToken);

            httpResponse = httpClient.execute(request);
            HttpEntity repEntity = httpResponse.getEntity();

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
            }

            responContent = EntityUtils.toString(repEntity, "UTF-8");
        }catch (Exception e){
            throw e;
        }finally {
            httpFinallyClose(httpClient,httpResponse);
        }

        return responContent;
    }


    public static String httpPostResponseByXML(String authToken, String interfaceUrl, String requestXML) throws Exception{
        String responseContent = null;
        CloseableHttpClient httpClient = null;

        CloseableHttpResponse httpResponse = null;

        try {
            httpClient = HttpClientBuilder.create().build();

            HttpPost httpPost = new HttpPost(interfaceUrl);

            //使用默认配置
//            httpPost.setConfig(requestConfig);


            httpPost.addHeader("Content-Type", "text/xml; charset=utf-8");
            httpPost.addHeader("authorization","Basic "+authToken);
            httpPost.addHeader("SOAPAction", "");
            httpPost.addHeader("Accept", "text/xml");
            StringEntity myEntity = new StringEntity(requestXML, "UTF-8");
            httpPost.setEntity(myEntity);

            httpResponse = httpClient.execute(httpPost);

            int statusCode = httpResponse.getStatusLine().getStatusCode();

            HttpEntity repEntity = httpResponse.getEntity();

            responseContent = EntityUtils.toString(repEntity, "UTF-8");

//            if (statusCode != HttpStatus.SC_OK) {
                // Do not feel like reading the response body
                // Call abort on the request object
                // 不打算读取response body
                // 调用request的abort方法
//                httpPost.abort();
//            }
        }catch (Exception e){
            throw e;
        }finally {
            httpFinallyClose(httpClient, httpResponse);
        }

        return responseContent;
    }

}
