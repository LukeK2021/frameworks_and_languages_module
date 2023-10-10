package luke.lukes.server;

import jakarta.validation.constraints.NotEmpty;

public class LukesServerData {
    
    public String internal_id;

    @NotEmpty
    public String user_id;

    
    public String[] keywords;

    
    public String description;
    
    
    public long lat;

    
    public long lon;

    
    public String getUser_id() {
        return this.user_id;
    }

    
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String[] getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public long getLat() {
        return this.lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return this.lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public String getInternal_id() {
        return this.internal_id;
    }

    public void setInternal_id(String internal_id) {
        this.internal_id = internal_id;
    }
    

    public LukesServerData(String user_id, String[] keywords, String description, long lat, long lon, String internal_id){
        this.user_id=user_id;
        this.internal_id=internal_id;
        this.keywords=keywords;
        this.description=description;
        this.lat=lat;
        this.lon=lon;
    }

    public LukesServerData()
    {

    }
}
