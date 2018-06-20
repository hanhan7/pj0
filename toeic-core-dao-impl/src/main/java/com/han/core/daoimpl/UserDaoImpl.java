package com.han.core.daoimpl;

import com.han.core.common.utils.HibernateUtil;
import com.han.core.dao.UserDao;
import com.han.core.data.daoimpl.AbstractDao;
import com.han.core.persistence.entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

    @Override
    public UserEntity findUserByUsernameAndPassword(String name, String password) {
        UserEntity entity = new UserEntity();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("FROM UserEntity WHERE name= :name AND password= :password ");
            Query query = session.createQuery(sql.toString());
            query.setParameter("name", name);
            query.setParameter("password", password);
            entity = (UserEntity) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }
}
