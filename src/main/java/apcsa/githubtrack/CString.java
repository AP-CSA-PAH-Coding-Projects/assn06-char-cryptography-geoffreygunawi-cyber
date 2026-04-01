package apcsa.githubtrack;

// Implement your CString class here
public class CString {
    
    private char[] cString;

    public CString(String initStr)
    {
        cString = new char[initStr.length()];
        // Puts the characters in initStr into cString
        for (int i = 0; i < initStr.length(); i++)
        {
            cString[i] = initStr.charAt(i);
        }
    }

    // Returns a string representation of cString
    public String toString()
    {
        String returnString = "";
        for (char s : cString)
        {
            returnString += s;
        }
        return returnString;
    }

    // Returns the list of chars
    public char[] getCString()
    {
        return cString;
    }

    // Reverses cString
    public String reverse()
    {
        int start = 0;
        int end = cString.length - 1;
        while (start < end)
        {
            char temp = cString[start];
            cString[start] = cString[end];
            cString[end] = temp;
            start++;
            end--;
        }
        String returnString = "";
        for (char s : cString)
        {
            returnString += s;
        }
        return returnString;
    }

    // Sorts cString in ascending order
    public String sortAscending()
    {
        // Selection sort is used to sort the array
        for (int j = 0; j < cString.length - 1; j++)
        {
            int minIndex = j;
            for (int k = j + 1; k < cString.length; k++)
            {
                if (cString[k] < cString[minIndex])
                {
                    minIndex = k;
                }
            }
            char temp = cString[j];
            cString[j] = cString[minIndex];
            cString[minIndex] = temp;
        }
        return cString.toString();
    }

    // Sorts cString in descending order
    public String sortDescending()
    {
        // Insertion sort is used to sort the array
        for (int j = 1; j < cString.length; j++)
        {
            char temp = cString[j];
            int possibleIndex = j;
            while (possibleIndex > 0 && temp > cString[possibleIndex-1])
            {
                cString[possibleIndex] = cString[possibleIndex-1];
                possibleIndex--;
            }
            cString[possibleIndex] = temp;
        }
        return cString.toString();
    }
}