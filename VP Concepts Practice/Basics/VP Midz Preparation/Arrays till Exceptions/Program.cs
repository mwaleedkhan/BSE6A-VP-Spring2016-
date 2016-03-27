using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Arrays_till_Exceptions
{
    class paramsarray
    {
        public int func(params int[] arr)
        {
            int avg = 0;
            foreach (int val in arr)
            {
                avg = val + avg;
            }
            return avg / arr.Length;
        }


        class Program
        {
            static void Main(string[] args)
            {
                int[] arr1 = new int[] { 1, 2, 3, 4, 5 };
                int[] arr2 = new int[3] { 1, 2, 3 };

                //multi dimensional arrays
                int[,] arr2d = new int[,] { { 1, 2 }, { 2, 3 } }; //2d 
                                                                  //int[,,]arr3d=new int [,,] { new { { 1, 2 }, 3 }, {4,3,5 } };

                //jagged array
                int x = 3;
                int y = 4;
                int[][] jagged = new int[x][];
                jagged[0] = new int[y];
                int[][] jagged2 = new int[][]
                {
                new int[] {1,2,4,5},
                new int[] {1,5,4,6}
                };
                int[][,] jagged3 = new int[x][,];
                jagged3[0] = new int[,] { { 1, 2 }, { 3, 4 } };

                Console.WriteLine(arr2d.Rank); //no of dimensions
                Console.WriteLine(arr2d.IsReadOnly);
                Console.WriteLine(Array.BinarySearch(arr1, 3));
                Array.Sort(arr2);
                Array.Reverse(arr2);
                int s;
                Array.Resize<int>(ref arr2, s = 3);
                paramsarray obj = new paramsarray();
                Console.WriteLine(obj.func(1, 2, 4, 50, 40));


                //after multi case possible break no space 

                //for each loop

                foreach (int m in arr1)
                {
                    Console.WriteLine(m);
                }

                try
                {
                    int i2 = (int) obj;
                    throw new Exception();
                }
                catch(Exception p)
                {
                    Console.WriteLine(p.Message);
                }
                finally
                {
                    Console.WriteLine("Take Care ");
                }

                Console.ReadLine();
            }
        }

        public static explicit operator int (paramsarray v)
        {
            throw new NotImplementedException();
        }
    }
}
