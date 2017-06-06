package wholedta_program;



import java.io.*;
import java.util.Collection;

import com.google.common.collect.ArrayListMultimap;

import com.google.common.collect.Multimap;


/**
 * @author Haijuan
 *
 */


public class MapForErrorSet {

   private static Multimap<String, String> multiMap;



    public MapForErrorSet(Multimap<String, String> multiMap) {
        this.multiMap = multiMap;
    }


    public static Multimap<String, String> map(){

        FileInputStream error = null;
        try {
            error = new FileInputStream("D:\\research\\data\\qual_score_report\\qs_report.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inReadere = null;
        try {
            inReadere = new InputStreamReader(error, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BufferedReader bufReadere = new BufferedReader(inReadere);

        String errorline = null;
        String ErrorRead_id = null;
       Multimap<String,String> Multimap = ArrayListMultimap.create();
        String[] Mylist = null;
        String position = null;


        try {
            while ((errorline = bufReadere.readLine()) != null)

            {

                Mylist = errorline.split(" ");
                ErrorRead_id = Mylist[0];
                position = Mylist[1];
                Multimap.put(ErrorRead_id, position);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


  return Multimap;
}



    public static Collection<String> multimap (String ReadID){
        Multimap<String, String> multimap = map( );
       MapForErrorSet m =new MapForErrorSet(multimap );
       Collection<String> PosArray = null;
        PosArray = multiMap.get(ReadID);
        return PosArray;
    }



}


