package fr.emilienleroy.outerspacemanager.models;

import java.util.List;

/**
 * Created by eleroy on 13/03/2018.
 */

public class ResponseListShip {
    private int size;
    private List<ApiShip> ships;

    public int getSize(){ return size;}

    public List<ApiShip> getShips(){ return ships;}
}
