package com.night.cloud.payment.controller;

import cn.hutool.core.util.ObjectUtil;
import com.night.cloud.common.model.PaymentModel;
import com.night.cloud.common.result.Result;
import com.night.cloud.payment.bean.QuestionBean;
import com.night.cloud.payment.dao.QuestionDao;
import com.night.cloud.payment.iservice.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 夜月
 * @description
 * @data 2020/5/27 14:30
 */
@Slf4j
@RequestMapping("/payment/")
@RestController
public class PaymentController {

    @Resource(name = "paymentService")
    private IPaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;


    @Value("${server.port}")
    private String serverPort;

    @PostMapping("add")
    public Result addPayment(@RequestBody PaymentModel paymentModel) {
        Integer count = paymentService.insert(paymentModel);
        log.info("serverPort:" + serverPort + ":*******:" + count);
        return Result.success();
    }

    @GetMapping("details/{id}")
    public Result details(@PathVariable("id") Long id) {
        PaymentModel payment = paymentService.getById(id);
        payment.setSerial(payment.getSerial() + "--" + serverPort);
        log.info("serverPort:" + serverPort + ":*******:" + payment);
        return Result.success(payment);
    }

    @GetMapping("discovery")
    public Result discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service: " + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("host: " + instance.getHost() + "\tport:" + instance.getPort() + "\turi:" + instance.getUri());
        }
        return Result.success(discoveryClient);
    }

    @GetMapping("lb")
    public Result lb() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.success(serverPort);
    }

    @Resource
    private QuestionDao questionDao;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("es")
    public Result es() {
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(
                        QueryBuilders.matchQuery("stem",
                                "建设工程项目施工总承包模式的最大缺点是")
                );
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        highlightBuilder.field("stem");
        SearchResponse searchResponse = elasticsearchTemplate.getClient().prepareSearch("question_index")
                .setQuery(query)
                .highlighter(highlightBuilder)
                .setFrom(0)
                .setSize(10)
                .get();
        SearchHits searchHits = searchResponse.getHits();
        List<QuestionBean> results = new ArrayList<>();
        //组装结果
        for (SearchHit searchHit : searchHits) {
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            Map<String, HighlightField> highlightFieldsResult = searchHit.getHighlightFields();
            try {
                QuestionBean questionBean = QuestionBean.class.newInstance();
                Field[] declaredFields = questionBean.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    String name = field.getName();
                    if (highlightFieldsResult.get(name) != null) {
                        field.set(questionBean, (highlightFieldsResult.get(name).getFragments()[0]).toString());
                    } else {
                        Object fieldValue = sourceAsMap.get(name);
                        if ("type".equals(name) && ObjectUtil.isNotEmpty(fieldValue)) {
                            fieldValue = fieldValue + "";
                        }
                        if ("fraction".equals(name) && ObjectUtil.isNotEmpty(fieldValue)) {
                            fieldValue = Long.parseLong(fieldValue + "");
                        }
                        field.set(questionBean, fieldValue);
                    }
                }
                results.add(questionBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Result.success(results);
    }

    /*@GetMapping("es")
    public Result es() {
        Pageable page = PageRequest.of(0, 10);
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(
                        QueryBuilders.matchQuery("stem",
                                "建设工程项目施工总承包模式的最大缺点是")
                );
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        highlightBuilder.field("stem");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withHighlightBuilder(highlightBuilder)
                .withPageable(page)
                .build();
        Page<QuestionBean> search = questionDao.search(searchQuery);
        return Result.success(search);
    }*/

}
