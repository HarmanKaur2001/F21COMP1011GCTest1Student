package Utilities;

import com.example.f21comp1011gctest1student.NetflixShow;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    private static String user = "student";
    private static String pw = "student";
    private static String connectURL = "jdbc:mysql://localhost:3306/javaTest";


    public static ArrayList<NetflixShow> getShowDetails(String showType, String showRating) {
        ArrayList<NetflixShow> netflixShows = new ArrayList<>();

        ResultSet resultSet = null;


        //for all the ratings and types
        String sql = "select * from netflix where type  != ? AND rating != ?";

        //now filter is applied based on type
        if (!showType.equals("All") && showRating.equals("All ratings"))
        sql = "select * from netflix where type  = ? AND rating != ?";

        //now filter is applied based on type
        if (showType.equals("All") && !showRating.equals("All ratings"))
        sql = "select * from netflix where type  != ? AND rating = ?";


        //filter based on types and ratings
        if (!showType.equals("All") && !showRating.equals("All ratings"))
        sql = "select * from netflix where type  != ? AND rating != ?";


        try (
                Connection conn = DriverManager.getConnection(connectURL, user, pw);
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, showType);
            statement.setString(2, showRating);
            resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                String cast = resultSet.getString("cast");
                String rating = resultSet.getString("rating");
                String showId = resultSet.getString("showId");


                NetflixShow netflixShow = new NetflixShow(showId, title, type, director, cast, rating);
                netflixShows.add(netflixShow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
        return netflixShows;

    }
}

