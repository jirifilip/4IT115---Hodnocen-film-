/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Collection;

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
    private int containsMovies;
    private int containsTvShows;
    private int requiresSignIn;
    
    private Collection<User> fansList;

    public MoviePage(int id, String name, String description, String url, int addsIntensity, int containsMovies, int containsTvShows, int requiresSignIn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.addsIntensity = addsIntensity;
        this.containsMovies = containsMovies;
        this.containsTvShows = containsTvShows;
        this.requiresSignIn = requiresSignIn;
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

    public int getContainsMovies() {
        return containsMovies;
    }

    public void setContainsMovies(int containsMovies) {
        this.containsMovies = containsMovies;
    }

    public int getContainsTvShows() {
        return containsTvShows;
    }

    public void setContainsTvShows(int containsTvShows) {
        this.containsTvShows = containsTvShows;
    }

    public int getRequiresSignIn() {
        return requiresSignIn;
    }

    public void setRequiresSignIn(int requiresSignIn) {
        this.requiresSignIn = requiresSignIn;
    }

    public Collection<User> getFansList() {
        return fansList;
    }

    public void setFansList(Collection<User> fansList) {
        this.fansList = fansList;
    }
    
    
    
    
}
