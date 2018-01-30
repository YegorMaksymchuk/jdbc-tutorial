package jdbctutorial.application.utils;

import jdbctutorial.application.model.Developer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhotoUtil {

    public void addPhoto(Developer developer, String path) throws SQLException, IOException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE developer_avatar SET avatar=? WHERE developer_id=?";

        int resultExecution;
        try {

            BufferedImage photo = ImageIO.read(new File(path));

            Blob blob = ConnectionUtil.getConnection().createBlob();

            try (OutputStream os = blob.setBinaryStream(1);) {
                ImageIO.write(photo, "jpg", os);
            }
            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sql);
            preparedStatement.setBlob(1, blob);
            preparedStatement.setLong(2, developer.getId());

            resultExecution = preparedStatement.executeUpdate();

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

    public File getPhoto(Developer developer) {
        return null;
    }
}
