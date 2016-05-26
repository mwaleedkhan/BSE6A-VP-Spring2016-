package com.example.hp.mp3player2;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by hp on 5/16/2016.
 */
public class RecieveBroadcast {
    public static DatagramSocket socket;
    private AudioTrack speaker;
    private EditText prt;
    private int sampleRate = 44100;
    private int channelConfig = AudioFormat.CHANNEL_IN_MONO;
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private boolean status = true;


    public void startReceiving(final int Portnum) {
        Thread receiveThread = new Thread (new Runnable() {

            @Override
            public void run() {
                try {
                    DatagramSocket socket = new DatagramSocket(Portnum);
                    Log.d("VR", "Socket Created");
                    byte[] buffer = new byte[256];

                    int minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
                    // int minBufSize=4096;
                    speaker = new AudioTrack(AudioManager.STREAM_MUSIC,sampleRate,channelConfig,audioFormat,minBufSize,AudioTrack.MODE_STREAM);
                    speaker.play();
                    Log.d("Speaker","Played");
                    while(status == true) {
                        try {
                            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
                            socket.receive(packet);
                            Log.d("VR", "Packet Received");
                            //reading content from packet
                            buffer=packet.getData();
                            Log.d("VR", "Packet data read into buffer");
                            //sending data to the Audiotrack obj i.e. speaker
                            speaker.write(buffer, 0, minBufSize);
                            Log.d("VR", "Writing buffer content to speaker");
                        } catch(IOException e) {
                            Log.e("VR","IOException");
                        }
                    }
                } catch (SocketException e) {
                    Log.e("VR", "SocketException");
                }
            }

        });
        receiveThread.start();
    }



        public  void StopRecieving()
        {
            status = false;
            speaker.release();}
        }
