package com.mayanguang.myspringboot.bean;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: MA
 * @Date: 2021/11/04 16:59
 */
//表明这是个实体类
@Entity
@Table(name = "springofpeople")
public class MainBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//把提供的逐渐生成策略设置为自增IDENTITY
    @Column(name = "f_id", nullable = false, updatable = false)//对应表列为f_id,不可为空,不可更新
    /**设定属性名为id Integer类型,MySQL表中,会建立一个int类型的字段 下同*/
    private Integer id;

    @Column(name = "f_name", nullable = true)//对应表列为f_id,可为空
    /**设定属性名为name String类型,MySQL表中,会建立一个长度为255的varchar类型的字段 下同*/
    private String name;

    @Column(name = "f_age", nullable = true)
    private Integer age;

    @Column(name = "f_pwd", nullable = false)
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainBean mainBean = (MainBean) o;
        return Objects.equals(id, mainBean.id) && Objects.equals(name, mainBean.name) && Objects.equals(age, mainBean.age) && Objects.equals(pwd, mainBean.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, pwd);
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**需要声明无参的构造函数*/
    public MainBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
