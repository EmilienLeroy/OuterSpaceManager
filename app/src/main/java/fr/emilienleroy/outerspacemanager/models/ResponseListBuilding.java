package fr.emilienleroy.outerspacemanager.models;

import java.util.List;

/**
 * Created by eleroy on 27/02/2018.
 */

public class ResponseListBuilding {
    private int size;
    private List<ApiBuilding> buildings;

    public int getSize(){ return size;}

    public List<ApiBuilding> getBuilding(){ return buildings;}
}
