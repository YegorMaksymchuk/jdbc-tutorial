package jdbctutorial.application.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
;

public class StoredProcedureUtil {

    public void createStoredProcedure() throws SQLException {
        deleteStoredProcedure("show_developers_by_project");

        String createProcedure = "CREATE PROCEDURE show_developers_by_project() " +
                "BEGIN \n" +
                "SELECT DISTINCT developers.first_name," +
                "projects.name \n" +
                "FROM developers \n" +
                "INNER JOIN projects \n" +
                "order by projects.name; \n" +
                "end";

        Statement statement = ConnectionUtil.getConnection().createStatement();
        try {
            statement.executeUpdate(createProcedure);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }

    }

    public Map<String,String> callStoredProcedure(String soredProcedureName) throws SQLException {
        Map<String, String> developersByProject = new HashMap<>();
        Connection connection = null;
        String procedureCall = "{call jdbc_tutorial." + soredProcedureName +"()}";
        ResultSet rs = null;
        try {
            connection = ConnectionUtil.getConnection();
            CallableStatement callableStatement = connection.prepareCall(procedureCall);
            rs = callableStatement.executeQuery();
            while (rs.next()){
                developersByProject.put(rs.getString("first_name"), rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return developersByProject;
    }

    public void deleteStoredProcedure(String soredProcedureName) throws SQLException {
        String queryDrop = "DROP PROCEDURE IF EXISTS " + soredProcedureName;
        Connection con = ConnectionUtil.getConnection();
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.execute(queryDrop);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
