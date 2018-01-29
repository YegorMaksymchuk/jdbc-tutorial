package jdbctutorial.application.dao.impl;

import jdbctutorial.application.dao.ProjectDAO;
import jdbctutorial.application.model.Project;
import jdbctutorial.application.utils.ConnectionUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO {
    @Override
    public Project getById(Long id) throws SQLException {
        String sql = "SELECT * FROM projects WHERE id = " + id;
        Statement statement = ConnectionUtil.getConnectionAutoCommitFalse().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Project project = null;

        while (resultSet.next()) {
            Long projectId = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String info = resultSet.getString("info");
            project = new Project(projectId, name, info);
        }

        try {
            statement.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return project;
    }

    @Override
    public List<Project> getAll() {
        return null;
    }

    @Override
    public void save(Project project) throws SQLException {

    }

    @Override
    public void update(Project project) throws SQLException {

    }

    @Override
    public void delete(Project project) throws SQLException {

    }
}
