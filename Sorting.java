import java.util.ArrayList;
import javax.swing.*;
import java.io.PrintWriter;

public class Sorting
{
	private static PrintWriter writer;

	public static void sort(Comparable[] a)
	{
		sort(a, 0, a.length);
	}

    // Sort a[lo, hi).
    public static void sort(Comparable[] a, int lo, int hi)
	{
        int N = hi - lo;        // number of elements to sort

        // 0- or 1-element file, so we're done
        if (N <= 1) return; 

        // recursively sort left and right halves
        int mid = lo + N/2; 
        sort(a, lo, mid); 
        sort(a, mid, hi); 

        // merge two sorted subarrays
        Comparable[] aux = new Comparable[N];
        int i = lo, j = mid;
        for (int k = 0; k < N; k++)
		{
            if (i == mid)
				aux[k] = a[j++];
            else if (j == hi)
				aux[k] = a[i++];
            else if (a[j].compareTo(a[i]) < 0)
				aux[k] = a[j++];
            else
				aux[k] = a[i++];
        }

        // copy back
        for (int k = 0; k < N; k++)
		{
            a[lo + k] = aux[k]; 
        }
    }

   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a)
	{
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i-1]) < 0) return false;
        return true;
    }

   /***********************************************************************
    *  Show results
    ***********************************************************************/
    public static void show(Comparable[] a)
	{
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }
	
//	public static String[] separate(String s)
//	{
//		ArrayList<String> list = new ArrayList<String>();
//		int lastIndex = 0;
//
//		for( int i = 0; i < s.length(); i++ )
//		{
//			System.out.println(i + ", " + (i+1) + ", " + lastIndex );
//			if( s.substring(i, 1).equals(";") )
//			{
//				if( lastIndex > 0 )
//					lastIndex += 1;
//
//				list.add( s.substring(lastIndex, i) );
//				lastIndex = i;
//			}
//			else if( s.substring(i, 1).equals("*") )
//			{
//				lastIndex += 1;
//				list.add ( s.substring(lastIndex, i) );
//
//				break;
//			}
//		}
//		
//		String[] ret = new String[list.size()];
//		list.toArray(ret);
//		
//		return ret;
//	}

    public static void main(String[] args)
	{
		//String list = JOptionPane.showInputDialog("Give me the values, separate each item with a ; and end the list with *");
		//String list = "J. Huckleberry;J. Dukes;M. Reigns;G. Chester;M. Collins;T. Gator;M. Miller;R. Paterson;J. Pearce;B. Soro;S. Spears*";
		String[] items = {"HM3 J. Huckleberry", "SA J. Dukes", "SA M. Reigns", "SR G. Chester", "SR M. Collins", "SR T. Gator", "SR M. Miller", "SR R. Paterson", "SR J. Pearce", "SR B. Soro", "SR S. Spears"};
		
		try{
			writer = new PrintWriter("names.txt", "UTF-8");
		} catch (Exception e) {};
		
        Sorting.sort(items);
        for (int i = 0; i < items.length; i++)
		{
            writer.println(items[i]);
        }
        writer.close();
    }
}