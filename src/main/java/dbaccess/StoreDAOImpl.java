package dbaccess;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: jicui
 * Date: 14-1-7
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class StoreDAOImpl implements StoreDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Override
    @Transactional
    public Store getStore(Long id) {
        return (Store)getSessionFactory().getCurrentSession().get(Store.class,id);  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
