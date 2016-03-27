using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;



namespace Delegates
{
    delegate int deli(int a, int b);

    class A
    {
        public  int sum,prod;
        public  int add(int x,int y)
        {
            sum = x + y;
            return sum; 
        }
        public  int mul(int x, int y)
        {
            prod = x * y;
            return prod;
        }
    }
    class Program
    {

        static void Main(string[] args)
        {
            A obj = new A();
            int a = 1, b = 2;
            deli dell = new deli(obj.add);

            dell += obj.mul;
            Console.ReadKey();
           

        }
       
    }
}
