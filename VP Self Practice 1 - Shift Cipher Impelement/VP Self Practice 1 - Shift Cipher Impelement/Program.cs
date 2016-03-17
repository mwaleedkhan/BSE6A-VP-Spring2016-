using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Self_Practice_1___Shift_Cipher_Impelement
{
    class ShiftCipher
    {
        string inputText;
        int key,option;
        static string charactersUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        static string characterLower = charactersUpper.ToLower(); 
        
        public void getUserData()
        {
            Console.WriteLine("  Welcome to the Shift Cipher  \n");
            Console.WriteLine(" Enter the text to Encrypt or Decrypt: ");
            inputText = Convert.ToString(Console.ReadLine());
            Console.Write("\n Enter the key: ");
            key = Convert.ToInt32(Console.ReadLine());

            Console.WriteLine("\n Press 1 to Encrypt or Press 2 to Decrypt: ");
            option = Convert.ToInt32(Console.ReadLine());

            if (option == 1)
                Encrypyt();
            else if (option == 2)
                Decrypt();
            else
            {
                Console.WriteLine(" Invalid Option Entered");
                Console.Clear();
                getUserData();
            }
            //Console.WriteLine(inputText); for testing the inputs
        }
        private void Encrypyt()
        {
            var inputToChar = inputText.ToCharArray();
            var charsUpper = charactersUpper.ToCharArray();
            var charsLower = characterLower.ToCharArray();


            //Console.WriteLine(inputToChar);
            //Console.WriteLine(charsUpper);
            //Console.WriteLine(charsLower);

            Console.ReadLine();

        }
        private void Decrypt()
        {

        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            ShiftCipher obj = new ShiftCipher();
            obj.getUserData();
        }
    }
}
