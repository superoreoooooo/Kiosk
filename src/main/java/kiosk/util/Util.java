package kiosk.util;

import kiosk.brand.Brand;
import kiosk.brand.BrandUtil;
import kiosk.menu.Menu;
import kiosk.menu.MenuUtil;
import kiosk.user.KioskUser;
import kiosk.user.KioskUserUtil;

import java.util.*;

public class Util {
    public static void addDefault() {
        MenuUtil.addMenu(new Menu("Iced Americano", UUID.randomUUID(), 1500));
        MenuUtil.addMenu(new Menu("Mango Yogurt Smoothie", UUID.randomUUID(), 3000));
        MenuUtil.addMenu(new Menu("Rice Noodles", UUID.randomUUID(), 5000));
        MenuUtil.addMenu(new Menu("Fried Rice", UUID.randomUUID(), 5000));
        MenuUtil.addMenu(new Menu("Pork Cutlet", UUID.randomUUID(), 6000));
        MenuUtil.addMenu(new Menu("Udon", UUID.randomUUID(), 5500));
        MenuUtil.addMenu(new Menu("Egg Rice", UUID.randomUUID(), 6000));
        MenuUtil.addMenu(new Menu("Kimchi Jjigae", UUID.randomUUID(), 7000));

        List<Menu> gs = new ArrayList<>();
        List<Menu> hs = new ArrayList<>();
        List<Menu> hzs = new ArrayList<>();
        List<Menu> ms = new ArrayList<>();

        gs.add(MenuUtil.getMenu("Iced Americano"));
        gs.add(MenuUtil.getMenu("Mango Yogurt Smoothie"));
        hs.add(MenuUtil.getMenu("Rice Noodles"));
        hs.add(MenuUtil.getMenu("Fried Rice"));
        hzs.add(MenuUtil.getMenu("Pork Cutlet"));
        hzs.add(MenuUtil.getMenu("Udon"));
        ms.add(MenuUtil.getMenu("Egg Rice"));
        ms.add(MenuUtil.getMenu("Kimchi Jjigae"));

        BrandUtil.addBrand(new Brand("Grazie", gs));
        BrandUtil.addBrand(new Brand("Hongdae Rice Noodles", hs));
        BrandUtil.addBrand(new Brand("Hazben", hzs));
        BrandUtil.addBrand(new Brand("Mankwon Whabap", ms));
    }

    public static void registerAdmin() {
        KioskUser admin = new KioskUser("Admin", "1000", UUID.randomUUID());
        KioskUserUtil util = new KioskUserUtil();
        util.addUser(admin);
    }
}
