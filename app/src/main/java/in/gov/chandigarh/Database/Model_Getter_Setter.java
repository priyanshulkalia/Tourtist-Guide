package in.gov.chandigarh.Database;

/**
 * Created by Microsoft on 9/17/2017.
 */

public class Model_Getter_Setter
{

    private double lat;
    private double lng;
    private String name;
    private String format_add;
    private String rating;
    private  String icon;
    private String photo;
    private String width;
   private String reference;

    public String getId() {
        return id;
    }

    private String id;


    public String getPhoto() {
        return photo;
    }

    public String getWidth() {
        return width;
    }

    public String getReference() {
        return reference;
    }

    public String getIcon() {
        return icon;
    }


    public Model_Getter_Setter(String name, String format_add, String rating,
                               double lat, double lng, String icon, String photo, String width,String reference,String id) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.format_add = format_add;
        this.rating = rating;
        this.icon = icon;
        this.photo = photo;
        this.width = width;
        this.reference=reference;
        this.id=id;
    }




    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }


    public String getName() {
        return name;
    }

    public String getFormat_add() {
        return format_add;
    }

    public String getRating() {
        return rating;
    }

}
