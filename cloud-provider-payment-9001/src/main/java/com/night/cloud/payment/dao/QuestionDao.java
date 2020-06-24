package com.night.cloud.payment.dao;

/**
 * @author 夜月
 * @description
 * @data 2019/9/29 11:20
 */
import com.night.cloud.payment.bean.QuestionBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 继承CRUD，第一个泛型是实体类类型，第二个泛型是id的类型
 */
@Repository
public interface QuestionDao extends ElasticsearchRepository<QuestionBean, String> {
}