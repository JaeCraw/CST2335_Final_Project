package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {
    protected static final String URL_STRING = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";
    protected static final String URL_IMAGE = "http://openweathermap.org/img/w/";
    protected static final String ACTIVITY_NAME = "WeatherForecast";
    private ImageView weatherImageView;
    final Context context = this;
    private TextView currentTextView, minTextView, maxTextView;
    private ProgressBar normProgBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.weather_house_activity);
        weatherImageView = (ImageView) findViewById(R.id.weatherImageView);
        currentTextView = (TextView) findViewById(R.id.currentTextView);
        minTextView = (TextView) findViewById(R.id.minTextView);
        maxTextView = (TextView) findViewById(R.id.maxTextView);
        normProgBar = (ProgressBar) findViewById(R.id.normProgBar);
        normProgBar.setVisibility(View.VISIBLE);
        normProgBar.setMax(100);
        new ForecastQuery().execute(null, null, null);
    }
    private class ForecastQuery extends AsyncTask<String, Integer, String> {
        private String currentTemp = null;
        private String minTemp = null;
        private String maxTemp = null;
        private String iconFilename = null;
        private Bitmap weatherImage = null;
        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream = null;
            try {
                URL url = new URL(URL_STRING);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                inputStream = conn.getInputStream();
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(inputStream, null);
                int eventType = parser.getEventType();
                boolean set = false;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (parser.getName().equalsIgnoreCase("current"))
                            set = true;
                        else if (parser.getName().equalsIgnoreCase("temperature") && set) {
                            currentTemp = parser.getAttributeValue(null, "value");
                            publishProgress(25);
                            minTemp = parser.getAttributeValue(null, "min");
                            publishProgress(50);
                            maxTemp = parser.getAttributeValue(null, "max");
                            publishProgress(75);
                        } else if (parser.getName().equalsIgnoreCase("weather") && set) {
                            iconFilename = parser.getAttributeValue(null, "icon") + ".png";
                            File file = getBaseContext().getFileStreamPath(iconFilename);
                            if (!file.exists()) {
                                saveImage(iconFilename);
                            } else {
                                Log.i(ACTIVITY_NAME, "Saved icon, " + iconFilename + " is displayed.");
                                try {
                                    FileInputStream in = new FileInputStream(file);
                                    weatherImage = BitmapFactory.decodeStream(in);
                                    System.out.println("Weather image is set");
                                } catch (FileNotFoundException e) {
                                    Log.i(ACTIVITY_NAME, "Saved icon, " + iconFilename + " is not found.");
                                }
                            }
                            publishProgress(100);
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (parser.getName().equalsIgnoreCase("current"))
                            set = false;
                    }
                    eventType = parser.next();
                }
            } catch (IOException e) {
                Log.i(ACTIVITY_NAME, "IOException: " + e.getMessage());
            } catch (XmlPullParserException e) {
                Log.i(ACTIVITY_NAME, "XmlPullParserException: " + e.getMessage());
            } finally {
                if (inputStream != null)
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                return null;
            }
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            normProgBar.setProgress(values[0]);
            if (values[0] == 100) {
            }
        }
        @Override
        protected void onPostExecute(String s) {
            System.out.println("Postexecute");
            super.onPostExecute(s);
            currentTextView.setText("Today: " + String.format(Locale.getDefault(),"%.1f", Double.parseDouble(currentTemp)) + "\u00b0");
            minTextView.setText("Min: " + String.format(Locale.getDefault(),"%.1f", Double.parseDouble(minTemp)) + "\u00b0");
            maxTextView.setText("Max: " + String.format(Locale.getDefault(),"%.1f", Double.parseDouble(maxTemp)) + "\u00b0");
            weatherImageView.setImageBitmap(weatherImage);
            normProgBar.setVisibility(View.INVISIBLE);
        }
        private void saveImage(String fname) {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(URL_IMAGE + fname);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    weatherImage = BitmapFactory.decodeStream(connection.getInputStream());
                    FileOutputStream outputStream = openFileOutput(fname, Context.MODE_PRIVATE);
                    weatherImage.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    Log.i(ACTIVITY_NAME, "Weather icon, " + fname + " is downloaded and displayed.");
                } else
                    Log.i(ACTIVITY_NAME, "Can't connect to the weather icon for downloading.");
            } catch (Exception e) {
                Log.i(ACTIVITY_NAME, "weather icon download error: " + e.getMessage());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.house_menu, m);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_two:
                Intent intent1 = new Intent(WeatherActivity.this, AutomobileRemoteActivity.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.toastauto), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(WeatherActivity.this, KithchenRemoteActivity.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.toastkitchen), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(WeatherActivity.this, LivingRoomRemoteActivity.class);
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

}
