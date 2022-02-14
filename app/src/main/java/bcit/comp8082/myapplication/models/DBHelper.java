package bcit.comp8082.myapplication.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;



public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shoppinglistapp.db";
    public static final String USER_TABLE_NAME = "users";
    public static final String LIST_TABLE_NAME = "shoppinglist";
    public static final String ITEM_TABLE_NAME = "itemlist";
    public static final String ITEMS_LIST_TABLE_NAME = "itemslist";

    // table variables
    public static class TableVars {
        public static final String USER_ID = "id";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";

        public static final String LIST_ID = "list_id";
        public static final String LIST_USER_ID = "user_id";
        public static final String LIST_NAME = "list_name";
        public static final String LIST_DESC = "list_desc";
        public static final String LIST_DATETIME = "list_datetime";

        public static final String ITEM_ID = "item_id";
        public static final String ITEM_NAME = "item_name";
        public static final String ITEM_DESC = "item_desc";
        public static final String ITEM_PRICE = "item_price";
        public static final String ITEM_IMG = "item_img";

        public static final String ITEMS_LIST_ID = "items_list_id";
        public static final String ITEMS_LIST_LIST_ID = "items_list_list_id";
        public static final String ITEMS_LIST_ITEM_ID = "items_list_item_id";
        public static final String ITEMS_LIST_ITEM_QTY = "items_list_item_qty";
        public static final String ITEMS_LIST_BACKUP_PRICE = "items_list_backup_price";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table " + USER_TABLE_NAME + " (" +
                TableVars.USER_ID + " INTEGER primary key AUTOINCREMENT, " +
                TableVars.USERNAME + " TEXT, " +
                TableVars.PASSWORD + " TEXT)");

        db.execSQL("create Table " + LIST_TABLE_NAME + " (" +
                TableVars.LIST_ID + " INTEGER primary key AUTOINCREMENT, " +
                TableVars.LIST_USER_ID + " INTEGER, " +
                TableVars.LIST_NAME + " TEXT, " +
                TableVars.LIST_DESC + " TEXT, " +
                TableVars.LIST_DATETIME + " INTEGER )");

        db.execSQL("create Table " + ITEM_TABLE_NAME + " (" +
                TableVars.ITEM_ID + " INTEGER primary key AUTOINCREMENT, " +
                TableVars.ITEM_NAME + " TEXT, " +
                TableVars.ITEM_DESC + " TEXT, " +
                TableVars.ITEM_PRICE + " REAL, " +
                TableVars.ITEM_IMG + " TEXT )");

        db.execSQL("create Table " + ITEMS_LIST_TABLE_NAME + " (" +
                TableVars.ITEMS_LIST_ID + "listitems_id INTEGER primary key AUTOINCREMENT, " +
                TableVars.ITEMS_LIST_LIST_ID + " INTEGER, " +
                TableVars.ITEMS_LIST_ITEM_ID + " INTEGER, " +
                TableVars.ITEMS_LIST_ITEM_QTY + " INTEGER, " +
                TableVars.ITEMS_LIST_BACKUP_PRICE + " REAL )");
    }

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists " + LIST_TABLE_NAME);
        db.execSQL("drop Table if exists " + ITEM_TABLE_NAME);
        db.execSQL("drop Table if exists " + ITEMS_LIST_TABLE_NAME);
        onCreate(db);
    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Verify User Login:
     * Returns true if username and password correct, false if incorrect.
     */
    public Boolean verifyUserLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + USER_TABLE_NAME + " where " +
                        TableVars.USERNAME + " = ? and " + TableVars.PASSWORD + " = ?",
                new String[] {username, password});
        return cursor.getCount() > 0;
    }

    /**
     * Get User ID:
     * Returns UserID from username and password, used in getAllLists for user's lists.
     */
    public int getUserID(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + USER_TABLE_NAME + " where " +
                        TableVars.USERNAME + " = ? and " + TableVars.PASSWORD + " = ?",
                new String[] {username, password});
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        } else {
            return -1;
        }
    }

    /**
     * Register new user.
     */
    public Boolean insertUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableVars.USERNAME, username);
        contentValues.put(TableVars.PASSWORD, password);
        long result = db.insert(USER_TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }

    public Boolean deleteUser(int user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USER_TABLE_NAME, TableVars.USER_ID + "=?",
                new String[]{Integer.toString(user_id)}) > 0;
    }

    public Boolean insertList(List list) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // contentValues.put(TableVars.LIST_ID, list.getList_id());
        contentValues.put(TableVars.LIST_NAME, list.getList_name());
        contentValues.put(TableVars.LIST_DESC, list.getList_desc());
        contentValues.put(TableVars.LIST_DATETIME, list.getList_datetime());
        long result = db.insert(LIST_TABLE_NAME, null, contentValues);
        db.close();
        return result != -1; // return true insert success
    }

    public Boolean deleteList(int list_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(LIST_TABLE_NAME, TableVars.LIST_ID + "=?",
                new String[]{Integer.toString(list_id)}) > 0;
    }

    public Boolean insertItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // contentValues.put(TableVars.ITEM_ID, item.getItem_id());
        contentValues.put(TableVars.ITEM_NAME, item.getItem_name());
        contentValues.put(TableVars.ITEM_DESC, item.getItem_desc());
        contentValues.put(TableVars.ITEM_PRICE, item.getItem_price());
        contentValues.put(TableVars.ITEM_IMG, item.getItem_img());
        long result = db.insert(ITEM_TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }

    public Boolean deleteItem(int item_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ITEM_TABLE_NAME, TableVars.ITEM_ID + "=?",
                new String[]{Integer.toString(item_id)}) > 0;
    }


    public Boolean insertItemsList(ItemsList itemsList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // contentValues.put(TableVars.ITEM_ID, item.getItem_id());
        contentValues.put(TableVars.ITEMS_LIST_LIST_ID, itemsList.getItems_list_id());
        contentValues.put(TableVars.ITEMS_LIST_ITEM_ID, itemsList.getItems_list_item_id());
        contentValues.put(TableVars.ITEMS_LIST_ITEM_QTY, itemsList.getItems_list_item_qty());
        contentValues.put(TableVars.ITEMS_LIST_BACKUP_PRICE, itemsList.getItems_list_backup_price());
        long result = db.insert(ITEMS_LIST_TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }

    public Boolean deleteItemsList(int itemsList_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ITEMS_LIST_TABLE_NAME, TableVars.ITEMS_LIST_ID + "=?",
                new String[]{Integer.toString(itemsList_id)}) > 0;
    }


    public ArrayList<List> getAllList(int user_id){
        ArrayList<List> lists = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + LIST_TABLE_NAME + " where " +
                TableVars.LIST_USER_ID + " = ?", new String[] {String.valueOf(user_id)});
        if(cursor.moveToFirst()) {
            do {
                lists.add(
                        new List(Integer.parseInt(cursor.getString(0)),
                                Integer.parseInt(cursor.getString(1)),
                                cursor.getString(2),
                                cursor.getString(3),
                                Integer.parseInt(cursor.getString(4)))
                );
            } while (cursor.moveToNext());
        }
        return lists;
    };


    public ArrayList<Item> getAllItem(){
        ArrayList<Item> items = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + ITEM_TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do {
                items.add(
                        new Item(Integer.parseInt(cursor.getString(0)),
                                cursor.getString(1),
                                cursor.getString(2),
                                Double.parseDouble(cursor.getString(3)),
                                cursor.getString(4))
                );
            } while (cursor.moveToNext());
        }
        return items;
    };


    public ArrayList<ItemsList> getItemsList(int list_id){
        ArrayList<ItemsList> items_lists = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + ITEMS_LIST_TABLE_NAME + " where " +
                TableVars.ITEMS_LIST_LIST_ID + " = ?", new String[] {String.valueOf(list_id)});
        if(cursor.moveToFirst()) {
            do {
                items_lists.add(
                        new ItemsList(Integer.parseInt(cursor.getString(0)),
                                Integer.parseInt(cursor.getString(1)),
                                Integer.parseInt(cursor.getString(2)),
                                Integer.parseInt(cursor.getString(3)),
                                Double.parseDouble(cursor.getString(4)))
                );
            } while (cursor.moveToNext());
        }
        return items_lists;
    }

}
