using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WaleedVPAssignment1
{ 
    class AgeFinder
    {
        public int siblingID;
        public int day, month, year; //will store the value for the date in taken from the user
        public int ageDays, ageMonths, ageYears; // will store the calculated values for the ages
        public DateTime myDate;
        public void getDate()
        {
            String inputDate;
            inputDate = Console.ReadLine();
            String format = "dd-MM-yyyy";

            //DateTime myDate = DateTime.Parse(inputDate.ToString()); //to test the working
           
            DateTime.TryParseExact(inputDate, format, null, System.Globalization.DateTimeStyles.None, out myDate);
            day = myDate.Day;
            month = myDate.Month;
            year = myDate.Year;

            //Console.WriteLine(myDate.ToShortDateString());  // to test the working
            //Console.WriteLine(day + "" + month + "" + year); // to test the working
        }

        public void findAge()
        {
            DateTime currentTime = DateTime.Today;

            //ageDays stores value for the age in days left in completing a whole month
            //it is calculated by taking the system date day and subtracting from the day entered by the user 
            ageDays = currentTime.Day - day;

            //The difference can go towards the negative side or less than zero so this if statement resolves the issue
            if (ageDays<0) 
            {
                currentTime = currentTime.AddMonths(-1); // if first deducts 1 month from the existing number meaning the month is still not finished

                ageDays = ageDays + DateTime.DaysInMonth(currentTime.Year, currentTime.Month); 
                //to cater for the negative number of days so we add the number of day in the existing month and year 
            }

            //ageMonths stores value for the age in month
            //it is calculated by the taking the system date month and subtracting from the month entered by the user 
            ageMonths = currentTime.Month - month;

            if(ageMonths < 0) //The difference can go towards the negative side or less than zero so this if statement resolves the issue
            {
                currentTime = currentTime.AddYears(-1); // if first deducts 1 year from the existing number meaning the year is still not finished 

                ageMonths = ageMonths + 12; //Then adding 12 to the ageMonths is to convert it from < 0 to >0 or making it positive 
            }

            //ageYears stores the value for the age in years
            //that is calculated by the taking the year from the system date and subtract date from the user
            ageYears = currentTime.Year - year;  

            Console.WriteLine(":  Years: " + ageYears + " - Months: " + ageMonths + " - Days: " + ageDays );
        }

        
    }
    
    class Program
    {
        static void Main(string[] args)
        {
            //String inputDate;
            //inputDate = Console.ReadLine();
            //String format = "dd-mm-yyyy";
            //DateTime myDate;
            ////DateTime myDate = DateTime.Parse(inputDate.ToString());
            //DateTime.TryParseExact(inputDate, format, null, System.Globalization.DateTimeStyles.None, out myDate));
            //Console.WriteLine(myDate.ToShortDateString());
            //Console.ReadLine();

            int siblings;
            Console.Write("\n Enter the Number of Siblings: ");
            siblings = Convert.ToInt32(Console.ReadLine());
            AgeFinder[] obj = new AgeFinder[siblings];
            Console.WriteLine("\n");
            Console.WriteLine(" Enter the Date of Birth in this format: DD-MM-YYYY for single digit numbers use 0 at the beginning");
            Console.WriteLine("\n");
            for (int i=0;i< siblings; i++)
            {
                obj[i] = new AgeFinder();
                obj[i].siblingID = i + 1;
                Console.Write(" Enter the DOB of Sibling " + obj[i].siblingID + ": ");
                obj[i].getDate();

            }
            Console.WriteLine("\n");
            for (int x=0; x < siblings; x++)
            {
                Console.Write(" Age of Sibling " +  obj[x].siblingID + " : ");
                obj[x].findAge();
            }

            Console.WriteLine("\n");
            for (int y= 0; y<siblings; y++ )
            {
                
                if (y>siblings-2)
                {
                    break;
                }
                else
                {
                    Console.Write(" Difference between Sibling " + obj[y].siblingID + " and " + obj[y + 1].siblingID + ": ");
                    Console.Write(" Years: " + Math.Abs(obj[y].ageYears - obj[y + 1].ageYears));
                    Console.Write(" Months: " + Math.Abs (obj[y].ageMonths - obj[y + 1].ageMonths));
                    Console.Write(" Days: " + Math.Abs (obj[y].ageDays - obj[y + 1].ageDays) + "\n");
                }
                
            }
            Console.ReadLine();
        }
    }
}
