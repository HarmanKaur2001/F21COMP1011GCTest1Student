package Utilities;

import com.example.f21comp1011gctest1student.NetflixShow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtility {
    private static String user = "student";
    private static String pw = "student";
    private static String connectURL = "jdbc:mysql://localhost:3306/javaProjects";


    public static ArrayList<NetflixShow> getCameraDetails()
    {
        ArrayList<NetflixShow> netflixShows = new ArrayList<>();

        String sql = "SELECT cameras.cameraId, make, model, megapixels, price, digital, mirrorless, COUNT(salesId) AS 'Units Sold' \n" +
                "FROM cameras LEFT JOIN cameraSales ON cameras.cameraId = cameraSales.cameraId\n" +
                "GROUP BY cameras.cameraId;";

        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next())
            {
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                double mp = resultSet.getDouble("megapixels");
                double price = resultSet.getDouble("price");
                boolean mirrorless = resultSet.getBoolean("mirrorless");
                boolean digital = resultSet.getBoolean("digital");
                int cameraId = resultSet.getInt("cameraId");
                int unitsSold = resultSet.getInt("Units Sold");

                NetflixShow netflixShow = new NetflixShow();
                //newCamera.setCameraId(cameraId);
                //newCamera.setUnitsSold(unitsSold);
                netflixShows.add(netflixShow);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return netflixShows;
    }
}
