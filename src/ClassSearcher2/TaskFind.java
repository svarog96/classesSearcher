package ClassSearcher2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;

/**
 * Created on 27.08.17.
 */
public class TaskFind implements Runnable {
    String szName,
            findTxt;

    public TaskFind( String findTxt, String szName ) {

        this.szName = szName;
        this.findTxt = findTxt;

    }

    public void run() {

        String s;

        try
        {
            LineNumberReader lnr =
                    new LineNumberReader(
                            new BufferedReader(
                                    new FileReader(szName)));
            while(true)
            {
                s = lnr.readLine();
                if (s == null) break;

                if(s.indexOf(findTxt) != -1)
                {
                    System.out.println( szName );
                    break;
                }
            }

            lnr.close();

        } catch ( Exception e ) {
            System.out.println( e.toString() );
        }
    }
}
