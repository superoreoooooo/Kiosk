package kiosk.user;

import kiosk.user.order.Order;

import java.util.*;

public class KioskUserUtil {
    private static Set<KioskUser> users = new HashSet<>();
    private static HashMap<KioskUser, List<Order>> userOrderHashMap = new HashMap<>();

    public Set<KioskUser> getUsers() {
        return users;
    }

    public HashMap<KioskUser, List<Order>> getUserOrderHashMap() {
        return userOrderHashMap;
    }

    public static boolean addUser(KioskUser user) {
        return users.add(user);
    }

    public List<KioskUser> getUserByName(String name) {
        List<KioskUser> target = new ArrayList<>();
        for (KioskUser user : users) {
            if (user.getName().equals(name)) {
                target.add(user);
            }
        }
        return target;
    }

    public KioskUser getUser(String number) {
        for (KioskUser user : users) {
            if (user.getNumber().equals(number)) {
                return user;
            }
        }
        return null;
    }

    public KioskUser getUser(UUID uid) {
        for (KioskUser user : users) {
            if (user.getUid().equals(uid)) {
                return user;
            }
        }
        return null;
    }

    public void addUserOrder(KioskUser user, List<Order> orders) {
        userOrderHashMap.put(user, orders);
    }

    public List<Order> getUserOrder(KioskUser user) {
        return userOrderHashMap.get(user);
    }
}
