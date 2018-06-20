package com.han.core.data.daoimpl;

import com.han.core.common.constant.CoreConstant;
import com.han.core.common.utils.HibernateUtil;
import com.han.core.data.dao.GenericDao;
import javassist.tools.rmi.ObjectNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by VO VAN NHAN on 5/21/2018.
 */
public abstract class AbstractDao<ID extends Serializable, T> implements GenericDao<ID,T>{
    private Class<T> persistenceClass;

    public AbstractDao() {
        //get class name by location of <1,2>
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    public String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }

//    protected Session getSession() {
//        return HibernateUtil.getSessionFactory().openSession();
//    }

    //adapting HQL in Hibernate
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

    }

    public T findById(ID id) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = (T) session.get(persistenceClass, id);
            if (result == null) {
                throw new ObjectNotFoundException(" NOT FOUND " + id, null);
            }
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return  result;
    }



    //    public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection , Integer offset, Integer limit ) {
    public Object[] findByProperty(HashMap<String,Object> property, String sortExpression, String sortDirection , Integer offset, Integer limit ) {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object totalItem = 0;
        String[] params = new String[property.size()];
        Object[] values = new Object[property.size()];
        int i = 0;
        for (Map.Entry<String, Object> item : property.entrySet()) {
            params[i] = (String) item.getKey();
            values[i] = item.getValue();
            i++;
        }
        try {
            //select item
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName());
            /*if (property != null && value != null) {
                sql1.append(" where ");
                sql1.append(property).append("= :value");
            }*/
            if (property.size() > 0) {
                for (int i1 = 0; i1 < params.length; i1++) {
                    if (i1 == 0) {
                        sql1.append(" where ");
                        sql1.append(params[i1]).append("= :"+params[i1]+"");
                    }
                    else {
                        sql1.append(" and ");
                        sql1.append(params[i1]).append("= :"+params[i1]+"");
                    }

                }
            }
            if (sortExpression != null && sortDirection != null) {
                sql1.append(" order by ").append(sortExpression);
                sql1.append(" " + (sortDirection.equals(CoreConstant.SORT_ASC) ? "asc" : "desc"));
            }
            Query query1 = session.createQuery(sql1.toString());
            /*if (value != null){
                query1.setParameter("value", value);
            }*/
            if (property.size() > 0) {
                for (int i2 = 0; i2 < params.length; i2++) {
                    query1.setParameter(params[i2], values[i2]);
                }
            }
            if (offset != null && offset >=0) {
                query1.setFirstResult(offset); //lay item bat dau cua trang...
            }
            if (limit != null && limit >0) {
                query1.setMaxResults(limit);
            }
            list = query1.list();

            //count item
            StringBuilder sql2 = new StringBuilder("select count(*) from ");
            sql2.append(getPersistenceClassName());
//            if (property != null && value != null) {
//                sql2.append(" where ").append(property).append("= :value");
//            }
            if (property.size() > 0) {
                for (int k1 = 0; k1 < params.length; k1++) {
                    if (k1 == 0) {
                        sql2.append(" where ");
                        sql2.append(params[k1]).append("= :"+params[k1]+"");
                    }
                    else {
                        sql2.append(" and ");
                        sql2.append(params[k1]).append("= :"+params[k1]+"");
                    }

                }
            }
            Query query2 = session.createQuery(sql2.toString());
//            if (value != null) {
//                query2.setParameter("value", value);
//            }
            if (property.size() > 0) {
                for (int k2 = 0; k2 < params.length; k2++) {
                    query2.setParameter(params[k2], values[k2]);
                }
            }
            totalItem = query2.list().get(0);

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return new Object[]{totalItem, list};
    }

    public Integer delete(List<ID> ids) {
        Integer count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (ID item : ids) {
                T t = (T) session.get(persistenceClass, item);
                session.delete(t);
                count++;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return count;
    }
}
