package kiosk.brand;

import java.util.*;

public class BrandUtil {
    private static List<Brand> brands = new ArrayList<>();

    public static boolean addBrand(Brand b) {
        return brands.add(b);
    }

    public static List<Brand> getBrands() {
        return brands;
    }

    public static Brand getBrand(String brandName) {
        for (Brand brand : brands) {
            if (brand.getBrandName().equalsIgnoreCase(brandName)) {
                return brand;
            }
        }
        return null;
    }
}
