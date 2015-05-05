package com.king.dao;

/**
 * Created by Mr.Peabody on 2015/3/18.
 */

import com.king.vo.base.AbstractBaseVO;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class DaoTemplate {
    @Autowired
    @Qualifier("sessionFactory")
    public SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Session getSession() {
        Session session = this.sessionFactory.getCurrentSession();
        return session;
    }

    //单实体操作

    public Object getObject(Class object, String id) {
        return this.getSession().get(object, id);
    }

    public void saveOrUpdate(AbstractBaseVO object) {
        Session session = this.getSession();
        object.setTs(new Date());//每次更新时，更新时间戳
        session.saveOrUpdate(object);
        session.flush();
    }

    public void remove(AbstractBaseVO vo){
        vo.setDataStatus(0);
        this.getSession().update(vo);
        this.getSession().flush();
    }
    public void delete(AbstractBaseVO object) {
        this.getSession().delete(object);
    }

    //Hibernate批操作-实体
    public void batchSaveOrUpdate(List<? extends AbstractBaseVO> entityList){
        StatelessSession session = sessionFactory.openStatelessSession();
        Transaction transaction = session.beginTransaction();
        for(AbstractBaseVO entity : entityList){
            entity.setTs(new Date());
            if(StringUtils.isBlank(entity.getId())){//保存时，与entity的实际类型相关，与引用它的类型(接口或父类)无关
                session.insert(entity);
            }
            else{
                session.update(entity);
            }
        }
        transaction.commit();
    }

    public void batchRemove(List<? extends AbstractBaseVO> entityList) throws Exception{
        StatelessSession session = sessionFactory.openStatelessSession();
        Transaction transaction = session.beginTransaction();
        for(AbstractBaseVO entity : entityList){
            if(!StringUtils.isBlank(entity.getId())){
                entity.setDataStatus(0);
                session.delete(entity);
            }
        }
        transaction.commit();
    }

    //JDBC操作
    public List listBySQL(String sql, Map map) {//考虑参数值可能为集合（即in条件查询）
        Query query = this.getSession().createSQLQuery(sql);
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iterator.next();
            query.setParameter(entry.getKey().toString(), entry.getValue());
        }
        return query.list();
    }
    public int executeUpdate(String sql,Map map){
        Transaction transaction = this.getSession().beginTransaction();
        Query query = this.getSession().createSQLQuery(sql);
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iterator.next();
            query.setParameter(entry.getKey().toString(), entry.getValue());
        }
        int result = query.executeUpdate();
        transaction.commit();
        return result;
    }

    //hql批操作
    public List findByHQL(String hql,Map params){
        Query query = this.getSession().createQuery(hql);
        for(Iterator iterator = params.entrySet().iterator();iterator.hasNext();){
            Map.Entry entry = (Map.Entry)iterator.next();
            query.setParameter(entry.getKey().toString(),entry.getValue());
        }
        return query.list();
    }




}
