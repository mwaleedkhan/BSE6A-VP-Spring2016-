using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Delegates
{
    delegate void sum(int x, int y);
    class Program
    {
        public void summm(int x, int y)
        {
            Console.WriteLine( x + y);
        }
        public void sub(int x, int y)
        {
            Console.WriteLine(x - y);
        }


        static void Main(string[] args)
        {
            Program pg = new Program();
            sum s = new sum(pg.summm);
            s += (pg.sub);
            accept(s);
            int[] arr1 = new int[] { 1, 2, 3, 4 };
            int[] arr2 = new int[] { 1, 2, 5, 4 };
            int[] intersect = new int[5];
            int counter = 0;
            for ( int i =0;i<arr1.Length;i++)
            {
                for(int j=0;j<arr2.Length; j++)
                {
                    if (arr1[i] == arr2[j])
                    {
                        intersect[counter] = arr1[i];
                        counter++;
                        break;

                    }
                    else
                        continue;
                }

            }
            foreach(int value in intersect)
            {
                Console.WriteLine(value);
            }
            Console.ReadLine();
        }
        public static void accept(sum s)
        {
            

            s(12,12);
         
            

        }

       

    }
}
