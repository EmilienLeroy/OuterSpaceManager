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
    private Integer level;
    private String effect;
    private String imageUrl;
    private String name;

    public String getCode(){
        return code;
    }

    public Integer getBuildingId(){
        return buildingId;
    }

    public Integer getLevel() {return level;}

    public String getEffect() {return effect;}

    public String getImageUrl() {return imageUrl;}

    public String getName() {return name;}
}
