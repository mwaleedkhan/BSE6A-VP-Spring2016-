using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Variables
{
    //class vars
    //{
    //    internal int waleed = 10;
    //    private int khan = 4;
    //    public int x = 0;
    //    protected int k = 5;
    //    protected internal int u = 94;
    //    public static int a = 12;
    //    readonly int g = 6;
    //    const int y = 7;

    ////}
    //class varschild: vars
    //{
         
    //}
    class user
    {
        public static implicit operator float (user var)
        {
            return var;
        }
        public static explicit operator int (user var)
        {
            return (int) var;
        }
        public void findSum2(ref int sum,ref int num1,ref int num2)
        {
            sum = num1 + num2;
        }
        public void findSum(int sum, int num1,int num2)
        {
            sum = num1 + num2;
        }
        public void findDiff(out int diff, out int num1, out int num2)
        {
            num1 = 10;
            num2 = 30;
            diff = 0;
            diff = num1 - num2;
        }
    }
    class Program
    {
            int x = 10;
        static void Main(string[] args)
        {
            //object y = x; // boxing value to reference
            //x = (int)y;// unboxing refrence to value 

            byte b = 255;
            checked
            {
                b++;

            }
            unchecked
            {
                b =(byte) 1000;
            }

            //implicit
            int j = 0;
            float f = j;

            //explicit
            j = (int) f;

            //userdefined casts;
            //implicit
            user vars = new user();
            float flo = vars;

            //explicit
            int integer = (int) vars;

            //value passing
            int x, w, z;
            x = 0;w = 2;z=3;
            int a,d,c;

            vars.findSum(x, w, z);
            Console.WriteLine(x);
            vars.findSum2(ref x, ref w, ref z);
            Console.WriteLine(x);

            vars.findDiff(out a,out d,out c);
            Console.WriteLine(a);
        }
    }
}
