package com.kaixue.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

//以下两个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
// 以下两个是属于jpa的注解
// 关于jpa注解 https://wenku.baidu.com/view/77f68edeee06eff9aff8071c.html
// 关于jpa，hibernate，spring-data-jpa的区别：https://blog.csdn.net/benjaminlee1/article/details/53087351
@Entity//这个表示是一个实体类
@Table(name = "teach_plan")//这个表示对应数据库中的表
// hibernate注解
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class TeachPlan implements Serializable
{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    //Jpa注解
    @Column(name = "p_name")
    private String pName;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "level")
    private String level;

    @Column(name = "p_type")
    private String pType;

    @Column(name = "description")
    private String description;

    @Column(name = "time_length")
    private Double timeLength;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "order_by")
    private String orderBy;

    @Column(name = "status")
    private String status;

    @Column(name = "try_learn")
    private String tryLearn;
}
