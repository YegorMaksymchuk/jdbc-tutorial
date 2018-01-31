package jdbctutorial.application.dao;

import jdbctutorial.application.model.Developer;
import jdbctutorial.application.model.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDAO extends GenericDAO<Project, Long> {
    void assignDeveloperToProject(Developer developer, Project project) throws SQLException;
}
