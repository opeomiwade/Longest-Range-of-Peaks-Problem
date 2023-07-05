import javax.xml.crypto.Data;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.lang.*;

//OPE OMIWADE

public class Main201477380{
  public static boolean nomatchfound = false;

  public static ArrayList<String> ReadData(String pathname) {
    ArrayList<String> strlist = new ArrayList<String>();
    try {

        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        int j = 0;
        String line = "";
        while ((line = br.readLine()) != null) {
            strlist.add(line);
        }
        return strlist;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return strlist;
  }

  public static ArrayList<ArrayList<Integer> > DataWash(ArrayList<String> Datalist) {
    ArrayList<ArrayList<Integer> > AIS = new ArrayList<ArrayList<Integer> >();
    ArrayList<Integer> IS = new ArrayList<Integer>();
    for (int i = 0; i < Datalist.size(); i++) {
        String Tstr = Datalist.get(i);
        if (Tstr.equals("A") == false) {
            IS.add(Integer.parseInt(Tstr));
        }
        if (Tstr.equals("A")) {
        	ArrayList<Integer> elemAIS = new ArrayList<Integer>(IS);
            AIS.add(elemAIS);
            IS.clear();
        }
    }
    return AIS;
  }



//%%%%%%%%%%%%%%%%%%%%%%% Procedure LongestRange() that will contain your code:
  //RECURSIVE HELPER METHODS USE FOR LONGEST RANGE PROBLEM
  public static int checkForValidRange(int current , int[][] peakValues, int range, int outerloopIndex,int[] array,int noofpeaks){

    //Base Case
    if(outerloopIndex > noofpeaks){
      return 0 ;
    }

    //Recursive Case
    for(int k = outerloopIndex + 1 ; k < noofpeaks ; k++){
      int peakDifference = Math.abs(current - peakValues[k][1]);

      //check 2
      if(peakDifference > 2){
        //check 4
        if( array[current] >= array[peakValues[k][0]] && array[peakValues[outerloopIndex][2]] <= array[peakValues[k][0]]){
           //increment range value as check 2 and check 4 were passed
          range++;
          // call function again changing the value of the current peak to the value that was matched to the previous current.

          range = checkForValidRange(peakValues[k][1], peakValues,range,k,array,noofpeaks);

          break; // breaks loop when no more matches for a peak can be found
        }
      }

      else{
        continue;
      }

    }
    return range;

  }


  //RECURSIVE HELPER METHODS USED FOR LONGEST RANGE PROBLEM
  public static int LongestRange(int[] A, int n){
    /* Input is array of input sequence (a_1 <= a_2 <= ... <= a_n) as A[0,1,...,n-1], that is,
    a_1 = A[0], a_2 = A[1], ..., a_n = A[n-1].
    n = number of integers in sequence A
    This procedure should return the value of the longest range (>= 1) or 0 if there is no peak.
    It should NOT return the respective longest range of peaks!
    */


    /* description of the main ideas of your solution:
    describe the recursive relation used in your dynamic programming
    solution and outline how you implement it sequentially in your code below.

    SOME NOTATION: s.t. is abbreviation of "such that"
                   a <= b (a is smaller than or equal to b)
                   a >= b (a is greater than or equal to b)
                   max [ a , b ] denotes the larger among a and b
                   Given an array T[1,...,n]
                   then M = max_{k : some condition C(k) holds} [ T[k] ],
                   M denotes the largest value T[k] over all indices k
                   such that condition C(k) holds.
                   Here we denote by {k : some condition C(k) holds} the set of all
                   elements k which make the condition C(k) hold true.

    Peaks are first searched for in teh array A and when the peaks are found they are put into teh DP array called peakArrays.

    A recursive method has been created to check if consecutive peaks pass condition 2 and condition 4 as stated in the brief given for the assignment.This
    works by using an if statement to check if the conditions are satisfied, if the conditions are satisfied the range value is incremented by 1
    and then the method invokes itself again, changing the 'current' parameter to the peak that was just matched and changing
    the outerloop index to the index of the peak that was just matched.This process is repeated for every peak found in the Array A.
    In the longest rage method this recursive method is called and after every execution of the recursive method it checks the current longestrange
    afainst the previously stored longest range and updates teh final value accordingly.
    */

    //find all peaks in the array of integers
    int j = 0; // for rows of 2d array
    int currentPeak;
    int noOfPeaks = 0;
    int longestRange = 0;
    int range = 1;
    int[][] peakArrays = new int[(n-1)/2][3]; // 2d array to hold all peaks found in n integers
    for(int i = 1 ; i < n - 1 ; i++){
      if(A[i - 1] < A[i] && A[i + 1] < A[i] ){
        peakArrays[j][0] = i - 1;
        peakArrays[j][1] = i;
        peakArrays[j][2] = i+1;

        j++;
        noOfPeaks++;
      }
    }

    //check if peaks are disjoint and find the longest range of peaks.


    for(int i = 0 ; i < noOfPeaks ; i++){
      currentPeak = peakArrays[i][1];
      range = 1;
      range = checkForValidRange(currentPeak,peakArrays,range,i,A,noOfPeaks);
      if(range > longestRange){
        longestRange = range;
      }
    }

    // return max longestRange value
    return longestRange;
  } // end of procedure LongestRange()




  public static int Computation(ArrayList<Integer> Instance, int opt){
      // opt=1 here means option 1 as in -opt1, and opt=2 means option 2 as in -opt2
      int NGounp = 0;
      int size = 0;
      int Correct = 0;
      size = Instance.size();

      int [] A = new int[size-opt];
      // NGounp = Integer.parseInt((String)Instance.get(0));
      NGounp = Instance.get(0); // NOTE: NGounp = 0 always, as it is NOT used for any purpose
                                         // It is just the first "0" in the first line before instance starts.
      for (int i = opt; i< size;i++ ){
          A[i-opt] = Instance.get(i);
      }
      int Size =A.length;
      if (NGounp >Size ){
          return (-1);
      }
      else {
          //Size
          int R = LongestRange(A,Size);
          return(R);
      }
  }

  public static String Test;


  public static void main(String[] args) {
  	if (args.length == 0) {
  		String msg = "Rerun with flag: " +
  		"\n\t -opt1 to get input from dataOne.txt " +
  		"\n\t -opt2 to check results in dataTwo.txt";
  		System.out.println(msg);
  		return;
  	}
      Test = args[0];
      int opt = 2;
      String pathname = "dataTwo.txt";
      if (Test.equals("-opt1")) {
          opt = 1;
          pathname = "dataOne.txt";
      }


      ArrayList<String> Datalist = new ArrayList<String>();
      Datalist = ReadData(pathname);
      ArrayList<ArrayList<Integer> > AIS = DataWash(Datalist);

      int Nins = AIS.size();
      int NGounp = 0;
      int size = 0;
      if (Test.equals("-opt1")) {
          for (int t = 0; t < Nins; t++) {
              int Correct = 0;
              int Result = 0;
              ArrayList<Integer> Instance = AIS.get(t);
              Result = Computation(Instance, opt);
              System.out.println(Result);
          }
      }
      else {
          int wrong_no = 0;
          int Correct = 0;
          int Result = 0;
          ArrayList<Integer> Wrong = new ArrayList<Integer>();
          for (int t = 0; t < Nins; t++) {
              ArrayList<Integer> Instance = AIS.get(t);
              Result = Computation(Instance, opt);
              System.out.println(Result);
              Correct = Instance.get(1);
              if (Correct != Result) {
                  Wrong.add(t+1);
                  wrong_no=wrong_no+1;
              }
          }
          if (Wrong.size() > 0) {System.out.println("Index of wrong instance(s):");}
          for (int j = 0; j < Wrong.size(); j++) {
              System.out.print(Wrong.get(j));
              System.out.print(",");

              /*ArrayList Instance = (ArrayList)Wrong.get(j);
              for (int k = 0; k < Instance.size(); k++){
                  System.out.println(Instance.get(k));
              }*/
          }
          System.out.println("");
          System.out.println("Percentage of correct answers:");
          System.out.println(((double)(Nins-wrong_no) / (double)Nins)*100);

      }
  }
}
