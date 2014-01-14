import dbaccess.Spitter;import dbaccess.SpitterDAO;import dbaccess.Store;import dbaccess.StoreDAO;import junit.framework.Assert;import org.hibernate.SessionFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.beans.factory.annotation.Qualifier;import org.springframework.test.annotation.Rollback;import org.springframework.test.context.ActiveProfiles;import org.springframework.test.context.ContextConfiguration;import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;import org.springframework.test.context.transaction.TransactionConfiguration;import org.springframework.transaction.annotation.Transactional;import org.testng.annotations.Test;/** * Demo the integration tests with Hibernate and some transaction roll back features * User: jicui * Date: 1/5/14 * Time: 8:43 PM * To change this template use File | Settings | File Templates. */@ContextConfiguration("/dbaccess/dbsource.xml")@ActiveProfiles("dev")@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)public class HibernateIntegrationTests extends AbstractTransactionalTestNGSpringContextTests {    private SpitterDAO spitterDAO ;    private StoreDAO storeDAO;    private SessionFactory sessionFactory;    @Test    @Rollback(true)    public void testHibernateIntegration(){        Spitter spitter = new Spitter();        spitter.setId(1L);        spitter.setEmail("jicui@ebay.com");        spitter.setFullName("Jason Cui");        spitter.setUserName("jicui");        spitter.setPassword("test123");        spitterDAO.add(spitter);        sessionFactory.getCurrentSession().flush(); //prevent false positive issue        Spitter tmp = spitterDAO.getSpitterById(1L);        Assert.assertNotNull(tmp);        Assert.assertEquals(spitter.getUserName(), tmp.getUserName());        Assert.assertEquals(tmp.getId().longValue(), 1L);        Store store=storeDAO.getStore(1L);        Assert.assertNull(store);    }    public SpitterDAO getSpitterDAO() {        return spitterDAO;    }    @Autowired    public void setSpitterDAO(SpitterDAO spitterDAO) {        this.spitterDAO = spitterDAO;    }    public StoreDAO getStoreDAO() {        return storeDAO;    }    @Autowired    public void setStoreDAO(StoreDAO storeDAO) {        this.storeDAO = storeDAO;    }    public SessionFactory getSessionFactory() {        return sessionFactory;    }    @Autowired    public void setSessionFactory(SessionFactory sessionFactory) {        this.sessionFactory = sessionFactory;    }}