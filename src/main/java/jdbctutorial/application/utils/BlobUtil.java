package jdbctutorial.application.utils;

import jdbctutorial.application.model.Developer;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BlobUtil {

    public void addDeveloperData(Developer developer, String avatar, String cv) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO developer_data (developer_id, photo, resume) VALUES (?,?,?)";
        try {
            FileInputStream photo = new FileInputStream(new File(avatar));
            FileInputStream resume = new FileInputStream(new File(cv));
            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, developer.getId());
            preparedStatement.setBinaryStream(2, photo);
            preparedStatement.setBinaryStream(3, resume);
            int res = preparedStatement.executeUpdate();
            System.out.println("completed : " + res);

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


    public File getPhoto(Developer developer) throws SQLException, IOException {
        File file = new File(developer.getLastName() + " " + developer.getFirstName() + ".jpg");
        FileOutputStream photo = new FileOutputStream(file);
        String sql = "SELECT photo FROM developer_data WHERE developer_id = " + developer.getId();
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            InputStream inputStream = resultSet.getBinaryStream("photo");
            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) > 0) {
                photo.write(buffer);
            }
        }
        return file;
    }

    public File getCV(Developer developer) throws SQLException, IOException {
        File file = new File(developer.getLastName() + " " + developer.getFirstName() + ".pdf");
        FileOutputStream cv = new FileOutputStream(file);
        String sql = "SELECT resume FROM developer_data WHERE developer_id = " + developer.getId();
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            InputStream inputStream = resultSet.getBinaryStream("resume");
            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) > 0) {
                cv.write(buffer);
            }
        }
        return file;
    }
}
