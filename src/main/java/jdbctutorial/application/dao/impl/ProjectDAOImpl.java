package jdbctutorial.application.dao.impl;

import jdbctutorial.application.dao.ProjectDAO;
import jdbctutorial.application.model.Developer;
import jdbctutorial.application.model.Project;
import jdbctutorial.application.utils.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String sql = "SELECT * FROM projects";
        List<Project> projectsList = new ArrayList<>();
        Statement statement = null;

        try {
            statement = ConnectionUtil.getConnectionAutoCommitFalse().createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getLong("id"));
                project.setName(resultSet.getString("name"));
                project.setInfo(resultSet.getString("info"));
                projectsList.add(project);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        }
        return projectsList;
    }

    @Override
    public void save(Project project) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO projects (id, name, info) VALUES (?,?,?)";

        try {
            preparedStatement = ConnectionUtil.getConnectionAutoCommitFalse().prepareStatement(sql);

            preparedStatement.setLong(1, project.getId());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setString(3, project.getInfo());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement.getConnection() != null) {
                preparedStatement.getConnection().close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public void update(Project project) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE projects SET NAME=?, INFO=? WHERE ID=?";
        int resultExecution;
        try {
            preparedStatement = ConnectionUtil.getConnectionAutoCommitFalse().prepareStatement(sql);

            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getInfo());
            preparedStatement.setLong(3, project.getId());

            resultExecution = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement.getConnection() != null) {
                preparedStatement.getConnection().close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public void delete(Project project) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM projects WHERE ID=?";

        try {
            preparedStatement = ConnectionUtil.getConnectionAutoCommitFalse().prepareStatement(sql);
            preparedStatement.setLong(1, project.getId());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement.getConnection() != null) {
                preparedStatement.getConnection().close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public void assignDeveloperToProject(Developer developer, Project project) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO developer_projects (developer_id, project_id) VALUES (?,?)";

        try {
            preparedStatement = ConnectionUtil.getConnectionAutoCommitFalse().prepareStatement(sql);

            preparedStatement.setLong(1, developer.getId());
            preparedStatement.setLong(2, project.getId());


            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement.getConnection() != null) {
                preparedStatement.getConnection().close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
