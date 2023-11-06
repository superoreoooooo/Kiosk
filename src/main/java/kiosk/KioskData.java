package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import kiosk.brand.Brand;
import kiosk.user.KioskUser;
import kiosk.user.KioskUserUtil;
import kiosk.user.order.Order;
import kiosk.util.Util;

public class KioskData {
	private static KioskUserUtil kioskUserUtil;
	public static KioskUser user;
	public static Brand brand;
	public static List<Order> orders = new ArrayList<>();
	public static int count = 1;
	public static boolean isInited = false;
	
	public KioskData() {
		user = null;
		brand = null;
	}
	
	public static void init() {
		if (!isInited) {
			Util.addDefault();
	        Util.registerAdmin();
		}
	}
	
	public static KioskUser login(String num) {
		kioskUserUtil = new KioskUserUtil();
		if (kioskUserUtil.getUser(num) != null) {
			return kioskUserUtil.getUser(num);
		} else {
			return null;
		}
	}
	
	public static KioskUser register(String num, String name) {
		kioskUserUtil = new KioskUserUtil();
		for (KioskUser user : kioskUserUtil.getUsers()) {
			if (Objects.equals(user.getNumber(), num)) {
				return null;
			}
		}
		return new KioskUser(name, num, UUID.randomUUID());
	}
}
