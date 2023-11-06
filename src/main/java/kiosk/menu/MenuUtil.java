package kiosk.menu;

import java.util.HashSet;
import java.util.Set;

public class MenuUtil {
    private static Set<Menu> menuSet = new HashSet<>();

    public static boolean addMenu(Menu menu) {
        return menuSet.add(menu);
    }

    public static Menu getMenu(String menuName) {
        for (Menu menu : menuSet) {
            if (menu.getName().equals(menuName)) {
                return menu;
            }
        }
        return null;
    }
}
