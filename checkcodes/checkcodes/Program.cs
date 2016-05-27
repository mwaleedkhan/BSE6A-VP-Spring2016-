using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace checkcodes
{
    class Program
    {
        public void get()
        {
            string name="hassan";
            string find = "a";
            for(int i = 0; i < name.Length; i++)
            {
                if (name.Contains(find))
                {
                    Console.WriteLine("found "+name.IndexOf(find));
                }
                else
                    Console.WriteLine("not");
            }


        }
         
        static void Main(string[] args)
        {
            
            Program cs = new Program();
            cs.get();
            Console.ReadLine();
        }
    }
}
