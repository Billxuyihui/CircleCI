import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;






public class AccountDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ServiceFinderDB.db"; 
    public static final String TABLE_USERACCOUNTS = "userAccounts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_PASSWORD = "password"; 
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_DATEOFBIRTH = "dateOfBirth";
    public static final String COLUMN_LEVEL = "permLevel";
	

    /**
     * Constructor for database helper
     * @param context to use for locating paths to the the database
     *   This value may be null.
     */
    public AccountDBHandler(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_USERACCOUNTS_TABLE = "CREATE TABLE " +TABLE_USERACCOUNTS + "("
		+ COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT,"
		+ COLUMN_FIRSTNAME + " TEXT," + COLUMN_LASTNAME + " TEXT," + COLUMN_DATEOFBIRTH + " TEXT,"
		+ COLUMN_LEVEL + " INTEGER" 
		+ ")";

		db.execSQL(CREATE_USERACCOUNTS_TABLE); 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERACCOUNTS); 
		onCreate(db);
	}

	//Create a method to add to a database
	public void addAccount(UserAccount account){

		//Get the data repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();

		//Creating a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(COLUMN_USERNAME, account.getUserName());
		values.put(COLUMN_PASSWORD, account.getPassword());
		values.put(COLUMN_FIRSTNAME, account.getFirstName());
		values.put(COLUMN_LASTNAME, account.getLastName());
		values.put(COLUMN_DATEOFBIRTH, account.getDateOfBirth());
		values.put(COLUMN_LEVEL, account.getPermLevel());

		//Insert
		db.Insert(TABLE_USERACCOUNTS,null,values);

		//Close
		db.close();

	}


	public UserAccount findAccount(String userName){

		//Get the data repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "Select * FROM " + TABLE_USERACCOUNTS +
		" WHERE " + COLUMN_USERNAME + " = \"" + userName + "\"";

		Cursor cursor = db.rawQuery(query, null);

		UserAccount account = new UserAccount();

		if(cursor.moveToFirst()){

			account.setID(Integer.parseInt(cursor.getString(0)));
			account.setUserName(cursor.getString(1));
			account.setPassword(cursor.getString(2));
			account.setFirstName(cursor.getString(3));
			account.setLastName(cursor.getString(4));
			account.setDateOfBirth(cursor.getString(5));
			account.setPermLevel(cursor.getString(6));
			cursor.close();
		}else{
			account = null;
		}
		db.close();
		return account;


	}


	public boolean deleteAccount(String userName){
		boolean result = false;

		//Get the data repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "Select * FROM " + TABLE_USERACCOUNTS +
		" WHERE " + COLUMN_USERNAME + " = \"" + userName + "\"";

		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst()){
			String idStr = cursor.getString(0);
			db.delete(TABLE_USERACCOUNTS, COLUMN_ID + "=" + idStr, null);
			cursor.close();
			result = true;
		}
		db.close();
		return result;
	}





}