package fr.emilienleroy.outerspacemanager.models;

/**
 * Created by eleroy on 23/01/2018.
 */

public class ApiUser {
    private String username;
    private Double gas;
    private Double minerals;
    private Double points;
    public String getUser(){
        return username;
    }
    public int getGas(){
        int g = ((int)(gas*100))/100;
        return g;
    }
    public int getMinerals(){
        int mine = ((int)(minerals*100))/100;
        return mine;
    }
    public Double getPoints(){
        return points;
    }
}
