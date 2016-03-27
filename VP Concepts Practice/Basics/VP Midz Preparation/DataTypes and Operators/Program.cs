using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataTypes_and_Operators
{
    class Program
    {
        static void  Main(string[] args)
        {
            char a= 'c';
            double d = 2.32434343;
            long l = 21332 ;
            decimal deci = 128;
            
            bool b= a=='c' &&  deci == 128? true:false;
            Console.WriteLine(b);
            

            //making a value nullable 
            int? i = null;
            Nullable<int> n = null;

            //Assignment by value
            int x = 10;
            int y = x; //simply copies the value in x to y 

            //Assigmnet by reference
            Object obj = new Object();
            Object obj1 = obj; //both show the same object in the memory


            //Coalesce Operator
            int? f = null;
            int k = f ?? default(int);
            Console.WriteLine(k);
            // is-as operator 
            Console.WriteLine((x is int ? "True" : "False"));
            int s =10;
            Object p = s as Object;
            Console.WriteLine(p);


            //Size of and type of key words
            
            unsafe
            {
                int u = 10;
                Console.WriteLine(sizeof(int).ToString());
            }
            
            Type t = typeof(Program);
            Console.WriteLine(t.ToString());
            Console.ReadLine();

        }
    }
}
