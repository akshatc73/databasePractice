package com.example.student.databasepractice;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.databasepractice.data.DataContract;
import com.example.student.databasepractice.data.DataDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long id = insertData();
                Toast.makeText(MainActivity.this, "id of the newly inserted pet is " + id, Toast.LENGTH_SHORT).show();
                showDatabase();
            }
        });


    }

    public void showDatabase() {

        DataDbHelper dataDbHelper = new DataDbHelper(this);

        SQLiteDatabase db = dataDbHelper.getReadableDatabase();

        String[] projection = {
                DataContract.DataEntry._ID,
                DataContract.DataEntry.COLUMN_NAME,
                DataContract.DataEntry.COLUMN_ROLLNO,
                DataContract.DataEntry.COLUMN_CLASS};

        Cursor c = db.query(DataContract.DataEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        try {
            TextView tv = (TextView) findViewById(R.id.textview);
            tv.setText("This is the database of students\n");
            tv.append(DataContract.DataEntry._ID + "\t" + DataContract.DataEntry.COLUMN_NAME + "\t"
                    + DataContract.DataEntry.COLUMN_ROLLNO + "\t"
                    + DataContract.DataEntry.COLUMN_CLASS + "\t\n");

            int idColumnIndex = c.getColumnIndex(DataContract.DataEntry._ID);
            int nameColumnIndex = c.getColumnIndex(DataContract.DataEntry.COLUMN_NAME);
            int rollColumnIndex = c.getColumnIndex(DataContract.DataEntry.COLUMN_ROLLNO);
            int classColumnIndex = c.getColumnIndex(DataContract.DataEntry.COLUMN_CLASS);

            while (c.moveToNext()) {

                int id = c.getInt(idColumnIndex);

                String name = c.getString(nameColumnIndex);

                String roll_no = c.getString(rollColumnIndex);

                String className = c.getString(classColumnIndex);

                tv.append(id + "\t" + name + "\t" + roll_no + "\t" + className + "\n");

            }


        } finally {
            c.close();

        }


    }


    public long insertData() {
        DataDbHelper dataDbHelper = new DataDbHelper(this);

        SQLiteDatabase db = dataDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DataContract.DataEntry.COLUMN_NAME, "Akshat Chaturvedi");

        contentValues.put(DataContract.DataEntry.COLUMN_ROLLNO, 704);

        contentValues.put(DataContract.DataEntry.COLUMN_CLASS, "12th");

        return db.insert(DataContract.DataEntry.TABLE_NAME, null, contentValues);


    }

}
