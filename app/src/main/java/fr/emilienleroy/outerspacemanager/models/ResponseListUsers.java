package fr.emilienleroy.outerspacemanager.models;

import java.util.List;

/**
 * Created by eleroy on 16/04/2018.
 */

public class ResponseListUsers {
    private int size;
    private List<ApiUser> users;

    public int getSize(){ return size;}

    public List<ApiUser> getRank(){ return users;}
}
