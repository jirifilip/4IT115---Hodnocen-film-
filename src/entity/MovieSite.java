/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Jirka_
 */
public class MovieSite extends Model {
    
    private int id;
    private String name;
    private String description;
    private String url;
    private int addsIntensity;
    private boolean containsMovies;
    private boolean containsTvShows;
    private boolean requiresSignIn;
    
    private Collection<User> fansList;

    public MovieSite(int id, String name, String description, String url,
            int addsIntensity, boolean containsMovies, boolean containsTvShows,
            boolean requiresSignIn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.addsIntensity = addsIntensity;
        this.containsMovies = containsMovies;
        this.containsTvShows = containsTvShows;
        this.requiresSignIn = requiresSignIn;
        
    }
    
    public MovieSite(
            String name, String description, String url, int addsIntensity,
            boolean containsMovies, boolean containsTvShows, boolean requiresSignIn) {
        
        this.name = name;
        this.description = description;
        this.url = url;
        this.addsIntensity = addsIntensity;
        this.containsMovies = containsMovies;
        this.containsTvShows = containsTvShows;
        this.requiresSignIn = requiresSignIn;
      
        
        Date date = new Date();
        
        ArrayList<String> paramList = new ArrayList<>();
        paramList.add(this.name);
        paramList.add(this.description);
        paramList.add(this.url);
        paramList.add(String.valueOf(this.addsIntensity));
        paramList.add(String.valueOf(this.containsMovies? 1 : 0));
        paramList.add(String.valueOf(this.containsTvShows? 1 : 0));
        paramList.add(String.valueOf(this.requiresSignIn? 1 : 0));
        
        Database
            .getInstance()
            .executeAction(
                    "insert into movie_page(name, description, url, add_intensity, contains_movies, contains_tv_shows, requires_login) "
                    + "values(?, ?, ?, ?, ?, ?, ?)" , paramList);
        
    }
    
    public void rate(boolean upOrDown, int userId) {
        ArrayList<String> params = new ArrayList<>();
        
        params.add(String.valueOf(this.id));
        params.add(String.valueOf(userId));
        params.add(upOrDown ? "1" : "-1");

        Database
                .getInstance()
                .executeAction("insert into movie_page_rating"
                        + " (movie_page_id, user_id, rating_value)"
                        + "values (?, ?, ?)",
                        params);
    }
    
    public int getRating() {
        
        int rating = 0;
        
        try (Connection conn = Database.getConnection()){
            PreparedStatement statement = null;
            ResultSet rs = null;
            
            statement = conn.prepareStatement(
                    "select sum(rating_value) from movie_page_rating"
                    + " where movie_page_id = ?");
            
            statement.setInt(1, this.id);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
                    
                rating = rs.getInt(1);
            }
            
            statement.close();
            conn.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return rating;
    }
    
    public void update() {
        ArrayList<String> paramList = new ArrayList<>();
        
        paramList.add(this.name);
        paramList.add(this.description);
        paramList.add(this.url);
        paramList.add(String.valueOf(this.addsIntensity));
        paramList.add(String.valueOf(this.containsMovies? 1 : 0));
        paramList.add(String.valueOf(this.containsTvShows? 1 : 0));
        paramList.add(String.valueOf(this.requiresSignIn? 1 : 0));
        paramList.add(String.valueOf(this.id));
        
//        String q = "update movie_page set name=" + this.name + ","
//                + " description=" + this.description + ","
//                + " url=" + this.url + ","
//                + " add_intensity=" + String.valueOf(this.addsIntensity) + ","
//                + " contains_movies=" + String.valueOf(this.containsMovies? 1 : 0) + ","
//                + " contains_tv_shows=" + String.valueOf(this.containsTvShows? 1 : 0) + ","
//                + " requires_login=" + String.valueOf(this.requiresSignIn? 1 : 0) + ","
//                + " where id_movie_page=" + String.valueOf(this.id) + ",";
//        
//        System.out.println(q);
        
        Database
            .getInstance()
            .executeAction(
                "update movie_page set name=?,"
                + " description=?,"
                + " url=?,"
                + " add_intensity=?,"
                + " contains_movies=?,"
                + " contains_tv_shows=?,"
                + " requires_login=?"
                + " where id_movie_page=? "
                , paramList);
    }
    
    
    public static ArrayList<MovieSite> fetchAll() {
        ArrayList<MovieSite> movies = new ArrayList<>();
        
        try (Connection conn = Database.getConnection()){
            PreparedStatement statement = null;
            ResultSet rs = null;
            
            statement = conn.prepareStatement("select * from movie_page");
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
                
                int id_ = rs.getInt(1);
                
                String title = rs.getString(2);
                String description = rs.getString(3);
                String url = rs.getString(4);
                int addIntensity = rs.getInt(5);
                boolean containsMovies = rs.getBoolean(6);
                boolean containsShows = rs.getBoolean(7);
                boolean requiresLogin = rs.getBoolean(8);
                        
                MovieSite moviePage = new MovieSite(id_, title, description,
                    url, addIntensity, containsMovies, containsShows, requiresLogin);
                
                
                movies.add(moviePage);
            }
            
            statement.close();
            conn.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return movies;
    }
    
    
    
    public static ArrayList<MovieSite> searchFor(String searchString) {
        ArrayList<MovieSite> movies = new ArrayList<>();
        
        try (Connection conn = Database.getConnection()){
            PreparedStatement statement = null;
            ResultSet rs = null;
            
            statement = conn.prepareStatement("select * from movie_page"
                    + " where name like ? "
                    + "or description like ?");
            
            statement.setString(1, "%" + searchString + "%");
            statement.setString(2, "%" + searchString + "%");
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
                
                int id_ = rs.getInt(1);
                
                String title = rs.getString(2);
                String description = rs.getString(3);
                String url = rs.getString(4);
                int addIntensity = rs.getInt(5);
                boolean containsMovies = rs.getBoolean(6);
                boolean containsShows = rs.getBoolean(7);
                boolean requiresLogin = rs.getBoolean(8);
                        
                MovieSite moviePage = new MovieSite(id_, title, description,
                    url, addIntensity, containsMovies, containsShows, requiresLogin);
                
                
                movies.add(moviePage);
            }
            
            statement.close();
            conn.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return movies;
    }
     
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAddsIntensity() {
        return addsIntensity;
    }

    public void setAddsIntensity(int addsIntensity) {
        this.addsIntensity = addsIntensity;
    }

    public boolean getContainsMovies() {
        return containsMovies;
    }

    public void setContainsMovies(boolean containsMovies) {
        this.containsMovies = containsMovies;
    }

    public boolean getContainsTvShows() {
        return containsTvShows;
    }

    public void setContainsTvShows(boolean containsTvShows) {
        this.containsTvShows = containsTvShows;
    }

    public boolean getRequiresSignIn() {
        return requiresSignIn;
    }

    public void setRequiresSignIn(boolean requiresSignIn) {
        this.requiresSignIn = requiresSignIn;
    }

    public Collection<User> getFansList() {
        return fansList;
    }

    public void setFansList(Collection<User> fansList) {
        this.fansList = fansList;
    }
    
    
    
    public void setAll(
            String name, String description, String url, int addsIntensity,
            boolean containsMovies, boolean containsTvShows, boolean requiresSignIn) {
        
        this.name = name;
        this.description = description;
        this.url = url;
        this.addsIntensity = addsIntensity;
        this.containsMovies = containsMovies;
        this.containsTvShows = containsTvShows;
        this.requiresSignIn = requiresSignIn;
    }

    public void delete() {
        ArrayList<String> params = new ArrayList<>();
        
        params.add(String.valueOf(id));
        
        Database
                .getInstance()
                .executeAction("delete from movie_page where id_movie_page=?"
                        , params);

    }
    
}
