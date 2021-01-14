package com.kaixue.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//以下两个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
// 以下两个是属于jpa的注解
// 关于jpa注解 https://wenku.baidu.com/view/77f68edeee06eff9aff8071c.html
// 关于jpa，hibernate，spring-data-jpa的区别：https://blog.csdn.net/benjaminlee1/article/details/53087351
@Entity//这个表示是一个实体类
@Table(name = "course_base")//这个表示对应数据库中的表
// hibernate注解,这个表示主键为uuid
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CourseBase implements Serializable
{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "users")
    private String users;
    @Column(name = "mt")
    private String mt;
    @Column(name = "level")
    private String level;
    @Column(name = "study_model")
    private String studyModel;
    @Column(name = "teach_mode")
    private String teachMode;
    @Column(name = "description")
    private String description;
    @Column(name = "st")
    private String st;
    @Column(name = "status")
    private String status;
    @Column(name = "company_id")
    private String companyId;
    @Column(name = "user_id")
    private String userId;
}
