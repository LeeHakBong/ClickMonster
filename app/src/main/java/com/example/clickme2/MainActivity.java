package com.example.clickme2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btClick,btAgain,btStorage;
    TextView tvClick,tvTime;
    ImageView iv;
    int click;
    int savedata;
    int value;
    BackgroundTask task;
    MediaPlayer mediaPlayer;
    int[] imgs = {R.drawable.fire, R.drawable.pica,R.drawable.seed,R.drawable.turtle,
            R.drawable.mew,R.drawable.mews,R.drawable.bug,R.drawable.fish,R.drawable.sleep};
    int strg[] = {R.drawable.mark,R.drawable.mark,R.drawable.mark,R.drawable.mark,
            R.drawable.mark,R.drawable.mark,R.drawable.mark,R.drawable.mark,R.drawable.mark};
    int randomNum;
    Dialog dlg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        btClick=(Button) findViewById(R.id.btClick);
        btAgain=(Button) findViewById(R.id.btAgain);
        btAgain.setVisibility(View.INVISIBLE);
        btStorage=(Button)findViewById(R.id.btStorage);
        tvClick=(TextView)findViewById(R.id.tvClick);
        tvTime=(TextView)findViewById(R.id.tvTime);
        iv=(ImageView)findViewById(R.id.iv);


        checker();
        tvClick.setText(String.valueOf(savedata));
        task = new BackgroundTask();
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        click=click+savedata;
        task.execute();

        dlg = new Dialog(MainActivity.this);       // Dialog 초기화
        dlg.setContentView(R.layout.custom);
        dlg.setTitle("MY STORAGE");


        btStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dlgShow();

            }
        });

        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                viewClick();
                if(Integer.parseInt(tvClick.getText().toString())==30)
                {
                    btClick.setVisibility(View.INVISIBLE);
                    randomNum=(int)(Math.random()*imgs.length);
                    iv.setImageResource(imgs[randomNum]);
                    iv.setPadding(20,20,20,20);
                    btAgain.setVisibility(View.VISIBLE);

                    tvClick.setText("0");

                    switch (randomNum)
                    {
                        case 0:
                            strg[0]=R.drawable.fire;
                            break;
                        case 1:
                            strg[1]=R.drawable.pica;
                            break;
                        case 2:
                            strg[2]=R.drawable.seed;
                            break;
                        case 3:
                            strg[3]=R.drawable.turtle;
                            break;
                        case 4:
                            strg[4]=R.drawable.mew;
                            break;
                        case 5:
                            strg[5]=R.drawable.mews;
                            break;
                        case 6:
                            strg[6]=R.drawable.bug;
                            break;
                        case 7:
                            strg[7]=R.drawable.fish;
                            break;
                        case 8:
                            strg[8]=R.drawable.sleep;
                            break;
                    }
                }

            }
        });

        btAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click=0;
                tvClick.setText("0");
                iv.setImageResource(R.drawable.pngegg);
                btAgain.setVisibility(View.INVISIBLE);
                btClick.setVisibility(View.VISIBLE);
                tvClick.setVisibility(View.VISIBLE);


            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("EXIT? (๑• . •๑)?? ");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { // 예 선택시 저장 후 종료
                task.cancel(true);
                mediaPlayer.stop();
                saved();
                finish();
            }
        });

        builder.setNegativeButton("NOPE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setTitle("Game Exit");
        builder.show();
    }

    public void saved() {
        SharedPreferences sf = getSharedPreferences("Save", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt("click", click);
        editor.putInt("storage",strg[0]);
        editor.putInt("storage1",strg[1]);
        editor.putInt("storage2",strg[2]);
        editor.putInt("storage3",strg[3]);
        editor.putInt("storage4",strg[4]);
        editor.putInt("storage5",strg[5]);
        editor.putInt("storage6",strg[6]);
        editor.putInt("storage7",strg[7]);
        editor.putInt("storage8",strg[8]);
        editor.commit();

    }

    public void checker() {
        SharedPreferences sf = getSharedPreferences("Save", Activity.MODE_PRIVATE);
        savedata = sf.getInt("click",0);
        strg[0]=sf.getInt("storage",strg[0]);
        strg[1]=sf.getInt("storage1",strg[1]);
        strg[2]=sf.getInt("storage2",strg[2]);
        strg[3]=sf.getInt("storage3",strg[3]);
        strg[4]=sf.getInt("storage4",strg[4]);
        strg[5]=sf.getInt("storage5",strg[5]);
        strg[6]=sf.getInt("storage6",strg[6]);
        strg[7]=sf.getInt("storage7",strg[7]);
        strg[8]=sf.getInt("storage8",strg[8]);



    }
    public void dlgShow(){

        ImageView imageView = dlg.findViewById(R.id.imageView);
        ImageView imageView1 = dlg.findViewById(R.id.imageView);
        ImageView imageView2 = dlg.findViewById(R.id.imageView2);
        ImageView imageView3 = dlg.findViewById(R.id.imageView3);
        ImageView imageView4 = dlg.findViewById(R.id.imageView4);
        ImageView imageView5 = dlg.findViewById(R.id.imageView5);
        ImageView imageView6 = dlg.findViewById(R.id.imageView6);
        ImageView imageView7 = dlg.findViewById(R.id.imageView7);

        imageView.setImageResource(strg[0]);
        imageView1.setImageResource(strg[1]);
        imageView2.setImageResource(strg[2]);
        imageView3.setImageResource(strg[3]);
        imageView4.setImageResource(strg[4]);
        imageView5.setImageResource(strg[5]);
        imageView6.setImageResource(strg[6]);
        imageView7.setImageResource(strg[7]);
        ImageView imageView8 = dlg.findViewById(R.id.imageView8);
        imageView8.setImageResource(strg[8]);

        dlg.show();

    }
    public void viewClick(){

        tvClick.setText(String.valueOf(click));
    }
    class BackgroundTask extends AsyncTask<Integer , Integer , Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {
            while (isCancelled() == false) {
                click++;
                value++;
                if (value >= 100) {
                    break;
                } else {

                    publishProgress(click);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {}
            }
            return value;
        }

        protected void onProgressUpdate(Integer ... values) {

            tvClick.setText(String.valueOf(click));

            if(Integer.parseInt(tvClick.getText().toString())==30)
            {
                randomNum=(int)(Math.random()*imgs.length);
                iv.setImageResource(imgs[randomNum]);
                iv.setPadding(20,20,20,20);
                btAgain.setVisibility(View.VISIBLE);
                btClick.setVisibility(View.INVISIBLE);
                tvClick.setVisibility(View.INVISIBLE);
                if(Integer.parseInt(tvClick.getText().toString())>30)
                {
                    click=0;
                }

                switch (randomNum)
                {
                    case 0:
                        strg[0]=R.drawable.fire;
                        break;
                    case 1:
                        strg[1]=R.drawable.pica;
                        break;
                    case 2:
                        strg[2]=R.drawable.seed;
                        break;
                    case 3:
                        strg[3]=R.drawable.turtle;
                        break;
                    case 4:
                        strg[4]=R.drawable.mew;
                        break;
                    case 5:
                        strg[5]=R.drawable.mews;
                        break;
                    case 6:
                        strg[6]=R.drawable.bug;
                        break;
                    case 7:
                        strg[7]=R.drawable.fish;
                        break;
                    case 8:
                        strg[8]=R.drawable.sleep;
                        break;
                }
            }

        }
    }


}
