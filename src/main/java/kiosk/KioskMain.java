package kiosk;
import kiosk.brand.Brand;
import kiosk.brand.BrandUtil;
import kiosk.menu.Menu;
import kiosk.menu.MenuUtil;
import kiosk.user.KioskUser;
import kiosk.user.KioskUserUtil;
import kiosk.user.order.Order;
import kiosk.util.Util;

import java.util.*;


public class KioskMain {
    private static boolean mode = true; //true : ui | false : text
    public static final Scanner sc = new Scanner(System.in);
    private static KioskUserUtil kioskUserUtil;

    public static void main(String[] args) throws InterruptedException {
        Util.addDefault();
        Util.registerAdmin();
        kioskUserUtil = new KioskUserUtil();
        
        System.out.println("구동 모드 선택");
        System.out.println("UI : 1 \n안전모드 : 2");
        System.out.println("!!UI모드에서 오류 발생 시 안전모드로 진행해주세요!!");
        int i = sc.nextInt();

        if (i == 1) {
        	Kiosk.main(new String[]{});
        } else if (i == 2) {
            System.out.println("Admin 계정으로 로그인하시면 추가적으로 사용하실 수 있는 기능이 있습니다!");
            Thread.sleep(3000);
            KioskUser user = null;
            while (true) {
                //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                if (user != null) {
                    System.out.println("====\nWelcome " + user.getName());
                    if (user.getName().equals("Admin")) {
                        System.out.println("====\n0. help\n1. Add User\n2. Login\n3. Logout\n4. Order\n5. Check order\n6. Check user\n7. Add brand\n8. Add menu");
                    } else {
                        System.out.println("====\n0. help\n1. Add User\n2. Login\n3. Logout\n4. Order");
                    }
                } else {
                    System.out.println("====\nWelcome Unknown!");
                    System.out.println("====\n0. help\n1. Add User\n2. Login\n3. Logout\n4. Order");
                }

                int p = sc.nextInt();
                //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                switch (p) {
                    case 0 -> {
                        help();
                    }
                    case 1 -> {
                        user = register();
                    }
                    case 2 -> {
                        user = login(user);
                    }
                    case 3 -> {
                        user = logout(user);
                    }
                    case 4 -> {
                        order(user);
                    }
                    case 5 -> {
                        if (user != null) {
                            if (user.getName().equals("Admin")) {
                                checkOrder();
                            }
                        }
                    }
                    case 6 -> {
                        if (user != null) {
                            if (user.getName().equals("Admin")) {
                                checkUser();
                            }
                        }
                    }
                    case 7 -> {
                        if (user != null) {
                            if (user.getName().equals("Admin")) {
                                addBrand();
                            }
                        }
                    }
                    case 8 -> {
                        if (user != null) {
                            if (user.getName().equals("Admin")) {
                                addMenu();
                            }
                        }
                    }
                }
            }
        } else {
        	System.out.println("Wrong Input!");
        }
    }

    public static void help() {

    }

    public static void addMenu() {
        int ct = 0;
        System.out.println("====\nPlease choose brand.");
        System.out.println("0. Return");
        for (Brand brand : BrandUtil.getBrands()) {
            System.out.println(++ct + ". " + brand.getBrandName());
        }
        Brand brand = null;
        int c = sc.nextInt();
        if (c <= BrandUtil.getBrands().size() && c >= 1) {
            brand = BrandUtil.getBrands().get(c - 1);
            if (brand != null) {
                System.out.println("====\nPlease input new menu's name.");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.println("====\nPlease input new menu's price.");
                int price = sc.nextInt();
                Menu menu = new Menu(name, UUID.randomUUID(), price);
                MenuUtil.addMenu(menu);
                brand.getMenus().add(menu);
                System.out.println("====\nMenu added to brand!");
            }
        } else if (c == 0) {
            System.out.println("====\nReturned!");
        }
    }

    public static void addBrand() {
        System.out.println("====\nPlease input new brand's name.");
        sc.nextLine();
        String bn = sc.nextLine();
        for (Brand brand : BrandUtil.getBrands()) {
            if (brand.getBrandName().equalsIgnoreCase(bn)) {
                System.out.println("====\nDuplicated name!");
                return;
            }
        }
        BrandUtil.addBrand(new Brand(bn, new ArrayList<>()));
    }

    public static void checkUser() {
        for (KioskUser user : kioskUserUtil.getUsers()) {
            System.out.println("USER " + user.getName() + " UID " + user.getUid() + " PHONE " + user.getNumber() + " MILEAGE " + user.getMileage());
        }
    }

    public static void checkOrder() {
        for (KioskUser user : kioskUserUtil.getUserOrderHashMap().keySet()) {
            List<Order> orders = kioskUserUtil.getUserOrder(user);
            for (Order order : orders) {
                System.out.println("USER " + user.getName() + " | COUNT." + order.getCount() + " | MENU " + order.getMenu().getName() + " | BRAND " + order.getBrand().getBrandName());
            }
        }
    }

    public static KioskUser register() {
        System.out.print("====\nName : ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Phone Number : ");
        sc.nextLine();
        String number = sc.nextLine();
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        KioskUser user = new KioskUser(name, number, UUID.randomUUID());
        KioskUserUtil.addUser(user);
        System.out.println("====\nRegistered!\nName : " + name + "\nnumber : " + number);
        return user;
    }

    public static KioskUser login(KioskUser user) {
        KioskUser user1 = null;
        if (user == null) {
            System.out.println("====\n1. Login by Name\n2. Login by Phone number");
            int n = sc.nextInt();
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            switch (n) {
                case 1 -> {
                    System.out.print("====\nInput your name : ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    List<KioskUser> userList = kioskUserUtil.getUserByName(name);
                    if (userList.size() == 0) {
                        System.out.println("====\nNo data! Please Register First!");
                        break;
                    }
                    System.out.println("====\nChoose your info");
                    System.out.println("0. Return");
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println((i + 1) + ". " + userList.get(i).getNumber());
                    }
                    boolean b = true;
                    while (b) {
                        int c = sc.nextInt();
                        if (c <= userList.size() && c >= 1) {
                            user1 = userList.get(c - 1);
                            System.out.println("====\nWelcome " + user1.getName() + " mileage : " + user1.getMileage());
                            b = false;
                        } else if (c == 0) {
                            b = false;
                            System.out.println("====\nReturned!");
                        } else {
                            System.out.println("====\nWrong Input!");
                        }
                    }
                }
                case 2 -> {
                    System.out.print("====\nInput your Phone number : ");
                    sc.nextLine();
                    String num = sc.nextLine();
                    //.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    if (kioskUserUtil.getUser(num) != null) {
                        user1 = kioskUserUtil.getUser(num);
                        System.out.println("====\nWelcome " + user1.getName() + " mileage : " + user1.getMileage());
                    } else {
                        System.out.println("====\nNo data! Please Register First!");
                    }
                }
            }
        } else {
            user1 = user;
            System.out.println("====\nYou are already logged in!");
        }
        return user1;
    }

    public static KioskUser logout(KioskUser user) {
        if (user == null) {
            System.out.println("====\nYou are already logged out!");
        } else {
            System.out.println("====\nYou are now logged out!");
        }
        return null;
    }

    public static void order(KioskUser user) {
        boolean mb = true;
        List<Menu> cart = new ArrayList<>();
        while (mb) {
            mb = select(user, cart);
        }
        System.out.println();
    }

    public static int orderCount = 0;
    public static boolean select(KioskUser user, List<Menu> cart) {
        boolean mb = true;
        int sum;
        if (user == null) {
            System.out.println("====\nYou are not logged in!\nFor the mileage, Please Log in.");
        }
        if (cart.size() != 0) {
            System.out.println("====\nCart list");
            int c = 0;
            sum = 0;
            for (Menu menu : cart) {
                sum += menu.getPrice();
                System.out.println(++c + ". " + menu.getName() + " | Price : " + menu.getPrice());
            }
            System.out.println("Total : " + sum);
        }
        System.out.println("====\nPlease Choose Brand.");
        System.out.println("0. Return");
        int ct = 0;
        for (Brand brand : BrandUtil.getBrands()) {
            System.out.println(++ct + ". " + brand.getBrandName());
        }
        System.out.println(++ct + ". Order");
        Brand brand = null;
        boolean b = true;
        while (b) {
            int c = sc.nextInt();
            if (c <= BrandUtil.getBrands().size() && c >= 1) {
                brand = BrandUtil.getBrands().get(c - 1);
                if (brand != null) {
                    System.out.println("====\n" + brand.getBrandName());
                    System.out.println("====\nSelect Menu!");
                    System.out.println("0. Return");
                    List<Menu> menus = brand.getMenus();
                    int cnt = 0;
                    for (Menu menu : menus) {
                        System.out.println(++cnt + ". " + menu.getName() + " | Price : " + menu.getPrice());
                    }
                    boolean b2 = true;
                    Menu menu;
                    while (b2) {
                        int c2 = sc.nextInt();
                        if (c2 <= brand.getMenus().size() && c2 >= 1) {
                            menu = brand.getMenus().get(c2 - 1);
                            System.out.println("====\nAdd \"" + menu.getName() + "\" to cart?\n1. Yes\n2. No");
                            int c3 = sc.nextInt();
                            switch (c3) {
                                case 1 -> {
                                    cart.add(menu);
                                    System.out.println("====\nSuccessfully Added " + menu.getName() + " to cart!");
                                }
                                case 2 -> {
                                    System.out.println("====\nSelect other menus!");
                                }
                                default -> {
                                    System.out.println("====\nWrong Input!");
                                }
                            }
                            b2 = false;
                        } else if (c2 == 0) {
                            b2 = false;
                            System.out.println("====\nReturned!");
                        } else {
                            System.out.println("====\nWrong Input!");
                        }
                    }
                }
                b = false;
            } else if (c == 0) {
                mb = false;
                b = false;
                System.out.println("====\nReturned!");
            } else if (c == ct) {
                mb = false;
                if (cart.size() != 0) {
                    System.out.println("====\nCart list");
                    int cr = 0;
                    sum = 0;
                    int qn = ++orderCount;
                    List<Order> orders = new ArrayList<>();
                    for (Menu menu : cart) {
                        sum += menu.getPrice();
                        System.out.println(++cr + ". " + menu.getName() + " | Price : " + menu.getPrice());
                        for (Brand brand1 : BrandUtil.getBrands()) {
                            if (brand1.getMenus().contains(menu)) {
                                orders.add(new Order(brand1, menu, qn));
                            }
                        }
                    }
                    System.out.println("Total Price : " + sum);

                    KioskUser odr;
                    if (user != null) {
                        user.setMileage(user.getMileage() + 1);
                        odr = user;
                    } else {
                        odr = new KioskUser("Unknown", "0", UUID.randomUUID());
                    }
                    KioskUserUtil.addUser(odr);
                    kioskUserUtil.addUserOrder(odr, orders);
                    System.out.println("====\nOrder Complete!\nOrder no. " + qn);
                    b = false;
                } else {
                    System.out.println("====\nCart is empty!");
                }
            } else {
                System.out.println("====\nWrong Input!");
            }
        }
        return mb;
    }
}