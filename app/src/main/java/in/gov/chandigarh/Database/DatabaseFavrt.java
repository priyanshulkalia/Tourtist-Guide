package in.gov.chandigarh.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Microsoft on 11/10/2017.
 */

public class DatabaseFavrt {
    /** for database */
    static final String DataBaseName = "ChandigarhDB";

    /** for Favorite table */
    public static final String FavoriteTable = "Favorite";
    public static final String ColItemID = "ItemID";
    public static final String ColItemImage = "ItemImage";
    public static final String ColItemName = "ItemName";
    public static final String ColItemRating = "ItemRating";
    public static final String ColItemAddress = "ItemAddress";
    public static final String ColItemIDD = "ItemIDD";
    public static final String ColItemReference = "ItemReference";



    public static final int DATABASE_VERSION = 2;

    //private static final String KEY_ROWID = "_id";

    private static final String FAVORITE_TABLE_CREATE ="Create table " + FavoriteTable +
            //"(_id INTEGER UNIQUE," + [old code]
            "("+ColItemID 	 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ColItemImage 	 + " VARCHAR(50) ,"+
            ColItemName 	 + " VARCHAR(50) ," +
            ColItemRating 	 + " VARCHAR(15) ," +
            ColItemAddress 	 + " VARCHAR(50) ,"+
             ColItemIDD 	 + " VARCHAR(50) ,"+
            ColItemReference 	 + " VARCHAR(100) )";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DatabaseFavrt(Context ctx){
        Log.i("test****", "**test***");
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context){
            super(context, DataBaseName , null, DATABASE_VERSION);
            Log.i("context","context");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(FAVORITE_TABLE_CREATE);
            Log.i("************", "table created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            Log.w("tag", "Upgrading database from version " + oldVersion + " to "+ newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + FavoriteTable);
            onCreate(db);
        }

    };

    public DatabaseFavrt open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        Log.i("open", "message");
        return this;
    }
    public void close(){
        DBHelper.close();
    }

    //public long insert(Integer empid, String empname, Integer empage, String empdept) {
    public long insert(String ItemImage, String ItemName, String ItemRating,String ItemAddress,String ItemIDD,String ItemReference) {
        Log.i("**** suruchitest **** ","*** test ***");
        ContentValues initialValues = new ContentValues();

        //initialValues.put(ColEmpID, empid);
        initialValues.put(ColItemImage, ItemImage);
        initialValues.put(ColItemName, ItemName);
        initialValues.put(ColItemRating, ItemRating);
        initialValues.put(ColItemAddress, ItemAddress);
        initialValues.put(ColItemIDD, ItemIDD);
        initialValues.put(ColItemReference, ItemReference);

        return db.insert(FavoriteTable, null, initialValues);
    }

    public Cursor getItemValues(){
        Cursor mCursor = db.query(FavoriteTable, null, null, null, null, null, null);

        return mCursor;
    }
    public int getItem(String id){
        String name;
      //  Cursor mCursor = db.query(FavoriteTable, new String[]{ColItemName}, ColItemIDD +" = "+ id, null, null, null, null);
        String selectQuery = "SELECT "+ColItemName+" FROM "+FavoriteTable+" WHERE "+ColItemIDD+"='"+id+"' ";
        Log.e("###111111####",""+selectQuery);
        Cursor c = db.rawQuery(selectQuery,null);
        Log.e("###222222####",""+c);
       int i = c.getCount();
        c.close();
        return i;
    }

    public void deleteItem(String id){
       /* Toast.makeText(context, "deleted", Toast.LENGTH_LONG).show();
        return db.delete(FavoriteTable, ColItemIDD +" = "+id, null) > 0;*/
        db.execSQL("DELETE FROM " + FavoriteTable+ " WHERE "+ColItemIDD+"='"+id+"'");
    }
  /*  public boolean updateEmplist(String empname, Integer empage, String empdept, Integer rowid){

        ContentValues initialValues = new ContentValues();
        Log.i("#####  "+rowid,""+empname+" "+empage+" "+empdept);

        //initialValues.put(ColEmpID, rowid);
        initialValues.put(ColEmpName,empname);
        initialValues.put(ColEmpAge,empage);
        initialValues.put(ColDept,empdept);

        try{
            int b = db.update(EmployeTable, initialValues,  ColEmpID+ " = " + rowid, null);
            Log.i("update", "up "+rowid+"  ddd   "+b);
            return true;
        }catch (Exception e){
            Log.d("asdfasdfsadfasdf", "_--___--__--_=-_");
            return false;
        }
    }
*/
}
