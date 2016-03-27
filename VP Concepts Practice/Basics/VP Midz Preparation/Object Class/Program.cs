using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Object_Class
{
    class A
    {
        public int id = 1;
        public String name = "Waleed Khan";
        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
        
    }
    class B
    {

    }
    class Program
    {
        static void Main(string[] args)
        {
            A obj = new A();
            A obj1 = new A();
            A obj2 = new A();
            B objb = new B();
            Console.WriteLine(obj1.Equals(obj1));
            Console.WriteLine(obj2.Equals(obj2));
            Console.WriteLine(obj1.Equals(obj2)); //cant figure out why is it returning false for objects of the same class with same data
            Console.WriteLine(obj2.Equals(obj1)); //cant figure out why is it returning false for objects of the same class with same data
            Console.WriteLine(Object.Equals(obj1, obj2));

            Console.WriteLine(obj.Equals(objb));
            Console.WriteLine(object.Equals(objb, obj));


            // Reference Equals:
            Console.WriteLine(ReferenceEquals(obj, obj));
            Console.WriteLine(ReferenceEquals(obj, obj1));
            Console.WriteLine(ReferenceEquals(objb, obj));

            // Get Hashcode
            Console.WriteLine(obj.GetHashCode());
            Console.WriteLine(obj1.GetHashCode());
            

            // object to string 
            Console.WriteLine(obj.ToString());

            //finalize function needs to be overriden
            
            Console.ReadLine();

        }
    }
}
