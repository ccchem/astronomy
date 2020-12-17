package ek.astro.utils;

public class DateMath
{
    static final int DAYS_PER_400Y = 365*400 + 97;
    
    // Number of days during the associated month of a non-leap year
    static final int JAN = 31;
    static final int FEB = 28;
    static final int MAR = 31;
    static final int APR = 30;
    static final int MAY = 31;
    static final int JUN = 30;
    static final int JUL = 31;
    static final int AUG = 31;
    static final int SEP = 30;
    static final int OCT = 31;
    static final int NOV = 30;
    static final int DEC = 31;
    
    // Number of days in a normal year that precede the first of the month
    static final int JAN0 = 0;
    static final int FEB0 = JAN + JAN0;
    static final int MAR0 = FEB + FEB0;
    static final int APR0 = MAR + MAR0;
    static final int MAY0 = APR + APR0;
    static final int JUN0 = MAY + MAY0;
    static final int JUL0 = JUN + JUN0;
    static final int AUG0 = JUL + JUL0;
    static final int SEP0 = AUG + AUG0;
    static final int OCT0 = SEP + SEP0;
    static final int NOV0 = OCT + OCT0;
    static final int DEC0 = NOV + NOV0;
        
    // Number of days that occur before the I'th month of a normal year
    private static int[] DAYS_BEFORE_MONTH = 
    {
        0,
        JAN0, FEB0, MAR0, APR0, MAY0, JUN0,  
        JUL0, AUG0, SEP0, OCT0, NOV0, DEC0            
    };

    
    public static boolean isLeapYear(int year)
    {
        if(year % 4 != 0) return false;
        if(year % 100 != 0) return true;
        if(year % 400 != 0) return false;
        
        return true;
    }

    
    // Number of days from 0001-01-01 to year-01-01
    public static int daysFrom111toYear11(int year)
    {
        return 365*(year-1) + ((year-1)/4) - ((year-1)/100) + ((year-1)/400);
    }

        
    // Day of the year
    public static int doyOfYear(int year, int month, int day) 
    {
        int doy = DAYS_BEFORE_MONTH[month] + day;
        if(isLeapYear(year) && month > 2)
        {
            doy++;
        }
        
        return doy;
    }

    
    // Number of days since 0001-01-01
    public static int daysFrom111(int year, int month, int day) 
    {
        int numDays = daysFrom111toYear11(year) + doyOfYear(year, month, day) - 1;

        if(year < 0) 
        {
            MutInt year400 = new MutInt();
            MutInt rem = new MutInt();
            intDiv(year, 400, year400, rem);
            year = rem.value;

            if(year == 0) 
            {
                year += 400;
                year400.value--;
            }

            int offset = DAYS_PER_400Y * year400.value;
            numDays += offset;
        }
        
        return numDays;
    }
    
    
    // Integer division.
    // Conditions:
    // (1) Denominator is positive
    // (2) NUM = DENOM * Q + REM
    public static void intDiv(int num, int denom, MutInt q, MutInt rem)
    {
        if(denom <= 0)
        {
            throw new IllegalArgumentException("Denominator should be a positive number");
        }

        q.value = num / denom;
        rem.value = num % denom;

        if(rem.value < 0)
        {
            q.value--;
            rem.value += denom;
        }
    }

}
