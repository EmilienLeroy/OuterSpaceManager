package fr.emilienleroy.outerspacemanager.models;

import java.util.List;

/**
 * Created by emili on 14/05/2018.
 */

public class RequestAttack {
    private List<RequestShip> ships;

    public RequestAttack(List<RequestShip> ships) {
        this.ships = ships;
    }

    public List<RequestShip> getShips() {
        return ships;
    }
}
