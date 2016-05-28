package com.example.muhammadwaleed.musichotspot;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by MuhammadWaleed on 5/5/2016.
 */
public class TcpClient {
    public final static String SERVER_ADDRESS = "127.0.0.1";
    public final static int SERVER_PORT = 65000;
    public String TotalResult;
    public int Length;
    byte[] result = new byte[21000000];


    public TcpClient()
    {



    }

    public int GetLength()
    {
        return Length;

    }

    public byte[] GetResult()
    {

        return result;

    }

    public void GetStream() {
        try {

            final Socket socket = new Socket("192.0.0.5", 65000);
            final InputStream input = new BufferedInputStream(socket.getInputStream());
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nread;


            while ((nread = input.read(result, 0, result.length)) != -1) {

                buffer.write(result, 0, nread);
            }
            buffer.flush();

            //input.read(result);
            Length = result.length;


            input.close();
            socket.close();

        } catch (UnknownHostException e) {
            String exc = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            String exc2 = e.getMessage();
            e.printStackTrace();
        }

    }

}
