package kiosk.menu;

import java.util.UUID;

public class Menu {
    private String name;
    private UUID menuUid;
    private int price;

    public Menu(String name, UUID menuUid, int price) {
        this.name = name;
        this.menuUid = menuUid;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getMenuUid() {
        return menuUid;
    }

    public void setMenuUid(UUID menuUid) {
        this.menuUid = menuUid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
