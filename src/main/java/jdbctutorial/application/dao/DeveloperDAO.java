package jdbctutorial.application.dao;

import jdbctutorial.application.model.Developer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DeveloperDAO extends GenericDAO<Developer, Long> {

    void addPhoto(Developer developer, String path) throws SQLException, IOException;

    File getPhoto(Developer developer);
}
