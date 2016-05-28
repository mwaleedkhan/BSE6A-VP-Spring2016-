package com.example.hp.mp3player2;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by hp on 5/16/2016.
 */
public class SendBroadcast {

    public byte[] buffer;

    public static DatagramSocket socket;
    private int port;
    AudioRecord recorder;
    private int sampleRate = 44100 ;
    private int channelConfig = AudioFormat.CHANNEL_IN_MONO;
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    int minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
    private boolean status = true;

    public void startStreaming(final String ip, final String prt1) {


        Thread streamThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //String ip=ip_addr.getText().toString();
                    //String prt1=prt.getText().toString();
                    port=Integer.parseInt(prt1);
                    recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,sampleRate,channelConfig,audioFormat,minBufSize*10);
                    Log.d("VS", "Recorder initialized");
                    DatagramSocket socket = new DatagramSocket();
                    Log.d("VS", "Socket Created");
                    buffer = new byte[minBufSize];
                    Log.d("VS","Buffer created of size " + minBufSize);
                    DatagramPacket packet;
                    final InetAddress destination = InetAddress.getByName(ip);
                    Log.d("VS", "Address retrieved");

                    recorder.startRecording();
                    while(status == true) {
                        socket.setBroadcast(true);
                        //reading data from MIC into buffer
                        minBufSize = recorder.read(buffer, 0, buffer.length);
                        packet = new DatagramPacket (buffer,minBufSize,destination,port);
                        socket.send(packet);
                        System.out.println("MinBufferSize: " +minBufSize);
                    }
                }
                catch (SocketException e) {
                    e.printStackTrace();
                }
                catch(UnknownHostException e)
                {
                    Log.e("VS", "UnknownHostException");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    Log.e("VS", "IOException");
                }
            }

        });
        streamThread.start();
    }
    public void StopBroadcast()
    {
        status = false;
        recorder.release();
        Log.d("VS", "Recorder released");
    }

    public void StartBroadcast(String Ip,String prt)
    {
        status = true;
        startStreaming(Ip,prt);
    }
}
