

public class main {


    static boolean test_calculate_price(){

        double expected_price = 29500;
        double actual_price = calculatePrice(20000, 2500, 10000, 3, 10);

        return expected_price == actual_price;

    }


    static double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount;
        double result;

        if (extras >= 5) {
            addon_discount = 15;
        }
        else if (extras >= 3) {
            addon_discount = 10;
        }
        else addon_discount = 0;

        if (discount > addon_discount) {
            addon_discount = discount;
        }

        result = baseprice/100.0 * (100-discount) + specialprice
                + extraprice/100.0 * (100-addon_discount);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(test_calculate_price());
    }
}