package models;

public class Guest {
    private String name;
    private char number;
    private String gender;
    private String date;
    private String address;
    private boolean tandc;
    
    public Guest(String name, char number, String gender, String date, String address, boolean tandc) {
        this.name = name;
        this.number = number;
        this.gender = gender;
        this.date = date;
        this.address = address;
        this.tandc = tandc;
    }
    
    public String getName() { return name; }
    public char getNumber() { return number; }
    public String getGender() { return gender; }
    public String getDate() { return date; }
    public String getAddress() { return address; }
    public boolean getTandC() { return tandc; }
    
    @Override
    public String toString() {
        return name + "-" + number + "-" + gender + "-" + date + "-" + address + "-" + tandc;
    }
}
