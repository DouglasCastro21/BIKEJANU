package bike.douglas.com.bikejanu.Entidades;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class LocalBikesMaps {



    public String latitude;
    public String longitude;





    @Exclude
    public Map<String , Object> toMap() {
        HashMap<String , Object> hashMapBike = new HashMap<>();


        hashMapBike.put("latitude",getLatitude());
        hashMapBike.put("longitude",getLongitude());


        return hashMapBike;

    }



    public String  getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
