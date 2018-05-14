package fr.emilienleroy.outerspacemanager.models;

/**
 * Created by emili on 14/05/2018.
 */

public class RequestShip {
    private Integer shipId;
    private Integer amount;


    public RequestShip(Integer shipId, Integer amount) {
        this.shipId = shipId;
        this.amount = amount;
    }

    public Integer getShipId() {
        return shipId;
    }

    public Integer getAmount() {
        return amount;
    }
}
