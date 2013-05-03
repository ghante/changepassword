package functional.com.trailblazers.freewheelers.helpers;

public class SyntaxSugar {

    public static final String SOME_PHONE_NUMBER = "555-123456";
    public static final String EMPTY = "";
    public static final String SOME_PASSWORD = "secret";

    public static String email_is(String s) {
        return s;
    }

    public static String password_is(String s) {
        return s;
    }

    public static String name_is(String s) {
        return s;
    }

    public static String phone_number_is(String s) {
        return s;
    }

    public static String emailFor(String userName) {
        return userName.replace(' ', '-') + "@random-email.com";
    }
}