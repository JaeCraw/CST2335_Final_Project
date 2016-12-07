package com.example.jae.cst2335_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RadioActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    private boolean mTwoPane;
    final Context context = this;
    TextView text1;
    protected SharedPreferences mSharedPrefs;
    protected String button1;
    protected String button2;
    protected String button3;
    protected String button4;
    protected String button5;
    protected String button6;
    protected String channelplaying;
    SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_automobile_activity);

        dbHelper = new DatabaseHelper(RadioActivity.this);
        db = dbHelper.getWritableDatabase();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text1 = (TextView)findViewById(R.id.textView2);

        final Button station1 = (Button) findViewById(R.id.button);
        final Button station2 = (Button) findViewById(R.id.button6);
        final Button station3 = (Button) findViewById(R.id.button5);
        final Button station4 = (Button) findViewById(R.id.button4);
        final Button station5 = (Button) findViewById(R.id.button3);
        final Button station6 = (Button) findViewById(R.id.button2);
        final TextView newT1= (TextView)findViewById(R.id.textView2);


        // Save button text
         mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
         mEditor = mSharedPrefs.edit();
        button4 = station4.getText().toString();
        mEditor.putString("button4", button4).commit();
        // get and set button text
        String getChannelplaying = mSharedPrefs.getString("channelplaying", channelplaying);
        String rbutton1 = mSharedPrefs.getString("button1", button1);
        String rbutton2 = mSharedPrefs.getString("button2", button2);
        String rbutton3 = mSharedPrefs.getString("button3", button3);
        String rbutton4 = mSharedPrefs.getString("button4", button4);
        String rbutton5 = mSharedPrefs.getString("button5", button5);
        String rbutton6 = mSharedPrefs.getString("button6", button6);
        station1.setText(rbutton1);
        station2.setText(rbutton2);
        station3.setText(rbutton3);
        station4.setText(rbutton4);
        station5.setText(rbutton5);
        station6.setText(rbutton6);
        newT1.setText(getChannelplaying);

        final Drawable myDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.play, null);

        station1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newT1.setText(station1.getText().toString());
                station2.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station3.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station4.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station5.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station6.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station1.setCompoundDrawablesWithIntrinsicBounds(myDrawable,null,null,null);
            }
        });

        station2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newT1.setText(station2.getText().toString());
                station3.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station4.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station5.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station6.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station1.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station2.setCompoundDrawablesWithIntrinsicBounds(myDrawable,null,null,null);
            }
        });

        station3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newT1.setText(station3.getText().toString());
                station2.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station4.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station5.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station6.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station1.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station3.setCompoundDrawablesWithIntrinsicBounds(myDrawable,null,null,null);
            }
        });

        station4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newT1.setText(station4.getText().toString());
                station2.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station3.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station5.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station6.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station1.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station4.setCompoundDrawablesWithIntrinsicBounds(myDrawable,null,null,null);
            }
        });

        station5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newT1.setText(station5.getText().toString());
                station2.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station3.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station4.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station6.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station1.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station5.setCompoundDrawablesWithIntrinsicBounds(myDrawable,null,null,null);
            }
        });

        station6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newT1.setText(station6.getText().toString());
                station2.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station3.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station4.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station5.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station1.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                station6.setCompoundDrawablesWithIntrinsicBounds(myDrawable,null,null,null);
            }
        });

        station1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater1.inflate(R.layout.custom_dialog_change, null);
                final EditText editText2 = (EditText) dialogView.findViewById(R.id.editText2);
                String dialog_title = (String)getString(R.string.dialog_title);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.radiov);
                builder.setView(dialogView);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        station1.setText(editText2.getText().toString());
                        dialog.dismiss();
                        channelplaying = newT1.getText().toString();
                        button1 = station1.getText().toString();
                        mEditor.putString("channelplaying", channelplaying).commit();
                        mEditor.putString("button1", button1).commit();
                    }

                });
                builder.setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }

        });


        station2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater1.inflate(R.layout.custom_dialog_change, null);
                final EditText editText2 = (EditText) dialogView.findViewById(R.id.editText2);
                String dialog_title = (String)getString(R.string.dialog_title);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.radiov);
                builder.setView(dialogView);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        station2.setText(editText2.getText().toString());
                        dialog.dismiss();
                        channelplaying = newT1.getText().toString();
                        button2 = station2.getText().toString();
                        mEditor.putString("channelplaying", channelplaying).commit();
                        mEditor.putString("button2", button2).commit();
                    }

                });
                builder.setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        station3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater1.inflate(R.layout.custom_dialog_change, null);
                final EditText editText2 = (EditText) dialogView.findViewById(R.id.editText2);
                String dialog_title = (String)getString(R.string.dialog_title);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.radiov);
                builder.setView(dialogView);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        station3.setText(editText2.getText().toString());
                        dialog.dismiss();
                        channelplaying = newT1.getText().toString();
                        button3 = station3.getText().toString();
                        mEditor.putString("channelplaying", channelplaying).commit();
                        mEditor.putString("button3", button3).commit();
                    }

                });
                builder.setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        station4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater1.inflate(R.layout.custom_dialog_change, null);
                final EditText editText2 = (EditText) dialogView.findViewById(R.id.editText2);
                String dialog_title = (String)getString(R.string.dialog_title);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.radiov);
                builder.setView(dialogView);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        station4.setText(editText2.getText().toString());
                        dialog.dismiss();
                        channelplaying = newT1.getText().toString();
                        button4 = station4.getText().toString();
                        mEditor.putString("channelplaying", channelplaying).commit();
                        mEditor.putString("button4", button4).commit();
                    }

                });
                builder.setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        station5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater1.inflate(R.layout.custom_dialog_change, null);
                final EditText editText2 = (EditText) dialogView.findViewById(R.id.editText2);
                String dialog_title = (String)getString(R.string.dialog_title);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.radiov);
                builder.setView(dialogView);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        station5.setText(editText2.getText().toString());
                        dialog.dismiss();
                        channelplaying = newT1.getText().toString();
                        button5 = station5.getText().toString();
                        mEditor.putString("channelplaying", channelplaying).commit();
                        mEditor.putString("button5", button5).commit();
                    }

                });
                builder.setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });


        station5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater1.inflate(R.layout.custom_dialog_change, null);
                final EditText editText2 = (EditText) dialogView.findViewById(R.id.editText2);
                String dialog_title = (String)getString(R.string.dialog_title);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.radiov);
                builder.setView(dialogView);
                builder.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        station5.setText(editText2.getText().toString());
                        dialog.dismiss();
                        channelplaying = newT1.getText().toString();
                        button6 = station6.getText().toString();
                        mEditor.putString("channelplaying", channelplaying).commit();
                        mEditor.putString("button6", button6).commit();
                    }

                });
                builder.setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });


        station6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater1.inflate(R.layout.custom_dialog_change, null);
                final EditText editText2 = (EditText) dialogView.findViewById(R.id.editText2);
                String dialog_title = (String)getString(R.string.dialog_title);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.radiov);
                builder.setView(dialogView);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        station6.setText(editText2.getText().toString());
                        dialog.dismiss();

                    }

                });
                builder.setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_three:
                Intent intent1 = new Intent(RadioActivity.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.house_settings), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(RadioActivity.this, KithchenRemoteActivity.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.kitchen_settings), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(RadioActivity.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, getString(R.string.living_settings), Toast.LENGTH_SHORT);
                toast3.show();
                break;
            case R.id.action_five:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String dialog_title = (String)getString(R.string.dialog_Title);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.help);
                String helpMessage = (String)getString(R.string.help_Message);
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


}