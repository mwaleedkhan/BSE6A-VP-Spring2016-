package com.example.hp.mp3player2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Player extends AppCompatActivity {
    MediaPlayer mp;
    ArrayList<File> mysongs;
    int position;
    Uri uri;
    int length;
    Button bu_stop,btn_ffd,btn_rrd,btn_nxt,btn_rev,btnStr_Brd,btnStp_Brd,btnStr_Rec,btnStp_Rec;
    SendBroadcast send;
    RecieveBroadcast Recieve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        btn_ffd=(Button)findViewById(R.id.ffd);
        btn_rrd=(Button)findViewById(R.id.rrd);
        btn_nxt=(Button)findViewById(R.id.nxt_song);
        btn_rev=(Button)findViewById(R.id.pre_song);
        btnStr_Brd=(Button)findViewById(R.id.StartBrd);
        btnStp_Brd=(Button)findViewById(R.id.StopBrd);
        btnStr_Rec=(Button)findViewById(R.id.StartRec);
        btnStp_Rec=(Button)findViewById(R.id.StopRec);

        send=new SendBroadcast();
        Recieve=new RecieveBroadcast();

        Intent i=getIntent();
        final Bundle b=i.getExtras();
        final ArrayList<File> mySongs=(ArrayList)b.getParcelableArrayList("song");
        position=b.getInt("pos",0);

        final Uri[] uri = {Uri.parse(mySongs.get(position).toString())};
        mp=MediaPlayer.create(getApplicationContext(), uri[0]);
        mp.stop();
        mp.release();
        mp.start();
        bu_stop=(Button)findViewById(R.id.stop);
        bu_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying())
                {
                    bu_stop.setText("play");
                    mp.pause();
                length=mp.getCurrentPosition();}
            else    if(!mp.isPlaying()) {
                   bu_stop.setText("stop");
                   mp.start();
                   mp.seekTo(length);
               }
            }
        });
        btn_ffd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.seekTo(mp.getCurrentPosition()+5000);

            }
        });
        btn_rrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.seekTo(mp.getCurrentPosition() - 5000);
            }
        });

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.release();
                position=(position+1)%mySongs.size();
                uri[0] =Uri.parse(mySongs.get(position).toString());
                mp=MediaPlayer.create(getApplicationContext(), uri[0]);
                mp.start();

            }
        });
        btn_rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.release();
                position=(position-1<0)?mySongs.size()-1:position-1;
                uri[0] =Uri.parse(mySongs.get(position).toString());
                mp=MediaPlayer.create(getApplicationContext(), uri[0]);
                mp.start();

            }
        });

    }

    public void StratBroadcast()
    {
        btnStr_Brd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = null;
                String port = null;
                send.StartBroadcast(ip,port);


            }
        });
    }
    public void StopBroadcast()
    {
        btnStp_Brd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               send.StopBroadcast();
            }
        });
    }
    public  void StartRecieve()
    {
        btnStr_Rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Port = 0;
               Recieve.startReceiving(Port);
            }
        });
    }
    public  void  StopRecieve()
    {
        btnStp_Rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Recieve.StopRecieving();

            }
        });
    }
}
