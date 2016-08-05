package app.gianglong.tracuudienthoai.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import app.gianglong.tracuudienthoai.Activity.SearchProduct_Activity;
import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.Objects.ParameterObject;
import app.gianglong.tracuudienthoai.Objects.ProductObject;
import app.gianglong.tracuudienthoai.Other.MyString;

/**
 * Created by Giang Long on 7/22/2016.
 */

public class Database extends SQLiteOpenHelper {
    SQLiteDatabase database;
    public Database(Context context) {
        super(context, MyString.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.database = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c  = db.rawQuery(sql, null);
        return c;
    }

    public void open(){
        database = getWritableDatabase();
    }

    public void insertRow(String id, String name, String company, String image, int year, ParameterObject obj, String color, String price, String link){
        String sql = "Insert into " + MyString.DATABASE_TABLE_NAME + " values ( ' "
                + id + " ',' " + name + " ',' " + company + " ',' " + image + " ', " + year + " ,' " + obj.getScreen() + " ',' " + obj.getSystem() + " ',' "
                + obj.getCamera() + " ',' " + obj.getCpu() + " ',' " + obj.getRam() + " ',' " + obj.getInternalMemory() + " ',' "
                + obj.getExternalMemory() + " ',' " + obj.getSim() + " ',' " + obj.getConnect() + " ',' " + obj.getPin() + " ',' "
                + obj.getType() + " ',' " + obj.getSkill() + " ',' " + color + " ',' " + price + " ',' " + link + " ')";

        queryData(sql);
    }

    public void addHistory(String history){
        String sql = "Insert into tbl_history values(null, '"+ history +"')";
        queryData(sql);

    }

    public void queryData(String query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public int getSize(){
        int size = 0;
        Cursor c = getData("Select * from " + MyString.DATABASE_TABLE_NAME);
        while (c.moveToNext()){
            size++;
        }

        return size;
    }

    public ArrayList getHistory(){
        ArrayList list = new ArrayList();
        Cursor c = getData("Select * from tbl_history");
        while (c.moveToNext()){
            boolean isExist = false;
            for (int i = 0; i < list.size(); i++){
                if(list.get(i).toString().equals(c.getString(1))){
                    isExist = true;
                }
            }
            if(!isExist){
                list.add(0,c.getString(1));
            }
        }
        return list;
    }

    public void  addHistorySearching(String product_name){
        boolean isCheck = false;
        for (int i = 0 ; i < SearchProduct_Activity.listHistory.size(); i++){
            if(SearchProduct_Activity.listHistory.get(i).equals(product_name)){
                SearchProduct_Activity.listHistory.remove(i);
                SearchProduct_Activity.listHistory.add(0, product_name);

                isCheck = true;
            }

        }

        if(!isCheck){
            SearchProduct_Activity.listHistory.add(0, product_name);
        }
    }

    public ArrayList getProductName(){
        ArrayList listName = new ArrayList();
        Cursor c = getData("Select * from " + MyString.DATABASE_TABLE_NAME);
        while (c.moveToNext()){
            listName.add(c.getString(1));
        }
        return listName;
    }

    public ProductObject getProduct(){
        ProductObject productObject = null;
        ParameterObject parameter = null;

        Cursor c = getData("Select * from " + MyString.DATABASE_TABLE_NAME);
        while (c.moveToNext()){
            parameter = new ParameterObject(c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9),c.getString(10),c.getString(11), c.getString(12), c.getString(13),c.getString(14), c.getString(15), c.getString(16));
            productObject = new ProductObject(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), parameter, c.getString(17), c.getString(18), c.getString(19));
            SearchProduct_Activity.arrlProduct.add(productObject);
            MainActivity.arrl.add(productObject);
        }

        return productObject;
    }

    public ArrayList<ProductObject> getProduct2(){
        ArrayList<ProductObject> arrl = new ArrayList<>();
        ProductObject productObject = null;
        ParameterObject parameter = null;

        Cursor c = getData("Select * from " + MyString.DATABASE_TABLE_NAME);
        while (c.moveToNext()){
            parameter = new ParameterObject(c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9),c.getString(10),c.getString(11), c.getString(12), c.getString(13),c.getString(14), c.getString(15), c.getString(16));
            productObject = new ProductObject(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), parameter, c.getString(17), c.getString(18), c.getString(19));

            arrl.add(productObject);
        }

        return arrl;
    }


    public  void deleteDatabase(String table_name, String clause, String[] value){
        open();
        database.delete(table_name, clause, value);
    }

    public  void deleteHistory(){
        open();
        database.delete("tbl_history", null, null);
    }



//    ParameterObject object = new ParameterObject(c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), c.getString(13), c.getString(14), c.getString(15), c.getString(16));
//    MainActivity.arrl.add(new ProductObject(c.getString(0), c.getString(1), c.getString(2), c.getString(3), Integer.parseInt(c.getString(4)), object, c.getString(17), c.getString(18), c.getString(19)));

}
