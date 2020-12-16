package ek.astro.utils;

public class DateMath
{
    /**
     * Compute the remainder of an integer division of NUM by DENOM.
     * Conditions:
     * (1) DENOM is positive
     * (2) NUM = DENOM * Q + REM
     * (3) REM is a non negative integer less than the absolute value of DENOM.
     * 
     * @param num Numerator (in) 
     * @param denom Denominator (in)
     * @param q Quotient (out)
     * @param rem Remainder (out)
     */
    public static void intdiv(int num, int denom, MutInt q, MutInt rem)
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
    private static int[] DPJAN0 = 
    {
        0,
        JAN0, FEB0, MAR0, APR0, MAY0, JUN0,  
        JUL0, AUG0, SEP0, OCT0, NOV0, DEC0            
    };

    
    // Number of additional days that appear before the first of a month 
    // during a leap year
    private static int[] EXTRA = 
    {
        0,
        0, 0, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1
    };
    
    
    public static boolean isDivisible(int year, int n)
    {
        return year % n == 0;
    }
    
    
    public static int numLeapDays(int year)
    {
        int val = 0;
        
        if(isDivisible(year, 4)) val++;
        if(isDivisible(year, 100)) val--;
        if(isDivisible(year, 400)) val++;
        
        return val;
    }

    
    // Number of days from 0001-01-01 to year-01-01
    public static int daysFrom111toYear11(int year)
    {
        return 365*(year-1) + ((year-1)/4) - ((year-1)/100) + ((year-1)/400);
    }

        
    /**
     * Day of the year
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int doyOfYear(int year, int month, int day) 
    {
        return DPJAN0[month] + EXTRA[month] * numLeapDays(year) + day;
    }

    
    // Number of days since 0001-01-01
    public static int daysFrom111(int year, int month, int day) 
    {
        return daysFrom111toYear11(year) + doyOfYear(year, month, day) - 1;
    }
}
