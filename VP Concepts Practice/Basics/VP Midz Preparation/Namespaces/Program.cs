using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MyNameSpaceUsing;

namespace MyNameSpaceUsing
{
    class HIJ
    {
        public int y;
        public void getY()
        {
            y = Console.Read();
        }
    }
}
namespace MyNameSpaceOut
{
    class XYZ
    {
        public int y;
        public void getY()
        {
            y = Console.Read();
        }
    }
}
namespace Namespaces
{
    namespace MyNameSpace1
    {
        class ABC
        {
            public int x; 
            public void getX()
            {
                x =Console.Read();
            }
            public static void getMe()
            {
                Console.WriteLine("Get Me Choclate Waleed");
            }
        }
    }
    class Program
    {
        static void Main(string[] args)
        {

            //There ways of calling a function from a namespace 

            //first is to to make an path call using dot operator
            MyNameSpace1.ABC obj = new MyNameSpace1.ABC();
            obj.getX();
            MyNameSpaceOut.XYZ obj2 = new MyNameSpaceOut.XYZ();
            obj2.getY();


            //making a call by utilizing the using statement to shorten the path of access
            HIJ obj3 = new HIJ();
            obj3.getY();


            //calling a static function of a class can be called directly without creating an object
            MyNameSpace1.ABC.getMe();

            Console.ReadLine();


        }
    }
}
