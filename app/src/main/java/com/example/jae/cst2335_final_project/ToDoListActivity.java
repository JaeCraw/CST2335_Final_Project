package com.example.jae.cst2335_final_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import com.example.jae.cst2335_final_project.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bmohm90 on 2016-12-01.
 */
public class ToDoListActivity extends AppCompatActivity {



   protected  DatabaseHelper dbHelper;
    protected  SQLiteDatabase db;
    private boolean mTwoPane;
    final Context context = this;
    protected  ListView automobileListview;
    protected  ArrayList<String> automobileArray;
    protected  ArrayAdapter<String> automobileAdapter;
    protected EditText editText1;
    protected EditText editText2;
    protected  Button addButton;
    protected  TextView listItemText;
    protected CustomAdaptor customAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_automobile_activity);

        dbHelper = new DatabaseHelper(ToDoListActivity.this);
        db = dbHelper.getWritableDatabase();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addButton = (Button) findViewById(R.id.button7);

        automobileListview = (ListView) findViewById(R.id.listView2);
        automobileArray = new ArrayList<String>();
        customAdaptor = new CustomAdaptor(this);

        automobileListview.setAdapter(customAdaptor);

        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM CHAT_TABLE", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int messageIndex = cursor.getColumnIndex(DatabaseHelper.KEY_MESSAGE);
            automobileArray.add(cursor.getString(messageIndex));
            cursor.moveToNext();
        }

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(ToDoListActivity.this);

                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater1.inflate(R.layout.custom_dialog_add, null);
                editText1 = (EditText) dialogView.findViewById(R.id.editText);
                editText2 = (EditText) dialogView.findViewById(R.id.editText2);

                builder.setView(dialogView);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String input = editText1.getText().toString() + "\n" + editText2.getText().toString();

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DatabaseHelper.KEY_MESSAGE, input);
                        db.insert(DatabaseHelper.CHAT_TABLE, null, contentValues);
                        automobileArray.add(input);


                        customAdaptor.notifyDataSetChanged();

                    }

                });
                builder.setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }


    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ListDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        ListDetailFragment fragment = new ListDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ListDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.id1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            db.close();
            dbHelper.close();
        }  catch(Exception e) {
        }
    }


    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_three:
                Intent intent1 = new Intent(ToDoListActivity.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.house_settings), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(ToDoListActivity.this, KithchenRemoteActivity.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.kitchen_settings), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(ToDoListActivity.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, getString(R.string.living_settings), Toast.LENGTH_SHORT);
                toast3.show();
                break;
            case R.id.action_five:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String dialog_title = (String) getString(R.string.dialog_Title);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.help);
                String helpMessage = (String) getString(R.string.help_Message);
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
            return automobileArray.size();
        }

        public String getItem(int position) {
            return automobileArray.get(position);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.automobile_listview, null);
            }

            //Handle TextView and display string from your list
            listItemText = (TextView) view.findViewById(R.id.textView8);
            listItemText.setText(automobileArray.get(position));


            //Handle buttons and add onClickListeners
            Button deleteBtn = (Button) view.findViewById(R.id.button8);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //do something
                    automobileArray.remove(position); //or some other task
                    customAdaptor.notifyDataSetChanged();
                }
            });

            return view;
        }

    }
}
