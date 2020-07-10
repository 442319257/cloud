package com.night.cloud.payment.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 夜月
 * @description
 * @data 2020/6/30 15:43
 */
@Configuration
public class ElasticsearchConfig /*extends AbstractElasticsearchConfiguration*/ {
    /**
     * es集群读取路径
     */
    public static final String X_PACK_ES_NAME = "cluster.name";
    /**
     * x-pack用户名密码读取路径
     */
    public static final String X_PACK_USER = "xpack.security.user";


    private String esClusterName = "my-master-es";

    // private String esHost = "47.103.158.51:9200";
    private String esHost = "47.103.158.51";

    private int esPort = 9300;

    private String esUserName = "elastic";

    private String esUserPassword = "elastic:123456";



    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        // 设置es节点的配置信息
        TransportClient client = new PreBuiltXPackTransportClient(
                Settings.builder()
                        .put(X_PACK_ES_NAME, esClusterName)
                        .put(X_PACK_USER, esUserPassword)
                        .build()
        ).addTransportAddress(new TransportAddress(InetAddress.getByName(esHost), esPort));
        return client;
    }

    /*@Override
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
    }*/


}
