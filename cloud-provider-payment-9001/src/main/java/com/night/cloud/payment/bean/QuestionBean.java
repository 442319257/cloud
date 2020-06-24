package com.night.cloud.payment.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author 夜月
 * @description 资讯信息
 * @data 2019/9/20 13:52
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "question_index", type = "question_type")
public class QuestionBean implements Serializable {

    /**
     * 主键id (主健ID)
     */
    @Id
    private String id;

    /**
     * 题干
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", store = true)
    private String stem;

    /**
     * 解析
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", store = true)
    private String analysis;

    /**
     * 答案
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", store = true)
    private String answer;

    /**
     * 试题类型
     */
    @Field(type = FieldType.Text, store = true)
    private String questionTypeName;

    /**
     * 类型(0单选/1/多选/3/不定项选择提4/填空/5/材料/6/判断)
     */
    @Field(type = FieldType.Text, store = true)
    private String type;

    /**
     * 科目id
     */
    @Field(type = FieldType.Text, analyzer = "keyword", searchAnalyzer = "keyword", store = true)
    private String categoryId;

    /**
     * 分数
     */
    @Field(type = FieldType.Long)
    private Long fraction;

    /**
     * 章节ID
     */
    @Field(type = FieldType.Text)
    private String chapterId;

}










