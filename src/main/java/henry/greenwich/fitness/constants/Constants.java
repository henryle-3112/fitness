package henry.greenwich.fitness.constants;

public class Constants {
    // email subject reset password
    public static final String EMAIL_SUBJECT_RESET_PASSWORD = "Greenwich Fitness - Reset Password";

    // email subject active account
    public static final String EMAIL_SUBJECT_ACTIVE_ACCOUNT = "Greenwich Fitness - Active Account";
    
    // account status
    public static final String ACCOUNT_STATUS_EMAIL_CONFIRMED = "EMAIL_CONFIRMED";
    public static final String ACCOUNT_STATUS_EMAIL_NOT_CONFIRMED = "EMAIL_NOT_CONFIRMED";
    public static final String ACCOUNT_STATUS_PASSWORD_RESET = "PASSWORD_RESET";
    public static final String ACCOUNT_STATUS_PASSWORD_NOT_RESET = "PASSWORD_NOT_RESET";
    
    // security constants
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/sign-up";
    
    // path to upload image
    public static final String PATH_PUBLIC_IMAGE_STRING = "src/main/resources/static/upload/";

    // paypal client
    public static final String PAYPAL_CLIENT_ID = "AZO0eJ86SfloUJjP5C_B9uzZaTsiX_VJVtTijSGaEVr16WYGFpS-YdIEvH2oYDo1hk_mPJ79jlVcMT0K";

    // paypal secret
    public static final String PAYPAL_SECRET = "EMgFSH4yBhc74CRcSZ0yWvznsNYN6x-BZfsqPLE_rMSAoPVLxKYQFOT36R-9ghBV9HzUIfI1B14OsEk4";
}
