package com.king.vo.base;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mr.Peabody on 2015/3/17.
 */
@MappedSuperclass
public abstract class AbstractBaseVO implements Serializable{//抽象类没有被创建实例，但是其成员变量
    private String id;
    private Date ts = new Date();//时间戳   //给予初始化值，可以用来作为创建记录时的默认值
    private Integer dataStatus = 1;//数据有效性

    @Id
    @GenericGenerator(name="idGenerator",strategy = "com.core.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)//仅仅用来在取值时进行格式化
    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    @Column(name = "data_status")
    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }
}
