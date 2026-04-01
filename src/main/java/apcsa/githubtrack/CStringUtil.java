package apcsa.githubtrack;

// Implement your CStringUtil class here
public class CStringUtil {

    // Returns true if str is a palindrome
    public static boolean isPalindrome(CString str)
    {
        String temp = str.toString();
        temp = temp.toLowerCase();
        temp = temp.replace(" ", "");
        String palindrome = "";
        // Reverses the string
        for (int i = temp.length() - 1; i >= 0; i--)
        {
            palindrome += temp.substring(i, i+1);
        }
        return temp.equals(palindrome);
    }

    // Turns str into its ascii value and offsets it by offset
    public static int[] toNumerical(CString str, int offset)
    {
        String temp = str.toString();
        String[] charList = temp.split("");
        int[] numList = new int[temp.length()];
        // Converts to ascii and offsets each value
        for (int i = 0; i < charList.length; i++)
        {
            numList[i] = charList[i].charAt(0) + offset;
        }
        return numList;
    }

    // Returns the length of the longest mirror section in cStr by passing it to maxMirror(int)
    public static int maxMirror(CString cStr)
    {
        if (cStr.toString().length() == 0)
        {
            return 0;
        }
        else
        {
            return maxMirror(toNumerical(cStr, 0));
        }
    }

    // Returns the length of the longest mirror section in intList
    public static int maxMirror(int[] intList)
    {
        if (intList.length == 0)
        {
            return 0;
        }
        int max = 0;
        // Traverses through the whole list
        // i represents the start value going forward
        for (int i = 0; i < intList.length; i++)
        {
            // j represents the start value going backwards
            for (int j = intList.length - 1; j >= 0; j--)
            {
                int count = 0;
                int a = i;
                int b = j;
                // Continues to loop as long as the mirror section can be expanded
                while (a < intList.length && b >= 0 && intList[a] == intList[b])
                {
                    count++;
                    a++;
                    b--;
                }
                // Changes max if count is greater than max
                if (count > max)
                {
                    max = count;
                }
            }
        }
        return max;
    }

    // Puts all the 7s after 6s in a list
    public static int[] memeifyArray(int[] nums)
    {
        int[] memed = nums;
        int j = 0;
        // Traverses through the whole list
        for (int i = 0; i < memed.length; i++)
        {
            // Checks if the current value is 6
            if (memed[i] == 6)
            {
                // If the next value is already 7, skip
                if (i + 1 < memed.length && memed[i+1] == 7)
                {
                    continue;
                }
                // Looks for 7
                while (j < memed.length)
                {
                    // Checks if the value is 7
                    if (memed[j] == 7)
                    {
                        // Checks if the value right before is not 6
                        // If so, leave the loop
                        if (j == 0 || memed[j-1] != 6)
                        {
                            break;
                        }
                    }
                    j++;
                }
                // Swaps the 7 and other value
                int temp = memed[i+1];
                memed[i+1] = memed[j];
                memed[j] = temp;
            }
        }
        return memed;
    }

    // Returns true if the values in inner are in outer
    public static boolean nestedSequence(CString outer, CString inner)
    {
        if (inner.toString().length() == 0)
        {   
            return true;
        }
        if (outer.toString().length() == 0)
        {
            return false;
        }
        // Creates lists and sorts them
        int[] outerList = toNumerical(new CString(outer.sortAscending()), 0);
        int[] innerList = toNumerical(new CString(inner.sortAscending()), 0);
        int i = 0;
        int j = 0;
        // Loops while i and j have not exceeded the length of the lists
        while (i < innerList.length && j < outerList.length)
        {
            // If the values are equal, move to the next value that needs to be searched for
            if (innerList[i] == outerList[j])
            {
                i++;
                j++;
            }
            // If the value in the outer list is smaller, go to the next value
            else if (outerList[j] < innerList[i])
            {
                j++;
            }
            else
            {
                return false;
            }
        }
        return i == innerList.length;
    }

    // Decrypts str
    public static CString decrypt(CString str)
    {
        int[] intList = toNumerical(str, 0);
        int count = 1;
        int clumps = 0;
        // Counts the amount of clumps
        for (int i = 1; i < intList.length; i++)
        {
            // If the value before is the same as the current value, count is increased
            if (intList[i] == intList[i-1])
            {
                count++;
            }
            else
            {
                // Checks if count is greater than or equal to 2.
                // If so, clumps is increased
                if (count >= 2)
                {
                    clumps++;
                }
                count = 1;
            }
        }
        if (count >= 2)
        {
            clumps++;
        }
        // inputString stores the characters in str
        String inputString = "";
        // Turns the ascii into letters, and then offsets it by the amount of clumps
        for (int j = 0; j < intList.length; j++)
        {
            char shiftedChar = (char) (intList[j] - clumps);
            inputString += shiftedChar;
        }
        String reversed = "";
        // Reverses the string
        for (int i = inputString.length() - 1; i >= 0; i--)
        {
            reversed += inputString.charAt(i);
        }

        return new CString(reversed);
    }
}