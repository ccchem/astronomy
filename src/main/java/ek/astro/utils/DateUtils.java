package ek.astro.utils;

public class DateUtils
{
    private static final double JULIAN_TO_111 = 1721425.5;
    private static final double J2000 = 2451545;
    
    
    public static double utc2jd(int year, int month, int day)
    {
        return DateMath.daysFrom111(year, month, day) + JULIAN_TO_111;
    }

    
    public static double utc2j2000(int year, int month, int day)
    {
        return utc2jd(year, month, day) - J2000;
    }

}
