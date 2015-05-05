package com.king.vo;

import com.king.vo.base.AbstractBaseVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Mr.Peabody on 2015/3/17.
 */
@Entity
@Table(name = "main_photographer_arrangement")
public class ScheduleVO extends AbstractBaseVO{
    private Date date;
    private Integer dateStatus;//共3种状态  每种日期类型有 0不可用 1可用 2已预约  并且0,1状态是针对大于当前日期的时间
    private String userId;
    private String orderId;
    private Integer dateType;


    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "schedule_date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Column(name = "date_status")
    public Integer getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(Integer dateStatus) {
        this.dateStatus = dateStatus;
    }

    @Column(name = "date_type")
    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }
}
