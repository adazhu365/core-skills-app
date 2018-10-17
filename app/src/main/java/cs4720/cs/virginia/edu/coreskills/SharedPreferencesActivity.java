package cs4720.cs.virginia.edu.coreskills;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**

 Assignment Notes: For this activity, the user should be able to
 save and load the username and computing ID from SharedPreferences,
 the key/value store built into Android.

 Reference:
 https://github.com/marksherriff/StorageExample

 */

public class SharedPreferencesActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "CoreSkillsPrefsFile";

    EditText spnameEditText;
    EditText spcompIDEditText;

    TextView spnameTextView;
    TextView spcompIDTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        spnameEditText = (EditText) findViewById(R.id.spnameEditText);
        spcompIDEditText = (EditText) findViewById(R.id.spcompIDEditText);

        spnameTextView = (TextView) findViewById(R.id.spnameTextView);
        spcompIDTextView = (TextView) findViewById(R.id.spcompIDTextView);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String name = settings.getString("name", "none");
        String compid = settings.getString("compid", "none");
        spnameTextView.setText(name);
        spcompIDTextView.setText(compid);
    }

    public void saveToSharedPreferences(View view) {
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String compid = spnameEditText.getText().toString();
        String name = spcompIDEditText.getText().toString();
        values.put("compid", compid);
        values.put("name", name);
        long newRowId;
        newRowId = db.insert(
                "person",
                null,
                values);

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                "compid",
                "name"
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                "compid" + " DESC";

        Cursor cursor = db.query(
                "person",  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        while(cursor.moveToNext()) {
            String currID = cursor.getString(
                    cursor.getColumnIndexOrThrow("compid")
            );
            Log.i("DBData", currID);
        }
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", spnameEditText.getText().toString());
        editor.putString("compid", spcompIDEditText.getText().toString());

        // Commit the edits!
        editor.commit();
        spnameTextView.setText(settings.getString("name", "none"));
        spcompIDTextView.setText(settings.getString("compid", "none"));
        // Add your code here to save


    }

    public void loadFromSharedPreferences(View view) {
        //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        //SharedPreferences.Editor editor = settings.edit();
        //editor.putString("name", spnameEditText.getText().toString());
        //editor.putString("compid", spcompIDEditText.getText().toString());

        // Commit the edits!
        //editor.commit();
        //spnameEditText.setText(spnameTextView.getText());
        //spcompIDEditText.setText(spcompIDTextView.getText());
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        spnameEditText.setText(settings.getString("name", "none"));
        spcompIDEditText.setText(settings.getString("compid", "none"));
        // Add your code here to load

    }
}
