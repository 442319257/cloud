package com.night.cloud.payment.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
/**
 * @author 夜月
 * @description
 * @data 2020/6/30 15:43
 */
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
    /**
     * es集群读取路径
     */
    public static final String X_PACK_ES_NAME = "cluster.name";
    /**
     * x-pack用户名密码读取路径
     */
    public static final String X_PACK_USER = "xpack.security.user";


    private String esClusterName = "my-master-es";

    private String esHost = "47.103.158.51:9200";

    private int esPort = 9300;

    private String esUserName = "elastic";

    private String esUserPassword = "123456";


    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        HttpHost[] httpHostArr = new HttpHost[1];
        httpHostArr[0] = HttpHost.create(esHost);
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        //es账号密码（默认用户名为elastic）
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esUserName, esUserPassword));
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(httpHostArr)
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                httpClientBuilder.disableAuthCaching();
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        }));
        return client;
    }


}
