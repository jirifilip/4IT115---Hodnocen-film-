/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import db.Database;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Jirka_
 */
public class MoviePage extends Model {
    
    private int id;
    private String name;
    private String description;
    private String url;
    private int addsIntensity;
    private boolean containsMovies;
    private boolean containsTvShows;
    private boolean requiresSignIn;
    
    private Collection<User> fansList;

    public MoviePage(int id, String name, String description, String url,
            int addsIntensity, boolean containsMovies, boolean containsTvShows,
            boolean requiresSignIn) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.addsIntensity = addsIntensity;
        this.containsMovies = containsMovies;
        this.containsTvShows = containsTvShows;
        this.requiresSignIn = requiresSignIn;
        
    }
    
    public MoviePage(
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
        paramList.add(String.valueOf(this.containsMovies));
        paramList.add(String.valueOf(this.containsTvShows));
        paramList.add(String.valueOf(this.requiresSignIn));
        
        Database
            .getInstance()
            .executeAction(
                    "insert into movie_page(name, description, url, add_intensity, contains_movies, contains_tv_shows, requires_login) "
                    + "values(?, ?, ?, ?)" , paramList);
        
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
    
    
    
    
}
