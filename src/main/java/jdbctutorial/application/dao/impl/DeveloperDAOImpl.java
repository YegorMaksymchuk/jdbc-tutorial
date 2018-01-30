package jdbctutorial.application.dao.impl;

import jdbctutorial.application.dao.DeveloperDAO;
import jdbctutorial.application.model.Developer;
import jdbctutorial.application.utils.ConnectionUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDAOImpl implements DeveloperDAO {
    @Override
    public Developer getById(Long id) throws SQLException {
        String sql = "SELECT * FROM developers WHERE id = " + id;
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Developer developer = new Developer();

        while (resultSet.next()) {
            Long developerId = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String specialty = resultSet.getString("specialty");
            BigDecimal salary = resultSet.getBigDecimal("salary");

            developer.withId(developerId)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withSpecialty(specialty)
                    .withSalary(salary);
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

        return developer;
    }

    @Override
    public List<Developer> getAll() {
        String sql = "SELECT * FROM developers";
        List<Developer> developersList = new ArrayList<>();
        Statement statement = null;

        try {
            statement = ConnectionUtil.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Developer Developer = new Developer();
                Developer.setId(resultSet.getLong("id"));
                Developer.setFirstName(resultSet.getString("first_name"));
                Developer.setLastName(resultSet.getString("last_name"));
                Developer.setSpecialty(resultSet.getString("specialty"));
                Developer.setSalary(resultSet.getBigDecimal("salary"));
                developersList.add(Developer);
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
        return developersList;
    }

    @Override
    public void save(Developer developer) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO developers (id, first_name, last_name, specialty, salary) VALUES (?,?,?,?,?)";

        try {
            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sql);

            preparedStatement.setLong(1, developer.getId());
            preparedStatement.setString(2, developer.getFirstName());
            preparedStatement.setString(3, developer.getLastName());
            preparedStatement.setString(4, developer.getSpecialty());
            preparedStatement.setBigDecimal(5, developer.getSalary());

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
    public void update(Developer developer) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE developers SET FIRST_NAME=?, LAST_NAME=?, SPECIALTY=?, SALARY=? WHERE ID=?";
        int resultExecution;
        try {
            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, developer.getFirstName());
            preparedStatement.setString(2, developer.getLastName());
            preparedStatement.setString(3, developer.getSpecialty());
            preparedStatement.setBigDecimal(4, developer.getSalary());
            preparedStatement.setLong(5, developer.getId());

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
    public void delete(Developer developer) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM developers WHERE ID=?";

        try {
            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, developer.getId());

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
}
