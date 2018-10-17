package cs4720.cs.virginia.edu.coreskills;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;


/**

 Assignment Notes: For this activity, the user should be able to
 save and load the username and computing ID from SQLite.  Several
 helper classes (DatabaseHelper and Section) are included to make
 this a bit easier.

 NOTE: YOU MUST ONLY SHOW THE LAST RECORD FROM THE DATABASE.  i.e.
 the record that was most recently added!

 Reference:
 https://github.com/marksherriff/StorageExample
 https://developer.android.com/training/basics/data-storage/databases.html

 */

public class SQLiteActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText compIDEditText;

    TextView nameTextView;
    TextView compIDTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        compIDEditText = (EditText) findViewById(R.id.compIDEditText);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        compIDTextView = (TextView) findViewById(R.id.compIDTextView);

        String FILENAME = "hello_file2";


        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            ArrayList a = new ArrayList();
            while((line = reader.readLine()) != null){
                Log.e("yes", line);
                a.add(line);

            }

            nameTextView.setText(a.get(0).toString());
            compIDTextView.setText(a.get(1).toString());
            fis.close();
        }catch(Exception e) {
            File file = new File(FILENAME);
            Log.e("StorageExample", e.getMessage());
        }

    }

    public void saveToDatabase(View view) {
        // Add code here to save to the database
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String compid = nameEditText.getText().toString();
        String name = compIDEditText.getText().toString();
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
        String FILENAME = "hello_file2";
        String string = nameEditText.getText().toString();
        String string2 = compIDEditText.getText().toString();
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.write("\n".getBytes());
            fos.write(string2.getBytes());
            fos.close();
        }catch(Exception e) {
            Log.e("StorageExample", e.getMessage());
        }
        nameTextView.setText(string);
        compIDTextView.setText(string2);


    }

    public void loadFromDatabase(View view) {
        String FILENAME = "hello_file2";
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            ArrayList a = new ArrayList();
            while((line = reader.readLine()) != null){
                a.add(line);
            }

            nameEditText.setText(a.get(0).toString());
            compIDEditText.setText(a.get(1).toString());
            fis.close();
        }catch(Exception e) {
            File file = new File(FILENAME);
            Log.e("StorageExample", e.getMessage());
        }



        // Add code here to load from the database

    }
}
