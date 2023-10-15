package com.example.mediaplayer;

import static android.os.Environment.*;
import static com.example.mediaplayer.R.id.relativelayouts;
import static com.example.mediaplayer.R.id.text_view;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.media.audiofx.DynamicsProcessing;
import android.media.audiofx.Equalizer;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.mediaplayer.R.id.userlist;
import static com.example.mediaplayer.R.layout.*;
import static java.lang.String.valueOf;

import com.chibde.visualizer.BarVisualizer;
import com.chibde.visualizer.CircleBarVisualizer;
import com.chibde.visualizer.CircleBarVisualizerSmooth;
import com.chibde.visualizer.CircleVisualizer;
import com.chibde.visualizer.LineVisualizer;
import com.chibde.visualizer.SquareBarVisualizer;

public class MainActivity extends AppCompatActivity {


    MediaPlayer mediaPlayer = new MediaPlayer();
    Field[] fields = R.raw.class.getFields();
    File[] musikvor=null;
    String musikrawfield="";
    TextView musikstueck;
    ListView myListView;
    String musikpathcomplete ="";
    String selectedItem="";
    SeekBar simpleSeekBar;

    Spinner mSpinner;
    ArrayAdapter aAdapter;
    //Equalizer equalizer;
    float volume=0.5f;

    private double startTime = 0;
    private int dirposition=0;
    private int zaehler=0;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        permissions();

        if(fields.length>=1) {
            musikrawfield = fields[0].getName( );
        }else{
            musikrawfield= "no raw file";
        }
        musikstueck = (TextView) findViewById(R.id.song);
        musikstueck.setText(musikrawfield);

        Context context = this.getApplicationContext();
        bandqualizer(context);
        spinnermusikstyle();
        spinnervisualizierung();
    }

    private void mvisualizer(String selectedItem) {

        RelativeLayout datenfluss = (RelativeLayout) findViewById(relativelayouts);
        switch (selectedItem) {
            case "linevisualzier":
                datenfluss.removeAllViews();
                LineVisualizer lineVisualizer = new LineVisualizer(this);
                //LineVisualizer lineVisualizer =  findViewById(R.id.linevisualzier);
                lineVisualizer.setColor(Color.BLACK);
                lineVisualizer.setVisibility(View.VISIBLE);
                lineVisualizer.setStrokeWidth(1);
                lineVisualizer.setPlayer(mediaPlayer.getAudioSessionId());
                datenfluss.addView(lineVisualizer);
                break;

            case "barvisualizer":
                //BarVisualizer barVisualizer =  findViewById(R.id.barvisualizer);
                datenfluss.removeAllViews();
                BarVisualizer barVisualizer = new BarVisualizer(this);
                barVisualizer.setColor(Color.BLACK);
                barVisualizer.setVisibility(View.VISIBLE);
                barVisualizer.setPlayer(mediaPlayer.getAudioSessionId());
                datenfluss.addView(barVisualizer);
                break;

            case "circebarvisualizer":
                //CircleBarVisualizer circlebarVisualizer =  findViewById(circebarvisualizer);
                datenfluss.removeAllViews();
                CircleBarVisualizer circlebarVisualizer = new CircleBarVisualizer(this);
                circlebarVisualizer.setColor(Color.BLACK);
                circlebarVisualizer.setVisibility(View.VISIBLE);
                circlebarVisualizer.setPlayer(mediaPlayer.getAudioSessionId());
                datenfluss.addView(circlebarVisualizer);
                break;
            case "circevisualizer":
                //CircleVisualizer circevisualizer =  findViewById(R.id.circevisualizer);
                datenfluss.removeAllViews();
                CircleBarVisualizer circevisualizer = new CircleBarVisualizer(this);
                circevisualizer.setColor(Color.BLACK);
                circevisualizer.setVisibility(View.VISIBLE);
                circevisualizer.setPlayer(mediaPlayer.getAudioSessionId());
                datenfluss.addView(circevisualizer);
                break;
            case "squarebarvisualizer":
                //SquareBarVisualizer squarebarvisualizer =  findViewById(R.id.squarebarvisualizer);
                datenfluss.removeAllViews();
                SquareBarVisualizer squarebarvisualizer = new SquareBarVisualizer(this);
                squarebarvisualizer.setColor(Color.BLACK);
                squarebarvisualizer.setVisibility(View.VISIBLE);
                squarebarvisualizer.setPlayer(mediaPlayer.getAudioSessionId());
                datenfluss.addView(squarebarvisualizer);
                break;
            default:
                datenfluss.removeAllViews();
                //LineVisualizer lineVisualizer_default =  findViewById(R.id.linevisualzier);
                LineVisualizer lineVisualizer_default = new LineVisualizer(this);
                lineVisualizer_default.setColor(Color.BLACK);
                lineVisualizer_default.setVisibility(View.VISIBLE);
                lineVisualizer_default.setStrokeWidth(1);
                lineVisualizer_default.setPlayer(mediaPlayer.getAudioSessionId());
                datenfluss.addView(lineVisualizer_default);
                break;


        }
    }

    public void newfiles(View view) {

        File externalStoragePublicDirectory = getExternalStoragePublicDirectory(DIRECTORY_MUSIC);
        //File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File[] daten=externalStoragePublicDirectory.listFiles();
        setmusikstueck(daten);
    }

    public void setmusikstueck(File[] musik)
    {
        musikvor=musik;
        permissions();
        final String[] item = {""};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < musik.length; ++i) {
            String strFileName = musik[i].getName( );
            list.add(strFileName);
            setContentView(secondactivity_main);
            myListView = (ListView) findViewById(R.id.listview);

            int finalI = i;
            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @SuppressLint("WrongViewCast")
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String clickedItem = (String) list.get(position);
                    dirposition=position;
                    if(musik[position].isDirectory())
                    {
                        File[] musik2=musik[position].listFiles();
                        setmusikstueck(musik2);
                    }else{
                        setContentView(activity_main);
                        musikstueck = (TextView) findViewById(R.id.song);
                        musikstueck.setText(clickedItem);
                        musikpathcomplete=musik[position].getAbsolutePath();

                        Context context=view.getContext();
                        spinnermusikstyle();
                        spinnervisualizierung();
                        bandqualizer(context);

                    }
                }
            });
        }

        ArrayAdapter<String>  myArrayAdapter = new ArrayAdapter<String>(this, secondactivity_main, text_view, list);
        myListView.setAdapter(myArrayAdapter);
    }


    public void bandqualizer(Context context)
    {
        Equalizer equalizer = new Equalizer(0,mediaPlayer.getAudioSessionId());
        //equalizer.setEnabled(true);
        for(int i=0; i< (short) equalizer.getNumberOfBands(); i++)
        {
            LinearLayout row = (LinearLayout) findViewById(R.id.linearContainer3);
            //band = new SeekBar(view.getContext());
            SeekBar band = new SeekBar(context.getApplicationContext());
            band.setPadding(30,10,40, 30);
            band.setId(i);
            band.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progress));
            band.setThumb(getResources().getDrawable(R.drawable.seekbar_thum));

            int[] maxmin=equalizer.getBandFreqRange((short)i);
            int durchsn=equalizer.getCenterFreq((short)i);
            int min = maxmin[0];
            int max = maxmin[1];

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                band.setMin(min);
            }
            band.setMax(max);
            band.setProgress(durchsn);

            row.addView(band);

            band.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progressChangedValue = 0;
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    final int id_ = band.getId();
                    equalizer.setEnabled(true);
                    equalizer.setBandLevel((short)id_,(short)progress);
                    equalizer.usePreset((short)id_);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    //Toast.makeText(MainActivity.this, "Seek bar update is :" + progressChangedValue,Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    //Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue, Toast.LENGTH_SHORT).show();

                }
            });

        }

    }

    public void spinnervisualizierung()
    {
        List<String> viusalizers = new ArrayList<String>();
        viusalizers.add("linevisualzier");
        viusalizers.add("barvisualizer");
        viusalizers.add("circebarvisualizer");
        //viusalizers.add("circebarvisualizersmooth");
        viusalizers.add("circevisualizer");
        viusalizers.add("squarebarvisualizer");

        Spinner visualSpinner = (Spinner) findViewById(R.id.spinnervisualizer);
        ArrayAdapter visualAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, viusalizers);
        visualSpinner.setAdapter(visualAdapter);

        visualSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = 31)
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                mvisualizer(selectedItem);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void spinnermusikstyle()
    {

        String[] music_styles;
        Equalizer equalizer = new Equalizer(0,mediaPlayer.getAudioSessionId());
        short numberOfPresets = equalizer.getNumberOfPresets();
        music_styles = new String[numberOfPresets];
        for(int k=0; k <numberOfPresets ; k++) {
            music_styles[k] = equalizer.getPresetName((short) k);
        }

        //mSpinner = (Spinner) findViewById(userlist);
        mSpinner = (Spinner) findViewById(R.id.userlist);
        aAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, music_styles);
        mSpinner.setAdapter(aAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                mSpinner.setSelection(position);

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



    }

    public void permissions() {
        int PERMISSION_REQUEST = 1;
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST);
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            String[] PERMISSIONS = {Manifest.permission.RECORD_AUDIO};
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST);
        }
    }

    //@RequiresApi(api = Build.VERSION_CODES.M)
    public void start(View view) {



        String musiktitel = (String) musikstueck.getText();
        String endtitel = musikpathcomplete;

        if(mediaPlayer.isPlaying() )
        {
            mediaPlayer.stop();
        }

        int resID = getResources().getIdentifier(musiktitel, "raw", getPackageName());
        if(resID != 0) {
            //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.soulfulhouse);
            mediaPlayer = MediaPlayer.create(MainActivity.this, resID);


        }

        if(resID==0) {
            //File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            //String endtitel=externalStoragePublicDirectory.toString()+"/"+musiktitel;
            Uri myUri = Uri.parse(endtitel);
            mediaPlayer = MediaPlayer.create(MainActivity.this, myUri);

        }

        mediaPlayer.start();
        mvisualizer(selectedItem);


        simpleSeekBar=(SeekBar) findViewById(R.id.simpleSeekBar); // initiate the Seekbar
        simpleSeekBar.setMax(mediaPlayer.getDuration());
        simpleSeekBar.getProgress();
        //mvisualizer(selectedItem);

        Handler mSeekbarUpdateHandler = new Handler();
        Runnable mUpdateSeekbar = new Runnable() {
            @Override
            public void run() {
                simpleSeekBar.setProgress(mediaPlayer.getCurrentPosition());
                mSeekbarUpdateHandler.postDelayed(this, 50);

            }
        };

        mUpdateSeekbar.run();

        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    mediaPlayer.seekTo(progress);
                progressChangedValue=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mUpdateSeekbar.run();
                //Toast.makeText(MainActivity.this, "Seek bar update is :" + progressChangedValue,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue, Toast.LENGTH_SHORT).show();

            }
        });

        MediaPlayer.OnCompletionListener onCompletionListener = null;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                nexttitel();
            }
        });



    }

    public void stop(View view) {
        if (mediaPlayer.isPlaying( )) {
            mediaPlayer.stop( );
        }
    }

    public void pause(View view) {
        if (mediaPlayer.isPlaying( )) {
            mediaPlayer.pause();
        }else{
            mediaPlayer.start();
            mvisualizer(selectedItem);

        }
    }


    public void nexttitel()
    {

        if(musikpathcomplete.isEmpty())
        {
            if(zaehler==fields.length)
            {
                zaehler=fields.length;
            }
            else {
                zaehler = zaehler + 1;
            }
            if(fields.length>=1) {
                if (zaehler < fields.length) {
                    zaehler=zaehler+1;
                    musikrawfield = fields[zaehler].getName();
                    musikstueck = (TextView) findViewById(R.id.song);
                    musikstueck.setText(musikrawfield);
                }
                int resID = getResources().getIdentifier(musikrawfield, "raw", getPackageName());
                if(resID != 0) {
                    //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.soulfulhouse);
                    mediaPlayer = MediaPlayer.create(MainActivity.this, resID);
                }


            }

        }else {
            if (musikvor.length-1 > dirposition) {
                dirposition = dirposition + 1;
                String musiktitel = musikvor[dirposition].getAbsolutePath();
                String titel = musikvor[dirposition].getName();
                File daten = new File(musiktitel);
                if(daten.isDirectory())
                {
                    dirposition=dirposition+1;
                    String bla= musikvor[dirposition].getName();
                    musikstueck.setText(bla);

                }else{
                    musikstueck.setText(titel);
                    Uri myUri = Uri.parse(musiktitel);
                    mediaPlayer = MediaPlayer.create(MainActivity.this, myUri);
                    musikpathcomplete=musiktitel;
                }
            }
        }

        MediaPlayer.OnCompletionListener onCompletionListener = null;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                nexttitel();
            }
        });
        mediaPlayer.start();
        mvisualizer(selectedItem);


        Log.e("HIER5", String.valueOf(zaehler));
    }

    public void next(View view)
    {

        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }

        nexttitel();

    }

    public void previous(View view){

        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }

        if(musikpathcomplete.isEmpty())
        {

            if(fields.length-1>=1) {
                if (zaehler > 0) {
                    zaehler=zaehler-1;
                    musikrawfield = fields[zaehler].getName();
                    musikstueck = (TextView) findViewById(R.id.song);
                    musikstueck.setText(musikrawfield);
                }

                int resID = getResources().getIdentifier(musikrawfield, "raw", getPackageName());
                if(resID != 0) {
                    //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.soulfulhouse);
                    mediaPlayer = MediaPlayer.create(MainActivity.this, resID);
                }

            }

        }else {
            if ( dirposition > 0) {
                dirposition = dirposition - 1;
                String musiktitel = musikvor[dirposition].getAbsolutePath();
                String titel = musikvor[dirposition].getName();
                File daten = new File(musiktitel);
                if(daten.isDirectory())
                {
                    dirposition=dirposition-1;
                    String bla= musikvor[dirposition].getName();
                    musikstueck.setText(bla);

                }else{
                    musikstueck.setText(titel);
                    Uri myUri = Uri.parse(musiktitel);
                    mediaPlayer = MediaPlayer.create(MainActivity.this, myUri);
                    musikpathcomplete=musiktitel;

                }
            }
        }
        mediaPlayer.start();
        mvisualizer(selectedItem);

    }


    public void exit(View view) {
        finish();
    }

    public void switch_theme(View view) {

        Switch switchthem = (Switch) findViewById(R.id.switchtheme);

        Boolean switchState = switchthem.isChecked();
        if(switchState==true)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            setTheme(R.style.dark_theme);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            //setTheme(R.style.themes);
        }


    }

    public void leiser(View view) {
        volume=volume-0.1f;
        mediaPlayer.setVolume(volume,volume);
    }

    public void lauter(View view) {
        volume=volume+0.1f;
        mediaPlayer.setVolume(volume,volume);
    }
}
