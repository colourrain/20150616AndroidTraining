package com.example.gitandroidtest.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.gitandroidtest.R;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;
import android.util.Log;

public class SearchDatabase {

	private DatabaseOpenHelper mDatabaseOpenHelper = null;

	public static final String COL_WORD = "WORD";
	public static final String COL_DEFINITION = "DEFINITION";

	private static final String DATABASE_NAME = "DICTIONARY";
	private static final String FTS_VIRTUAL_TABLE = "FTS";
	private static final int DATABASE_VERSION = 1;

	private static final String FTS_TABLE_CREATE = "CREATE VIRTUAL TABLE "
			+ FTS_VIRTUAL_TABLE + " USING fts3 (" + COL_WORD + ", "
			+ COL_DEFINITION + ")";

	public SearchDatabase(Context context) {
		mDatabaseOpenHelper = new DatabaseOpenHelper(context);
		mDatabaseOpenHelper.onUpgrade(mDatabaseOpenHelper.getWritableDatabase(), 1, 2);
	}
	public DatabaseOpenHelper getDbOpenHelper(){
		return mDatabaseOpenHelper;
	}

	class DatabaseOpenHelper extends SQLiteOpenHelper {
		private final Context mHelperContext;
		private SQLiteDatabase mDatabase;

		public DatabaseOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			mHelperContext = context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			mDatabase = db;
			mDatabase.execSQL(FTS_TABLE_CREATE);
			loadDictionary();

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("test", "Upgrading database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + FTS_VIRTUAL_TABLE);
			onCreate(db);
		}
		
		private void loadDictionary() {
			new Thread(new Runnable() {
				public void run() {
					try {
						loadWords();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}).start();
		}

		private void loadWords() throws IOException {
			final Resources resources = mHelperContext.getResources();
			InputStream inputStream = resources.openRawResource(R.raw.definitions);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));

			try {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] strings = TextUtils.split(line, "-");
					if (strings.length < 2)
						continue;
					long id = addWord(strings[0].trim(), strings[1].trim());
					if (id < 0) {
						Log.e("test", "unable to add word: " + strings[0].trim());
					}
				}
			} finally {
				reader.close();
			}
		}

		public long addWord(String word, String definition) {
			ContentValues initialValues = new ContentValues();
			initialValues.put(COL_WORD, word);
			initialValues.put(COL_DEFINITION, definition);
			return mDatabase.insert(FTS_VIRTUAL_TABLE, null, initialValues);
		}

	}
	
	public Cursor getWordMatches(String query, String[] columns) {
	    String selection = COL_WORD + " MATCH ?";
	    String[] selectionArgs = new String[] {query+"*"};
	    //columns=new String[]{COL_WORD,COL_DEFINITION};
	    return query(selection, selectionArgs, columns);
	}

	private Cursor query(String selection, String[] selectionArgs, String[] columns) {
	    SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
	    builder.setTables(FTS_VIRTUAL_TABLE);

	    Cursor cursor = builder.query(mDatabaseOpenHelper.getReadableDatabase(),
	            columns, selection, selectionArgs, null, null, null);

	    if (cursor == null) {
	        return null;
	    } else if (!cursor.moveToFirst()) {
	        cursor.close();
	        return null;
	    }
	    return cursor;
	}

	
}
