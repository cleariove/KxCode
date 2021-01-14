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
@Table(name = "category")//这个表示对应数据库中的表
// hibernate注解，这个表示主键自定义
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class Category implements Serializable {
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(length = 32)
    private String id;
    private String name;
    private String label;
    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "is_show")
    private String isShow;
    @Column(name = "order_by")
    private Integer orderBy;
    @Column(name = "is_leaf")
    private String isLeaf;

}
