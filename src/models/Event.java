package models;

public class Event {
    private String date;
    private String description;
    private String frequency;
    private String email;
    private String alarm;
    private boolean selected;
    
    public Event(String date, String description, String frequency, String email, String alarm, boolean selected) {
        this.date = date;
        this.description = description;
        this.frequency = frequency;
        this.email = email;
        this.alarm = alarm;
        this.selected = selected;
    }
    
    public String getDate() { return date; }
    public String getDescription() { return description; }
    public String getFrequency() { return frequency; }
    public String getEmail() { return email; }
    public String getAlarm() { return alarm; }
    public boolean getSelected() { return selected; }
    
    @Override
    public String toString() {
        return description + " - " + date + " - " + frequency + "-" + email + "-" + alarm + "-" + selected;
    }
}
