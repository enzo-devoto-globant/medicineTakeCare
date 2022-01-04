package enzoDevoto.apps.medicineTakeCare.web.utils;

public class PatientConstants {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECCION = "asc";


    public static final String EMAIL_VALIDATION =
            "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

    public static final String TEN_DIGITS_PHONE_VALIDATION = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\.0-9]{9,15}$";

}