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
    private static String connectURL = "jdbc:mysql://localhost:3306/javaTest";


    public static ArrayList<NetflixShow> getShowDetails()
    {
        ArrayList<NetflixShow> netflixShows = new ArrayList<>();


        String sql = "select * from netflix";

        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next())
            {
                String type  = resultSet.getString("type");
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                String cast = resultSet.getString("cast");
                String rating = resultSet.getString("rating");
                String showId = resultSet.getString("showId");



                NetflixShow netflixShow = new NetflixShow(showId,title,type,director,cast,rating);
                netflixShow.setShowId(showId);
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
