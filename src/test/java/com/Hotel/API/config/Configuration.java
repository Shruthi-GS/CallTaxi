package com.Hotel.API.config;

public class Configuration {
    public static final String username="user1";
    public static final String password="pass123";
    
    public static int userId1=1002;
    public static int userId2=10;
    public static int userId3=10002;
    public static String validGender="Female";
    public static String invalidGender="123";
    public static String phoneNumber="9898767654";
    
    public static final String AUTH_BASE_URI = "https://webapps.tekstac.com/OAuthRestApi/webapi/auth";
    public static final String USER_SERVICE_BASE_URI = "https://webapps.tekstac.com/HotelAPI/UserService";
    
    public static final String EXCEL_PATH = ".\\src\\test\\java\\com\\Hotel\\API\\data\\Data.xlsx";
    public static final String EXTENT_REPORT_PATH =".\\src\\test\\java\\com\\Hotel\\API\\utils\\Reports\\ExtentReport.html";
}
