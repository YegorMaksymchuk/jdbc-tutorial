package jdbctutorial.application.utils;

import jdbctutorial.application.model.Developer;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlobUtil {

    public void addDeveloperData(Developer developer, String avatar, String cv) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO developer_avatar (developer_id, avatar, cv) VALUES (?,?,?)";
        try {
                FileInputStream photo = new FileInputStream(new File(avatar));
                Reader resume = new FileReader(cv);
                preparedStatement = ConnectionUtil.getConnection().prepareStatement(sql);
                preparedStatement.setLong(1, developer.getId());
                preparedStatement.setBinaryStream(2, photo);
                preparedStatement.setClob(3, resume);
                int res = preparedStatement.executeUpdate();
            System.out.println("completed : "+res);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
