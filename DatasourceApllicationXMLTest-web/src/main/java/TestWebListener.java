import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by renannp on 2/1/16.
 */
@WebListener
public class TestWebListener implements ServletContextListener {

    @Resource(mappedName = "java:jboss/datasources/worldDb")
    DataSource dataSource;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //
        System.out.println("Test PersistenceContext EntityManager");
        Query nativeQuery = em.createNativeQuery("SELECT * FROM department");
        List resultList = nativeQuery.getResultList();
        System.out.println("resultList.size: " + resultList.size());
        for (Object object : resultList) {
            System.out.println(object);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
