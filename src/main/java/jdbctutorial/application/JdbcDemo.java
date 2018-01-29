package jdbctutorial.application;


import jdbctutorial.application.dao.DeveloperDAO;
import jdbctutorial.application.dao.impl.DeveloperDAOImpl;
import jdbctutorial.application.model.Developer;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class JdbcDemo {
    public static void showAllDev(DeveloperDAO developerDAO) {
        List<Developer> developerList = developerDAO.getAll();

        for (int i = 0; i < developerList.size(); i++) {
            System.out.println(developerList.get(i));
        }
    }

    public static Developer getNewDev(Long id, String name, String lastName, String specialty, BigDecimal salary) {
        Developer developer = new Developer();
        developer.setId(id);
        developer.setFirstName(name);
        developer.setLastName(lastName);
        developer.setSpecialty(specialty);
        developer.setSalary(salary);
        return developer;
    }

    private static void showAndUpdateDevSpecialty(Developer developer, String language, DeveloperDAO developerDAO) throws SQLException {
        developer.setSpecialty(language);
        developerDAO.update(developer);
        System.out.println(developerDAO.getById(developer.getId()));
    }

    public static void showDevById(DeveloperDAO developerDAO, Long id) throws SQLException {
        System.out.println(developerDAO.getById(id));
    }


    public static void main(String[] args) throws SQLException {
        DeveloperDAO developerDAO = new DeveloperDAOImpl();

//        showAllDev(developerDAO);

//        showDevById(developerDAO, 1L);
//
//        developerDAO.save(getNewDev(7L,"Petro", "Ivanov", "Go", BigDecimal.valueOf(3000.00)));
//
//        showAllDev(developerDAO);
//
//        showAndUpdateDevSpecialty(developerDAO.getById(1L), "Ruby", developerDAO);
    }
}

