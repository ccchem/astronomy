package tt;

import ek.astro.utils.DateMath;
import ek.astro.utils.MutInt;

public class TestDateMath
{
    public static void main(String[] args)
    {
        System.out.println(DateMath.numLeapDays(2001));
        System.out.println(DateMath.numLeapDays(-2000));
    }
    
    
    public static void test1()
    {
        int year = 2000;
        int month = 14;
        int day = 1;
        
        MutInt tmpYear = new MutInt();
        MutInt tmpMonth = new MutInt();
        
        DateMath.intdiv(month-1, 12, tmpYear, tmpMonth);
        System.out.format("%d  %d\n", tmpYear.value, tmpMonth.value);
        
        year += tmpYear.value;
        month = tmpMonth.value + 1;
        int offset = 0;

        System.out.format("%d  %d\n", year, month);
    }

}
