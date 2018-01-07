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
    
}
