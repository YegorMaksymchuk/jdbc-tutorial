package jdbctutorial.application.utils;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class StoredProcedureUtil {

    public void createStoredProcedure() throws SQLException {

        String createProcedure = null;

        deleteStoredProcedure("show_developers_by_project");

        createProcedure =
                "CREATE PROCEDURE show_developers_by_project() " +
                        "BEGIN " +
                        "SELECT DISTINCT developers.first_name,\n" +
                        "projects.name\n" +
                        "FROM developers \n" +
                        "INNER JOIN projects\n" +
                        "order by projects.name;" +
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

    public void callStoredProcedure(String soredProcedureName) {

    }

    public void deleteStoredProcedure(String soredProcedureName) throws SQLException {
        String queryDrop = "DROP PROCEDURE IF EXISTS " + soredProcedureName;
        Connection con = ConnectionUtil.getConnection();
        Statement stmtDrop = null;
        try {
            System.out.println("Calling DROP PROCEDURE");
            stmtDrop = con.createStatement();
            stmtDrop.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmtDrop != null) {
                stmtDrop.close();
            }
        }
    }

}
