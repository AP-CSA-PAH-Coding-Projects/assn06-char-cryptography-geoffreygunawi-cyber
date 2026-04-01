package apcsa.githubtrack;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

// Implement your main application logic here
public class Main {
    public static void main(String[] args) throws IOException
    {
        ArrayList<String> strList = new ArrayList<>();
        File secretMessage = new File("src/main/resources/secretMessage.txt");
        Scanner scan = new Scanner(secretMessage);
        // Puts the words into strList
        while (scan.hasNext())
        {
            String item = scan.next();
            strList.add(item);
        }
        CString[] cStrList = new CString[strList.size()];
         // Creates CStrings for each word and decrypts them
        for (int i = 0; i < strList.size(); i++)
        {
            cStrList[i] = CStringUtil.decrypt(new CString(strList.get(i)));
        }
        int max = 0;
        // Searches for the largest ascii value in secretMessage.txt
        for (int j = 0; j < strList.size(); j++)
        {
            for (int k = 0; k < strList.get(j).length(); k++)
            {
                if ((int) strList.get(j).charAt(k) > max)
                {
                    max = (int) strList.get(j).charAt(k);
                }
            }
        }
        // Rotates the list
        cStrList = rotate(cStrList, max - 60);
        // Prints the list
        for (CString c : cStrList)
        {
            System.out.print(c.toString() + " ");
        }
        scan.close();
    }

    // Rotates strList to the left by amt
    public static CString[] rotate(CString[] strList, int amt)
    {
        // Rotates amt amount of times
        for (int i = 0; i < amt; i++)
        {
            // Moves every value forward once, with the front moved to the end
            CString temp = strList[0];
            for (int j = 1; j < strList.length; j++)
            {
                strList[j-1] = strList[j];
            }
            strList[strList.length-1] = temp;
        }
        return strList;
    }
}