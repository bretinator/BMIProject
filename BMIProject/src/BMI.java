/*
    Handles getting and setting BMI data
    along with validation and calculations.
    Bret Shepard
    12/19/2022
 */

public class BMI implements Comparable<BMI>
{
    int iWeight, iHeight, iOption;
    String iFirstName, iLastName, oTypeLiteral;

    public BMI()
    {
        iWeight = 70;
        iHeight = 170;
        iOption = 1;
    }

    public BMI(int w, int h, int o, String firstName, String lastName)
    {
        SetWeight(w);
        SetHeight(h);
        SetOption(o);
        SetFirstName(firstName);
        SetLastName(lastName);
    }

    public int GetWeight() {return iWeight;}
    public int GetHeight() {return iHeight;}
    public int GetOption() {return iOption;}
    public String GetFirstName() {return iFirstName;}
    public String GetLastName() {return iLastName;}

    public void SetWeight(int w)
    {
        if (w > 0)
        {
            iWeight = w;
        }
        else {iWeight = 70;}
    }
    public void SetHeight(int h)
    {
        if (h > 0)
        {
            iHeight = h;
        }
        else {iHeight = 170;}
    }
    public void SetOption(int o)
    {
        if (o > 0)
        {
            iOption = o;
        }
        else {iOption = 1;}

        switch (o)
        {
            case 1:
                oTypeLiteral = "Metric";
                break;
            case 2:
                oTypeLiteral = "Standard";
        }
    }
    public void SetFirstName(String firstName)
    {
        if (!firstName.isEmpty())
        {
            iFirstName = firstName;
        }
    }
    public void SetLastName(String lastName)
    {
        if (!lastName.isEmpty())
        {
            iLastName = lastName;
        }
    }
    public double calcBMI()
    {
        double cBMI = 0;
        if (iOption == 1) // Metric
        {
            double cHeightMeters = iHeight / (double)100;
            cBMI = iWeight / (cHeightMeters * cHeightMeters); // Weight in KG, height in meters
        }
        else if (iOption == 2) // Standard
        {
            cBMI = 703 * (iWeight / (double)(iHeight * iHeight)); // Weight in pounds, height in inches
        }
        return cBMI;
    }

    public String getBMIResult()
    {
        double cBMI = calcBMI();
        String oBMIResult = "";
        if (cBMI < 18.5)
        {
            oBMIResult = "Underweight";
        }
        else if (cBMI >= 18.5 && cBMI <= 24.9)
        {
            oBMIResult = "Normal Weight";
        }
        else if (cBMI >= 25 && cBMI <= 29.9)
        {
            oBMIResult = "Overweight";
        }
        else if (cBMI >= 30)
        {
            oBMIResult = "Obesity";
        }
        return oBMIResult;
    }

    @Override
    public String toString()
    {
        double cBMI = calcBMI();
        String oBMIResult = "";
        if (cBMI < 18.5)
        {
            oBMIResult = "Underweight";
        }
        else if (cBMI >= 18.5 && cBMI <= 24.9)
        {
            oBMIResult = "Normal Weight";
        }
        else if (cBMI >= 25 && cBMI <= 29.9)
        {
            oBMIResult = "Overweight";
        }
        else if (cBMI >= 30)
        {
            oBMIResult = "Obesity";
        }
        return "BMI is " + calcBMI() +  " and is considered " + oBMIResult;
    }

    @Override public int compareTo(BMI a)
    {
        return this.iLastName.compareTo(a.iLastName);
    }
}
