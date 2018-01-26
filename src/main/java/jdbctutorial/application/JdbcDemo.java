package jdbctutorial.application;


import jdbctutorial.application.dao.DeveloperDAO;
import jdbctutorial.application.dao.impl.DeveloperDAOImpl;
import jdbctutorial.application.model.Developer;

import java.sql.SQLException;

public class JdbcDemo {
    public static void main(String[] args) throws SQLException {
        DeveloperDAO developerDAO = new DeveloperDAOImpl();
        Developer developer = developerDAO.getById(5L);

        System.out.println(developer);
    }
}
