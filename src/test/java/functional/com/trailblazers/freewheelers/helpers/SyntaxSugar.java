package functional.com.trailblazers.freewheelers.helpers;

public class SyntaxSugar {

    public static final String SOME_PHONE_NUMBER = "555-123456";
    public static final String SOME_PASSWORD = "secret";

    public static final String EMPTY = "";

    public static String emailFor(String userName) {
        return userName.replace(' ', '-') + "@random-email.com";
    }

    public static String email_is(String s) {
        return s;
    }

    public static String password_is(String s) {
        return s;
    }

    public static String name_is(String s) {
        return s;
    }

    public static String price_is(String s) {
        return s;
    }

    public static String type_is(String s) {
        return s;
    }

    public static String phone_number_is(String s) {
        return s;
    }

    public static String description_is(String s) {
        return s;
    }

    public static String quantity_is(String s) {
        return s;
    }

    public static String from(String s) {
        return s;
    }

    public static String to(String s) {
        return s;
    }



}