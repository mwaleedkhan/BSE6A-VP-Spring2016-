package com.example.muhammadwaleed.udpreciever;

import android.media.AudioFormat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Created by MuhammadWaleed on 5/26/2016.
 */
public class Server {

    AudioInputStream audioInputStream;
    static AudioInputStream ais;
    static AudioFormat format;
    static boolean status = true;
    static int port = 50005;
    static int sampleRate = 11025;
    static int bufferSize = 9728;

    static Long lastTime;
    static long totalBytesReceived = 0L;

    private static final int audioStreamBufferSize = bufferSize * 20;
    static byte[] audioStreamBuffer = new byte[audioStreamBufferSize];
    private static int audioStreamBufferIndex = 0;

    public static void main(String args[]) throws Exception {

        Log("Starting the AudioServer...");

        Log("Creating the datagram socket on port " + port + "...");
        DatagramSocket serverSocket = new DatagramSocket(null);
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(port));

        Log("Creating the buffer to hold the received data of size "
                + bufferSize + "...");
        byte[] receiveData = new byte[bufferSize];

        Log("Setting the audio rate to " + sampleRate + "hz...");
        format = new AudioFormat(sampleRate, 16, 1, true, false);

        Log("Ready to receive audio data");
        while (status == true) {

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            serverSocket.receive(receivePacket);
            bufferAudioForPlayback(receivePacket.getData(),
                    receivePacket.getOffset(), receivePacket.getLength());
        }

        serverSocket.close();
    }

    private static void bufferAudioForPlayback(byte[] buffer, int offset,
                                               int length) {

        byte[] actualBytes = new byte[length];

        for (int i = 0; i < length; i++) {
            actualBytes[i] = buffer[i];
        }

        for (byte sample : actualBytes) {

            int percentage = (int) (((double) audioStreamBufferIndex / (double) audioStreamBuffer.length) * 100.0);
            Log("buffer is " + percentage + "% full");

            audioStreamBuffer[audioStreamBufferIndex] = sample;
            audioStreamBufferIndex++;
            Log("Buffer " + audioStreamBufferIndex + " / "
                    + audioStreamBuffer.length + "    " + percentage);

            if (audioStreamBufferIndex == audioStreamBuffer.length - 1) {
                toSpeaker(audioStreamBuffer);
                audioStreamBufferIndex = 0;
                System.exit(0);
            }
        }
    }

    private static void Log(String log) {
        System.out.println(log);
    }

    public static void toSpeaker(byte soundbytes[]) {
        try {

            DataLine.Info dataLineInfo = new DataLine.Info(
                    SourceDataLine.class, format);
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem
                    .getLine(dataLineInfo);

            sourceDataLine.open(format);

            FloatControl volumeControl = (FloatControl) sourceDataLine
                    .getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(100.0f);

            sourceDataLine.start();
            sourceDataLine.open(format);
            sourceDataLine.start();
            sourceDataLine.write(soundbytes, 0, soundbytes.length);
            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (Exception e) {
            System.out.println("Error with audio playback: " + e);
            e.printStackTrace();
        }
    }
}
