using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Structs_Enum_and_All
{
    public struct Car
    {
        public int carno;
    }
    public enum TimeOfDay
    {
        hours=10,
    }
    class Program
    {
        static void Main(string[] args)
        {
            Car obj = new Car();
            obj.carno = 1009;

            
            Console .WriteLine(TimeOfDay.hours.ToString());

            string a = "hey";
            string b = "mate";
            string c = a + b;
            Console.WriteLine(c);
            c += "howdy";

            Console.WriteLine(a.CompareTo(b));
            c.CopyTo(0,a.ToCharArray(),0,2);
           
            Console.WriteLine(String.Format("a-b-c", c));
            Console.WriteLine(a);
            

            c = a.Substring(0);
            Console.WriteLine(c);
            string num = "10000";
            int num2 = int.Parse(num);
            Console.WriteLine(num2);
            
            Console.ReadLine();
        
        }
    }
}
