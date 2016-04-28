using System;
using Android.Net;
using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Android.Net.Rtp;
using Java.Util;
using Java.Net;
using Android.Util;
using System.Threading;

namespace RTP1
{
    [Activity(Label = "RTP1", MainLauncher = true, Icon = "@drawable/icon")]
    public class MainActivity : Activity
    {
        
        EditText ed1,ed2;
        protected override void OnCreate(Bundle bundle)
        {
            
            base.OnCreate(bundle);

            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.Main);
            // Get our button from the layout resource,

            ThreadStart childref = new ThreadStart(call);
            Thread childThread = new Thread(childref);
            childThread.Start();

        }
             
       
        public void call()
        {
            string getvalue = statr();
            ed1 = FindViewById<EditText>(Resource.Id.editText1);
            ed1.Text += getvalue;
            AudioStream audi = new AudioStream(InetAddress.GetByAddress());
            InetAddress ipad = audi.LocalAddress;
            ed2 = FindViewById<EditText>(Resource.Id.editText2);
            ed2.Text += ipad.ToString();

        }
        public byte[] statr()
        {
            
            IEnumeration e = NetworkInterface.NetworkInterfaces;
            while (e.HasMoreElements)
            {
                NetworkInterface n = (NetworkInterface)e.NextElement();
                IEnumeration ee = n.InetAddresses;      
                while (ee.HasMoreElements)
                {
                    InetAddress i = (InetAddress)ee.NextElement();
                    //Inet4Address i = (Inet4Address)ee.NextElement();
                    //Inet6Address i = (Inet6Address)ee.NextElement();             
                    return i.HostAddress;



                }
            }
            return null;
        }
    }
}

