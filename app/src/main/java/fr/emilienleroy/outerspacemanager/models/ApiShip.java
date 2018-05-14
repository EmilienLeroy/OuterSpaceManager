package fr.emilienleroy.outerspacemanager.models;

/**
 * Created by eleroy on 13/03/2018.
 */

public class ApiShip {
    private Integer shipId;
    private String name;
    private Integer life;
    private Integer speed;
    private Integer amount;

    public Integer getShipID(){ return shipId;}
    public String getName(){return name;}
    public Integer getLife(){return life;}
    public Integer getSpeed(){return speed;}
    public Integer getAmount(){return amount;}
}
