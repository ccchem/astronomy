package tt;

import ek.astro.utils.DateMath;
import ek.astro.utils.DateUtils;


public class TestDateMath
{
    public static void main(String[] args)
    {
        //November 24, 4714: dayNum = -1721425
        
        //double d = DateMath.utc2et(-4713, 11, 25); System.out.println(d);
        double d = DateUtils.utc2j2000(2000, 1, 1); System.out.println(d);
    }
    
    
}
