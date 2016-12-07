package com.example.jae.cst2335_final_project;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by pelle on 2016-11-30.
 */
public class HouseTempActivity extends AppCompatActivity {

    protected ListView tempListView;
    protected ArrayList<String> tempArray;
    protected ArrayAdapter<String> tempAdapter;
    protected EditText editText1;
    protected EditText editText2;
    protected EditText editText3;
    protected TextView listItemText;
    final Context context = this;
    protected DataBaseHelper dbHelper;
    protected SQLiteDatabase db;
    protected Button button7;
    protected TextView textview1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_temp_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText3 = (EditText) findViewById(R.id.editText);
        textview1 = (TextView)findViewById(R.id.textView);
        Button addButton = (Button) findViewById(R.id.button6);
        Button changetempbutton = (Button) findViewById(R.id.button7);

        dbHelper = new DataBaseHelper(HouseTempActivity.this);
        db = dbHelper.getWritableDatabase();


        tempArray = new ArrayList<String>();
        tempAdapter = new CustomAdaptor(this);
        tempListView = (ListView) findViewById(R.id.listView);

        tempListView.setAdapter(tempAdapter);

        changetempbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText3.getText().toString();
                textview1.setText(input);
            }
        });



        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(HouseTempActivity.this);

                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater1.inflate(R.layout.custom_dialog_add, null);
                editText1 = (EditText) dialogView.findViewById(R.id.editText);
                editText2 = (EditText) dialogView.findViewById(R.id.editText2);

                builder.setView(dialogView);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String input = editText2.getText().toString() + "\n" + editText1.getText().toString() + "\u00B0C" ;

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DataBaseHelper.KEY_MESSAGE, input);
                        db.insert(DataBaseHelper.CHAT_TABLE, null, contentValues);
                        tempArray.add(input);


                        tempAdapter.notifyDataSetChanged();

                    }

                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.house_menu, m);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_two:
                Intent intent1 = new Intent(HouseTempActivity.this, AutomobileRemoteActivity.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.toastauto), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(HouseTempActivity.this, KithchenRemoteActivity.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.toastkitchen), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(HouseTempActivity.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, getString(R.string.toastliving), Toast.LENGTH_SHORT);
                toast3.show();
                break;

            case R.id.action_five:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String dialog_title = (String)getString(R.string.Help);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.questionmark);
                String helpMessage = (String)getString(R.string.help_message);
                builder.setMessage(helpMessage);
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
        }
        return true;
    }


private class CustomAdaptor extends ArrayAdapter<String> {

    public CustomAdaptor(Context newContext) {
        super(newContext, 0);
    }


    public int getCount() {
        return tempArray.size();
    }

    public String getItem(int position) {
        return tempArray.get(position);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.temp_listview, null);
        }

        //Handle TextView and display string from your list
        listItemText = (TextView) view.findViewById(R.id.textView8);
        listItemText.setText(tempArray.get(position));


        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.button8);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                tempArray.remove(position); //or some other task
                tempAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

}
}


