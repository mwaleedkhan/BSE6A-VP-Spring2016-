using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TryingPart
{
    class Program
    {
        static void Main(string[] args)
        {
            DateTime myDater = new DateTime();
            myDater = Convert.ToDateTime(Console.ReadLine());
            Console.WriteLine(myDater.Date);
            Console.ReadLine();

            
        }
    }
}
