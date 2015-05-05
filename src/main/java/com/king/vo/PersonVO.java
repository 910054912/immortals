package com.king.vo;

import com.king.vo.base.AbstractBaseVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Mr.Peabody on 2015/4/8.
 */
@Entity
@Table(name = "kf_person")
public class PersonVO extends AbstractBaseVO{
    private String name;
    private Integer sex;
    private Integer age;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
