package wholedta_program;

import com.google.common.collect.Multimap;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Haijuan on 2/11/2017.
 */

public class TNData extends MapForErrorSet {


    public TNData(Multimap<String, String> Multimap) {
        super(Multimap);
    }

    public static void main(String[] args) throws IOException {

        FileInputStream Orig = new FileInputStream("D:\\research\\fasqfile\\D1S_5X_R1.fastq\\D1S_5X_R1.fastq");
        InputStreamReader inReaderO = new InputStreamReader(Orig, "UTF-8");
        BufferedReader bufReaderO = new BufferedReader(inReaderO);
        LineNumberReader bufReaderOO = new LineNumberReader(inReaderO);
        FileInputStream Prem = new FileInputStream("D:\\research\\fasqfile\\D1S_5X_R1_prem.fastq\\prem.txt");
        InputStreamReader inReadP = new InputStreamReader(Prem, "UTF-8");
        BufferedReader bufReaderP = new BufferedReader(inReadP);
        LineNumberReader bufReaderPP = new LineNumberReader(inReadP);
        File f = new File("D:\\research\\data\\qual_score_report\\TN.txt");

        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);

        String ReadID = null;
        String OriSeq = null;
        String PremSeq = null;
        String OrigQS;
        String PremQS;
        String LineO;
        String LineP;

        int j = 0;
        int i = 1;


        while ((LineO = bufReaderO.readLine()) != null && (LineP = bufReaderP.readLine()) != null) {
            j++;
            if (j == i) {
                ReadID = LineO.substring(1);

            }
            else if (j - i == 1) {
                OriSeq = LineO;
              System.out.println(OriSeq);
                PremSeq = LineP;
              System.out.println(PremSeq);
                System.out.println("Read.id" + " is " + ReadID);

            }
                 else if (j - i == 3) {
                    OrigQS = LineO;
                    PremQS = LineP;
                  //  System.out.println("Read.id" + " is " + ReadID);
                    Collection<String> rr = multimap(ReadID);
                    if (rr.isEmpty()) {
                      System.out.println(rr);
                        for (int k = 1; k <= OriSeq.length(); k++) {
                            char a = OrigQS.charAt(k-1);
                            char b = PremQS.charAt(k-1);
                            int OrigQV = (int) a - 33;
                            int PremQV = (int) b - 33;
                           System.out.print(ReadID + " " + k + " "+ OrigQV + " "+ PremQV +" " + "\n");
                            bw.write(ReadID + " " + k + " "+ OrigQV + " "+ PremQV +" " + "\n");
                        }

                    } else if (!rr.isEmpty()) {
                        System.out.println(ReadID);
                      System.out.println(!multimap(ReadID).isEmpty());
                       System.out.println(rr);

                     //   System.out.println(OriSeq);

                        // System.out.println(PremSeq);
                        for (int z = 0; z < OriSeq.length(); z++) {
                            if (OriSeq.charAt(z) == PremSeq.charAt(z) && rr.contains(z + 1) == false) {
                                char a = OrigQS.charAt(z);
                                char b = PremQS.charAt(z);
                                int OrigQV = (int) a - 33;
                                int PremQV = (int) b - 33;
                               System.out.print(ReadID + " " + (z + 1) + " "+ OrigQV + " "+ PremQV +" " + "\n");
                               bw.write(ReadID + " " + (z + 1) + " "+ OrigQV + " "+ PremQV +" " + "\n");
                            }


                        }

                    }
                i = i + 4;
                    System.out.println(j);
                }


            }
        }
    }













