import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*
    This program allows the user to create
    a BMI object and calculate a BMI based
    on the user's input.
    Bret Shepard
    10/2/2022
 */

public class BMIProgram
{
    private static String filename = "BMIDATA.txt";
    private static FileInputStream inFile;
    private static ObjectInputStream inStream;
    private static FileOutputStream outFile;
    private static ObjectOutputStream outStream;
    private static List<BMI> BMIs = new ArrayList<>();
    private static Scanner input;
    private static boolean isRunning;
    public static void main(String[] args) throws IOException {
        isRunning = true;
        Init();
        while (isRunning)
        {
            MainMenu();
            RestartMenu();
        }
    }

    private static void Init()
    {
        input = new Scanner(System.in);
        try{
            outFile = new FileOutputStream(filename);
            outStream = new ObjectOutputStream(outFile);
            outStream.writeObject(BMIs);
            outStream.close();
            outFile.close();
        }
        catch(Exception ex){
            System.out.println("Error writing BMIs to file" + ex.getMessage());
        }
    }

    private static void MainMenu() throws IOException {
        System.out.println("Welcome to BMI Calculator\n" +
                "Select an Option: ('1' - Entry in metric, '2' - Entry in standard, '3' - Display Patients, '4' - Exit Application\n");
        int ans = 0;
        while (!(ans <= 4 && ans >= 1)) {
            try {
                ans = Integer.parseInt(input.nextLine());
                if (!(ans <= 4 && ans >= 1)) {
                    System.out.println("Error, please enter a listed option: ('1' - Entry in Metric, '2' - Entry in Standard, '3' - Display Patients, '4' - Exit Application\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error, please enter in number format: ('1' - Entry in Metric, '2' - Entry in Standard, '3' - Display Patients, '4' - Exit Application\n");
            }
        }

            if (ans == 4) {
                isRunning = false;
                return;
            }
            if (ans == 3)
            {
                DisplayAllPatients();
            }

            String iFirstName = GetFirstName();
            String iLastName = GetLastName();
            int iWeight = GetWeight(ans);
            int iHeight = GetHeight(ans);
            BMI bmi = new BMI(iWeight, iHeight, ans, iFirstName, iLastName);
            System.out.println(bmi + "\n");
            SaveBMIDataMenu(bmi);
    }

    private static int GetWeight(int option)
    {
        if (option == 1)
        {
            System.out.println("Enter your weight in kilograms: \n");
        }
        else
        {
            System.out.println("Enter your weight in pounds: \n");
        }
        int ans = -1;
        while (ans < 1)
        {
            try
            {
                ans = Integer.parseInt(input.nextLine());
                if (ans < 1)
                {
                    System.out.println("Enter a number greater than 0: \n");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Error, please enter in number format: \n");
            }
        }

        return ans;
    }
    private static int GetHeight(int option)
    {
        if (option == 1)
        {
            System.out.println("Enter your height in meters: \n");
        }
        else
        {
            System.out.println("Enter your height in centimeters: \n");
        }
        int ans = -1;
        while (ans < 1)
        {
            try
            {
                ans = Integer.parseInt(input.nextLine());
                if (ans < 1)
                {
                    System.out.println("Enter a number greater than 0: \n");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Error, please enter in number format: \n");
            }
        }
        return ans;
    }

    private static String GetFirstName()
    {
        String firstName = "";
        while (firstName.isEmpty())
        {
            System.out.println("Enter your first name: \n");
            try
            {
                firstName = input.nextLine();
            }
            catch (Exception e)
            {
                System.out.println("Error, unexpected value entered.");
            }
        }
        return firstName;
    }

    private static String GetLastName()
    {
        String lastName = "";
        while (lastName.isEmpty())
        {
            System.out.println("Enter your last name: \n");
            try
            {
                lastName = input.nextLine();
            }
            catch (Exception e)
            {
                System.out.println("Error, unexpected value entered.");
            }
        }
        return lastName;
    }

    private static void SaveBMIDataMenu(BMI bmi) throws IOException {
        System.out.println("Would you like to add the patient to the file?: ('Y', 'N')\n");

        String ans = null;
        while (ans == null || ans.isEmpty())
        {
            try
            {
                ans = input.nextLine();
            }
            catch (InputMismatchException ignored)
            {
                System.out.println("No numbers, would you like to add the patient to the file?: ('Y', 'N')\n");
            }
        }

        if (ans.equalsIgnoreCase("y"))
        {
            try
            {
                inFile = new FileInputStream(filename);
                inStream = new ObjectInputStream(inFile);

                // read the objects as an entire array
                BMIs = (List<BMI>) inStream.readObject();

                inStream.close();
                inFile.close();
            }

            catch(Exception ex)
            {
                System.out.println("Error reading Song file");
            }
        }

        MainMenu();
    }

    private static void RestartMenu() throws IOException {
        if (!isRunning)
        {
            return;
        }
        System.out.println("Would you like to calculate another BMI: ('Y', 'N')\n");
        String ans = null;
        while (ans == null || ans.isEmpty())
        {
            try
            {
                ans = input.nextLine();
            }
            catch (InputMismatchException ignored)
            {
                System.out.println("No numbers, Would you like to calculate another BMI: ('Y', 'N')\n");
            }
        }

        if (ans.equalsIgnoreCase("y"))
        {
            MainMenu();
        }
        else
        {
            isRunning = false;
        }
    }

    private static void DisplayAllPatients() throws IOException {
        for (int i = 0; i < BMIs.size(); i++)
        {
            BMI bmi = BMIs.get(i);
            System.out.println("Name--------------------Height----------Weight----------Type----------BMI----------Status\n" +
                               bmi.GetLastName() + ", " + bmi.GetFirstName() +
                    "-----" + bmi.GetHeight() + "-----" + bmi.GetWeight() + "-----" + bmi.oTypeLiteral + "-----" + bmi.calcBMI() + "-----" + bmi.getBMIResult());
        }

        MainMenu();
    }
}
