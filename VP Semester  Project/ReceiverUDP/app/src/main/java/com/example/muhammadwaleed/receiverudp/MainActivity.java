package com.example.muhammadwaleed.receiverudp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class MainActivity extends AppCompatActivity {

    AudioInputStream audioInputStream;
    static AudioInputStream ais;
    static AudioFormat format;
    static boolean status = true;
    static int port = 50005;
    static int sampleRate = 44100;
    static DataLine.Info dataLineInfo;
    static SourceDataLine sourceDataLine;
    Button receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receive=(Button)findViewById(R.id.buttonReceive);
    }

    public void onReceive(View v){
        try {
            Connection();
            Toast.makeText(this,"Receiving Music",Toast.LENGTH_LONG);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Connection() throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] receiveData = new byte[4096];
        format = new AudioFormat(sampleRate, 16, 1, true, false);
        dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        sourceDataLine.open(format);
        sourceDataLine.start();
        FloatControl volumeControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(1.00f);
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);
        ByteArrayInputStream baiss = new ByteArrayInputStream(
                receivePacket.getData());
        while (status == true) {
            serverSocket.receive(receivePacket);
            ais = new AudioInputStream(baiss, format, receivePacket.getLength());
            toSpeaker(receivePacket.getData());
        }
        sourceDataLine.drain();
        sourceDataLine.close();
    }

    public static void toSpeaker(byte soundbytes[]) {
        try {
            sourceDataLine.write(soundbytes, 0, soundbytes.length);
        } catch (Exception e) {
            System.out.println("Not working in speakers...");
            e.printStackTrace();
        }

    }
}
