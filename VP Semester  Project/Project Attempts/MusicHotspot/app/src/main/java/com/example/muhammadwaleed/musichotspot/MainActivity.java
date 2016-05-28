package com.example.muhammadwaleed.musichotspot;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onTCPStream(View v)
    {
        final TcpClient client = new TcpClient();

        new Thread(new Runnable(){
            @Override
            public void run() {
                final MediaPlayer mediaPlayer = new MediaPlayer();
                client.GetStream();
                runOnUiThread(new Runnable(){
                    public void run()
                    {
                        int length = client.GetLength();
                        if(length > 0)
                        {
                            byte[] result = client.GetResult();
                            try {
                                // create temp file that will hold byte array
                                File tempMp3 = File.createTempFile("mp3test", "mp3", getCacheDir());
                                tempMp3.deleteOnExit();
                                FileOutputStream fos = new FileOutputStream(tempMp3);
                                fos.write(result);
                                fos.close();


                                mediaPlayer.reset();

                                FileInputStream fis = new FileInputStream(tempMp3);
                                mediaPlayer.setDataSource(fis.getFD());

                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } catch (IOException ex) {
                                String s = ex.toString();
                                ex.printStackTrace();
                            }

                        }
                    }
                });

            }
        }).start();

    }
}
