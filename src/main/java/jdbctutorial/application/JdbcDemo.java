package jdbctutorial.application;


import jdbctutorial.application.dao.DeveloperDAO;
import jdbctutorial.application.dao.ProjectDAO;
import jdbctutorial.application.dao.impl.DeveloperDAOImpl;
import jdbctutorial.application.dao.impl.ProjectDAOImpl;
import jdbctutorial.application.model.Developer;
import jdbctutorial.application.utils.StoredProcedureUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcDemo {
    public static void showAllDev(DeveloperDAO developerDAO) {
        List<Developer> developerList = developerDAO.getAll();

        for (int i = 0; i < developerList.size(); i++) {
            System.out.println(developerList.get(i));
        }
    }

    public static Developer createDeveloper(Long id, String name, String lastName, String specialty, BigDecimal salary) {
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


    public static void main(String[] args) throws SQLException, IOException {
        DeveloperDAO developerDAO = new DeveloperDAOImpl();

        showAllDev(developerDAO);

        showDevById(developerDAO, 1L);

        developerDAO.save(createDeveloper(7L,"Petro", "Ivanov", "Go", BigDecimal.valueOf(3000.00)));

        showAllDev(developerDAO);

        showAndUpdateDevSpecialty(developerDAO.getById(1L), "Ruby", developerDAO);

        ProjectDAO projectDAO = new ProjectDAOImpl();
        System.out.println(projectDAO.getById(1l));

        StoredProcedureUtil storedProcedureUtil = new StoredProcedureUtil();
        storedProcedureUtil.createStoredProcedure();
        printMap(storedProcedureUtil.callStoredProcedure("show_developers_by_project"));
    }


    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("| Developer name : " + entry.getKey() +" | "+ " Project name : " + entry.getValue() +" |");
        }
    }
}
