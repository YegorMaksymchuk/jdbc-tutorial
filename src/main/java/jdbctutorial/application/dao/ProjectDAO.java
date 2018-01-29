package jdbctutorial.application.dao;

import jdbctutorial.application.model.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDAO extends GenericDAO<Project, Long> {

    @Override
    Project getById(Long aLong) throws SQLException;

    @Override
    List<Project> getAll();

    @Override
    void save(Project project) throws SQLException;

    @Override
    void update(Project project) throws SQLException;

    @Override
    void delete(Project project) throws SQLException;
}
