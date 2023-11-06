package kiosk.brand;

import kiosk.menu.Menu;

import java.util.List;

public class Brand {
    private String brandName;
    private List<Menu> menus;

    public Brand(String brandName, List<Menu> menus) {
        this.brandName = brandName;
        this.menus = menus;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }   

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
