package com.example.muhammadwaleed.receiverudpmodule;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import java.io.ByteArrayInputStream;
        import java.io.IOException;
        import java.net.DatagramPacket;
        import java.net.DatagramSocket;
        import java.net.SocketException;

        import javax.sound.sampled.AudioFormat;
        import javax.sound.sampled.AudioInputStream;
        import javax.sound.sampled.AudioSystem;
        import javax.sound.sampled.DataLine;
        import javax.sound.sampled.FloatControl;
        import javax.sound.sampled.LineUnavailableException;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] receiveData = new byte[4096];
        format = new AudioFormat(sampleRate, 16, 1, true, false);
        dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
        try {
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            sourceDataLine.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        sourceDataLine.start();
        FloatControl volumeControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(1.00f);
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);
        ByteArrayInputStream baiss = new ByteArrayInputStream(
                receivePacket.getData());
        while (status == true) {
            try {
                serverSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
