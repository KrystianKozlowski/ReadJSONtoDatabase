package com.myschoolbook.service;

import com.myschoolbook.model.FreequentCrime;
import com.myschoolbook.pojo.CrimeAtLocation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Krystian on 2017-06-01.
 */
public class CrimeService {

    public void clearTable() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String SQL_QUERY = "delete from CrimeAtLocation crime";
            Query query = session.createQuery(SQL_QUERY);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Table was cleared!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public CrimeAtLocation addCrime(CrimeAtLocation crimeAtLocation){
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(crimeAtLocation);
        session.getTransaction().commit();
        session.close();
        return crimeAtLocation;
    }

    public List<FreequentCrime> listOfCrime(int year, int localizationId) {

        List<FreequentCrime> fq = new ArrayList<FreequentCrime>();

        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            String SQL_QUERY = "select crime.category, count(*) as counted from CrimeAtLocation crime  group by crime.category order by counted DESC ";
            Query query = session.createQuery(SQL_QUERY);
            query.setMaxResults(5);
            for (Iterator it = query.iterate(); it.hasNext();) {
                Object[] row = (Object[]) it.next();
                //System.out.println("Category: " + row[0] + " Number: " + row[1]);
                fq.add(new FreequentCrime(year, localizationId, Integer.parseInt(row[1].toString()),row[0].toString()));
            }
            session.close();
            //HibernateUtility.getSessionFactory().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fq;
    }
}
