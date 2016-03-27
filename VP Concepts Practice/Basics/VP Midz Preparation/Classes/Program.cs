using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Classes
{
    interface interfaces
    {
        void getName();
    }
    interface interfacechid:interfaces
    {
      
    }
 
    public class A
    {
        private int x;
        private int y;
        public int X
        {
            set
            {
                x = value;
            }
            get
            {
                return x;
            }
        }

        public int this[int i]
        {
            get
            {
                switch (i)
                {
                    case 1:
                        return y = 1;
                        break;
                    case 2:
                        return y = 2;
                        break;
                    
                   
                }

                return 0;
            }
            set
            {
                switch (i)
                {
                    case 1:
                        y = value / 2;
                        break;
                    case 2:
                        y = value * 2;
                        break;


                }

            }
        }
        public virtual void getName()
        {

            Console.WriteLine("Get Name from Parent");
        }
       
    }
      class B: A
    {

        public override void getName()
        {


            Console.WriteLine("Get Name from Child");

        }

    }

    //class C:B, interfacechid
    //{
    //    public void getName()
    //    {

    //    }
    //}
    class Program
    {
        static void Main(string[] args)
        {
            A obj = new A();
            obj.getName();
            B obj2 = new B();
            obj2.getName();
            obj = obj2;
            obj.getName();
            obj2 = (B) obj;
            obj2.getName();
            obj.X = 100;

            obj[1] = 100;
            Console.WriteLine(obj[1]);
            Console.WriteLine(obj.X);

            
            Console.ReadLine();

        }
    }
}
