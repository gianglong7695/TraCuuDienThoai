package app.gianglong.tracuudienthoai.Other;

/**
 * Created by Giang Long on 7/22/2016.
 */

public class MyString {
    public static final String DATABASE_NAME = "TraCuuSmartPhone";
    public static final String DATABASE_TABLE_NAME = "infomation";

    public static final String DATABASE_ROW_ID = "id";
    public static final String DATABASE_ROW_NAME = "name";
    public static final String DATABASE_ROW_COMPANY = "company";
    public static final String DATABASE_ROW_IMAGE = "image";
    public static final String DATABASE_ROW_YEAR = "year";
    public static final String DATABASE_ROW_PARAMETER_SCREEN = "screen";
    public static final String DATABASE_ROW_PARAMETER_SYSTEM = "system";
    public static final String DATABASE_ROW_PARAMETER_CAMERA = "camera";
    public static final String DATABASE_ROW_PARAMETER_CPU = "cpu";
    public static final String DATABASE_ROW_PARAMETER_RAM = "ram";
    public static final String DATABASE_ROW_PARAMETER_INTERNALMEMORY = "internal";
    public static final String DATABASE_ROW_PARAMETER_EXTERNALMEMORY = "external";
    public static final String DATABASE_ROW_PARAMETER_SIM = "sim";
    public static final String DATABASE_ROW_PARAMETER_CONNECT = "connect";
    public static final String DATABASE_ROW_PARAMETER_PIN = "pin";
    public static final String DATABASE_ROW_PARAMETER_TYPE = "type";
    public static final String DATABASE_ROW_PARAMETER_SKILL = "skill";
    public static final String DATABASE_ROW_COLOR = "color";
    public static final String DATABASE_ROW_PRICE = "price";
    public static final String DATABASE_ROW_LINK = "link";

    public static final String SQL_CREATE_TABLE = "Create table if not exists " + MyString.DATABASE_TABLE_NAME + "(" +
            MyString.DATABASE_ROW_ID + " text primary key, " +
            MyString.DATABASE_ROW_NAME + " text, " +
            MyString.DATABASE_ROW_COMPANY + " text, " +
            MyString.DATABASE_ROW_IMAGE + " text, " +
            MyString.DATABASE_ROW_YEAR + " integer, " +
            MyString.DATABASE_ROW_PARAMETER_SCREEN + " text, " +
            MyString.DATABASE_ROW_PARAMETER_SYSTEM + " text, " +
            MyString.DATABASE_ROW_PARAMETER_CAMERA + " text, " +
            MyString.DATABASE_ROW_PARAMETER_CPU + " text, " +
            MyString.DATABASE_ROW_PARAMETER_RAM + " text, " +
            MyString.DATABASE_ROW_PARAMETER_INTERNALMEMORY + " text, " +
            MyString.DATABASE_ROW_PARAMETER_EXTERNALMEMORY + " text, " +
            MyString.DATABASE_ROW_PARAMETER_SIM + " text, " +
            MyString.DATABASE_ROW_PARAMETER_CONNECT + " text, " +
            MyString.DATABASE_ROW_PARAMETER_PIN + " text, " +
            MyString.DATABASE_ROW_PARAMETER_TYPE + " text, " +
            MyString.DATABASE_ROW_PARAMETER_SKILL + " text, " +
            MyString.DATABASE_ROW_COLOR + " text, " +
            MyString.DATABASE_ROW_PRICE + " text, " +
            MyString.DATABASE_ROW_LINK + " text" +
            ")";

    public static final String SQL_HISTORY = "Create table if not exists tbl_history(_stt INTEGER PRIMARY KEY, ten text)";
    // Max 20 row ?
//    public static final String DATABASE_ROW_UPDATE = "update";
//    public static final String DATABASE_ROW_AUTHER = "auther";
}
