package kiosk.user.order;

import kiosk.brand.Brand;
import kiosk.menu.Menu;

public class Order {
    private Brand brand;
    private Menu menu;
    private int count;
    private boolean done;

    public Order(Brand brand, Menu menu, int count) {
        this.brand = brand;
        this.menu = menu;
        this.done = false;
        this.count = count;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
