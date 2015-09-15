package services;

import com.impetus.client.cassandra.common.CassandraConstants;
import play.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by vitthalawate on 13/9/15.
 */
public class Eloquent
{
    private EntityManager em;

    private Logger logger;

    protected EntityManager getEntityManager() {
        if (this.em == null || !em.isOpen()) {
            Map<String, String> propertyMap = new HashMap<String, String>();
            propertyMap.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cassandra_pu", propertyMap);
            this.em = emf.createEntityManager();
        }

        return this.em;
    }

    protected void closeEntity() {
        this.getEntityManager().close();
    }

    protected <T> T find(Class<T> modelClass, String id) {
        return this.getEntityManager().find(modelClass, id);
    }

    protected <T> T find(Class<T> modelClass, Integer id) {
        return this.getEntityManager().find(modelClass, id);
    }

    protected boolean create(Object o) {
        try {
            this.getEntityManager().persist(o);
            return true;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return false;
        }
    }

    protected boolean update(Object o) {
        try {
            this.getEntityManager().merge(o);
            return true;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return false;
        }
    }

    protected List selectQuery(String query) {
        Query q = this.getEntityManager().createQuery(query);
        return  q.getResultList();
    }

}
