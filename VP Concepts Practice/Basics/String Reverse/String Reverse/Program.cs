using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace String_Reverse
{
    delegate void del(int a);
    class Program
    {
        public void func(int a)
        {
            Console.WriteLine(a * a);
        }
        public void func2(int a)
        {
            Console.WriteLine(a + a);
        }
        static void Main(string[] args)
        {

            Program obj = new Program();
            del a = new del(obj.func);
            a(2);
            a = new del(obj.func2);
            a(3);
            Console.ReadLine();

        }
    }
}
