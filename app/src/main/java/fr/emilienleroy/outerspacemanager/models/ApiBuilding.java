package fr.emilienleroy.outerspacemanager.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;

/**
 * Created by eleroy on 23/01/2018.
 */

public class ApiBuilding {
    private String code;
    private Integer buildingId;

    public String getCode(){
        return code;
    }

    public Integer getBuildingId(){
        return buildingId;
    }
}
