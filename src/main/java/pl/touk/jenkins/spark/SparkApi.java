package pl.touk.jenkins.spark;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;
import java.util.logging.Logger;

public class SparkApi {

    private static final Logger LOGGER = Logger.getLogger(SparkApi.class.getName());

    private final static String DEFAULT_SPARK_API_URL = "https://api.spark.io/v1";
    private final CloseableHttpAsyncClient httpclient;
    private final String accessToken;
    private final String deviceId;
    private final String sparkCloudUrl;

    public SparkApi(String accessToken, String deviceId) {
        this(accessToken, deviceId, DEFAULT_SPARK_API_URL);
    }

    public SparkApi(String accessToken, String deviceId, String sparkCloudUrl) {
        this.accessToken = accessToken;
        this.deviceId = deviceId;
        this.sparkCloudUrl = sparkCloudUrl;
        this.httpclient = HttpAsyncClients.createDefault();
        this.httpclient.start();
    }

    public void notify(String function) {
        HttpPost request = new HttpPost(sparkCloudUrl + String.format("/devices/%s/%s", deviceId, function));
        request.addHeader("Authorization", "Bearer " + accessToken);
        httpclient.execute(request, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                try {
                    LOGGER.info("Request completed: " + IOUtils.toString(httpResponse.getEntity().getContent()));
                } catch (IOException ignored) { }
            }

            @Override
            public void failed(Exception e) {
                LOGGER.info("Request failed: " + e);
            }

            @Override
            public void cancelled() {
            }
        });
    }

}
