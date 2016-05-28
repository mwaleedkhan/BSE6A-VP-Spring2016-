import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
public class ReceiverScreen {

	private JFrame frame;
	AudioInputStream audioInputStream; //its an input stream
	static AudioInputStream ais;
	static AudioFormat format;
	static boolean status = true;
	static int port = 50005
	static int sampleRate = 44100;
	DatagramSocket serverSocket;

	static DataLine.Info dataLineInfo;
	static SourceDataLine sourceDataLine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiverScreen window = new ReceiverScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public ReceiverScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnReceive = new JButton("Receive");
		btnReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					establishCommunication();
				} catch (Exception e) {
			// TODO Auto-generated cat	 ch block
					e.printStackTrace();
				}
			}
			
		});
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeCommunication();
			}
		});
		panel.add(btnStop);
		panel.add(btnReceive);
	}
	
	public  void establishCommunication() throws Exception {
			serverSocket= new DatagramSocket(port);
			serverSocket.setReuseAddress(true);
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
	public void closeCommunication(){
		
	serverSocket.close();
	serverSocket.disconnect();
	}

		
	}

