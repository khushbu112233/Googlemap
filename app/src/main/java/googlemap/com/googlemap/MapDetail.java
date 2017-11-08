package googlemap.com.googlemap;

/**
 * Created by aipxperts-ubuntu-01 on 19/4/17.
 */

public class MapDetail {


    public String id;
    public String subject;
    public String latitude;
    public String longitude;
    public String business_facebook_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLatitude() {
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

    public String getBusiness_facebook_url() {
        return business_facebook_url;
    }

    public void setBusiness_facebook_url(String business_facebook_url) {
        this.business_facebook_url = business_facebook_url;
    }
}
