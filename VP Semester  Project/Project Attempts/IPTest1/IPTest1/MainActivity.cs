using System;
using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Android.Net.Wifi;
using Android.Net;
using Android.Net.Rtp;
using System.Net;
using System.Net.NetworkInformation;
using System.Net.Sockets;
using System.Threading;
using Java.Net;
using Android.Media;
using Android.Util;

namespace IPTest1
{
    [Activity(Label = "IPTest1", MainLauncher = true, Icon = "@drawable/icon")]
    public class MainActivity : Activity
    {
        EditText info,info2;
        WifiManager wifii;
        DhcpInfo d;
        string ip;
       
        string mac;
        AudioStream audi;
       
        InetAddress iin;
        InetAddress ini;
        AudioGroup audiogroup;
        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);

            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.Main);

            // Get our button from the layout resource,
            // and attach an event to it

            info = FindViewById<EditText>(Resource.Id.editText1);
            wifii = (WifiManager)GetSystemService(Context.WifiService);
            d = wifii.DhcpInfo;
            ip = Convert.ToString(IntToIp(d.IpAddress));
            iin = InetAddress.GetByName(ip);

           
            
            string[] array = ip.Split('.');
            for (int i = 0; i <=255; i++)
            {
                string Broadcast = array[0] + "." + array[1] + "." + array[2] + "." + i;
                ini = InetAddress.GetByName(Broadcast);
                Thread childThread = new Thread(() => send(iin, ini));
                childThread.Start();
            }
            
            
            
           
            WifiInfo wa = wifii.ConnectionInfo;           
            info2 = FindViewById<EditText>(Resource.Id.editText2);
            //mac = wa.MacAddress + "\n BSSID " + wa.BSSID + "\n Link Speed " + wa.LinkSpeed + "\n Network Id " + wa.NetworkId + "\n" + IntToIp(wa.IpAddress);
            //info2.Text = mac;
            //info.Text =" BroadCast Address"+ Broadcast.ToString();
            info.Text = ini.ToString();
           // ThreadStart childref = new ThreadStart(send(iin,ini));
            
        }
        public string IntToIp(int ip)
        {
            return
                (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                ((ip >> 24) & 0xFF);                
                   
                 
        }
        public void send(InetAddress iin,InetAddress ini)
        {
            try
            {
                AudioManager audio = (AudioManager)GetSystemService(Context.AudioService);
                audio.SpeakerphoneOn = true;
                audio.Mode = Mode.InCommunication;
                audiogroup = new AudioGroup();
                audiogroup.Mode = AudioGroupMode.Normal;
                audi = new AudioStream(iin);   //To Be Continued
                audi.Mode = RtpStreamMode.Normal;
                audi.Associate(ini, audi.LocalPort);
                audi.Codec = AudioCodec.Pcmu;
                audi.Join(audiogroup);
            }
            catch (Java.Net.SocketException s) { s.PrintStackTrace(); }
            catch(UnknownHostException uk) { uk.PrintStackTrace(); }
            catch(Exception e) { Log.Error(" Error at :", e.StackTrace); }
        }
       
        
    }
}

