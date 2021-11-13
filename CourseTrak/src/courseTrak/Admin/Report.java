package courseTrak.Admin;

/**
 * @author Alex Reep
 *
 */
public class Report {

    private int id;
    private String description;
    private String date;

    public Report(int id, String description, String date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }

    public int getID() {
        return id;
    }

    public void setID(int iD) {
        id = iD;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
