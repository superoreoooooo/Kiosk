package kiosk.user;

import java.util.UUID;

public class KioskUser {
    private String name; //user name
    private String number; //phone number
    private UUID uid;
    private int mileage;

    public KioskUser(String name, String number, UUID uid) {
        this.name = name;
        this.number = number;
        this.uid = uid;
        this.mileage = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UUID getUid() {
        return this.uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
