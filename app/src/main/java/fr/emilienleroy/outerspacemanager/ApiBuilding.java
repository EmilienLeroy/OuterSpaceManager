package fr.emilienleroy.outerspacemanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;

/**
 * Created by eleroy on 23/01/2018.
 */

class ApiBuilding {
    private String code;
    private Array buildings;
    private Integer buildingId;

    public String getCode(){
        return code;
    }

    public Integer getBuiding(){
        return buildingId;
    }
}
