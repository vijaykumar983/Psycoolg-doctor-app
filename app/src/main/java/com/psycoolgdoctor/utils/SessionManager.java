package com.psycoolgdoctor.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.databinding.BaseObservable;

import static android.content.Context.MODE_PRIVATE;

public class SessionManager extends BaseObservable {

    private final String IS_LOGIN = "isLoggedIn";
    private final String AUTH_TOKEN = "auth_token";
    private final String API_TOKEN = "api_token";

    private final String USER_ID = "user_id";
    private final String EMAIL = "email";
    private final String USERNAME = "username";
    private final String STD_CODE = "std_code";
    private final String IS_MOBILE = "is_mobile";
    private final String PHONE = "phone";
    private final String IMAGE = "image";
    private final String FIRST_NAME = "first_name";
    private final String LAST_NAME = "last_name";
    private final String LANGUAGE = "language";
    private final String IS_VERIFIED = "is_verified";
    private final String FB_ID = "fb_id";
    private final String GOOGLE_ID = "google_id";
    private final String TWITTER_ID = "twitter_id";

    private final String TOTALITEMAMOUNT = "totalItemAmount";
    private final String TOTALITEMS = "totalItems";
    private final String CARTID = "cartId";
    private final String CARTRESTAURANTID = "cartRestaurantId";
    private final String CARTBRANCHID = "cartBranchId";
    private final String DEFAULTADDRESS = "defaultAddress";


    private final String LAT = "lat";
    private final String LNG = "lng";


    private final String USER_IMAGE = "user_image";
    private final String TOKEN = "token";
    private final String UNIQUE_ID = "unique_id";
    private final String DEVICE_TOKEN = "device_token";

    private static SharedPreferences shared;
    private static SharedPreferences.Editor editor;
    private static SessionManager session;

    public static SessionManager getInstance(Context context) {
        if (session == null) {
            session = new SessionManager();
        }
        if (shared == null) {
            shared = context.getSharedPreferences("PsyCooLGApp", MODE_PRIVATE);
            editor = shared.edit();
        }
        return session;
    }

    public String getLanguage() {
        return shared.getString(LANGUAGE, "en");
    }

    public void setLanguage(String language) {
        editor.putString(LANGUAGE, language);
        editor.commit();
    }

    public boolean isLogin() {
        return shared.getBoolean(IS_LOGIN, false);
    }

    public void setLogin() {
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();
    }

    public String getAuthToken() {
        return shared.getString(AUTH_TOKEN, "");
    }

    public void setAuthToken(String authToken) {
        editor.putString(AUTH_TOKEN, authToken);
        editor.commit();
    }

    public String getCountryCode() {
        return shared.getString(STD_CODE, "0");
    }

    public void setCountryCode(String contryCode) {
        editor.putString(STD_CODE, contryCode);
        editor.commit();
    }

    public String getLogin() {
        return shared.getString(IS_LOGIN, "0");
    }

    public void setLogin(String isLogin) {
        editor.putString(IS_LOGIN, isLogin);
        editor.commit();
    }

   /* @Bindable("data")
    public UserData getUserData() {

        UserData userData = new UserData();
        userData.setId(shared.getInt(USER_ID, -1));
        userData.setEmail(shared.getString(EMAIL, ""));
        userData.setFirst_name(shared.getString(FIRST_NAME, ""));
        userData.setLast_name(shared.getString(LAST_NAME, ""));
        userData.setName(shared.getString(USERNAME, ""));
        userData.setStd_code(shared.getString(STD_CODE, ""));
        userData.setPhone(shared.getString(PHONE, ""));
        userData.setLanguage(shared.getString(LANGUAGE, ""));
        userData.set_verified(shared.getInt(IS_VERIFIED, -1));
        userData.setFb_id(shared.getString(FB_ID, ""));
        userData.setGoogle_id(shared.getString(GOOGLE_ID, ""));
        userData.setTwitter_id(shared.getString(TWITTER_ID, ""));
        return userData;
    }

    @Bindable("data")
    public void setUserData(UserData userData) {

        editor.putString(USER_ID, String.valueOf(userData.getId()));
        editor.putString(EMAIL, userData.getEmail());
        editor.putString(FIRST_NAME, userData.getFirst_name());
        editor.putString(LAST_NAME, userData.getLast_name());
        editor.putString(USERNAME, userData.getName());
        editor.putString(STD_CODE, userData.getStd_code());
        editor.putString(PHONE, userData.getPhone());
        editor.putString(LANGUAGE, userData.getLanguage());
        editor.putString(IS_VERIFIED, String.valueOf(userData.is_verified()));
        // editor.putString(TOKEN, userData.getToken());
        editor.putString(FB_ID, userData.getFb_id());
        editor.putString(GOOGLE_ID, userData.getGoogle_id());
        editor.putString(TWITTER_ID, userData.getTwitter_id());
        editor.commit();
    }*/


    /*Current Latitude*/
    public String getCurrentLatitude() {
        return shared.getString(LAT, "");
    }

    public void setCurrentLatitude(String lat) {
        editor.putString(LAT, lat);
        editor.commit();
    }


    /*Current Longitude*/
    public String getCurrentLongitude() {
        return shared.getString(LNG, "");
    }

    public void setCurrentLongitude(String lng) {
        editor.putString(LNG, lng);
        editor.commit();
    }


    public void logout() {

        editor.putString(USER_ID, "");
        editor.putString(FIRST_NAME, "");
        editor.putString(LAST_NAME, "");
        editor.putString(USERNAME, "");
        editor.putString(EMAIL, "");
        editor.putString(PHONE, "");
        editor.putString(STD_CODE, "");
        editor.putString(API_TOKEN, "");
        editor.putBoolean(IS_LOGIN, false);
        editor.commit();
    }
}