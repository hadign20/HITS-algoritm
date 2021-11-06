// Hadi Ghahremannezhad     cs610  0206  prp

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Math.abs;


public class hits0206 {

    private static int numOfNodes;
    private static double errorrate;
    private static int numOfLines = 0;  // number of lines in the graph file (ignoring the first line)
    private static int numOfIters = 1;
    private static DecimalFormat order = new DecimalFormat("0.0000000");


    public static void main(String[] args) throws IOException {

        //****************************   arguments   ****************************
        if (args.length != 3) {
            System.out.println("You should put three arguments: #ofIterations InitialValues GraphFileName");
            return;
        }

        int iterations = Integer.parseInt(args[0]);
        int initialValue = Integer.parseInt(args[1]);
        String filename = args[2];

        //****************************   graph file   ****************************
        //reading the input graph file and get the number of nodes and edges

        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(filename));
            //buffer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Invalid Graph File");
            e.printStackTrace();
        }
        assert buffer != null;
        String line = buffer.readLine();
        numOfNodes = Integer.parseInt(line.split(" ")[0]);
        int numOfEdges = Integer.parseInt(line.split(" ")[1]);
        //numOfEdges = numOfLines - 1;

        //If the graph has N GREATER than 10, then the values for iterations, initialvalue are automatically set to 0 and -1 respectively.
        if (numOfNodes > 10) {
            iterations = 0;
            initialValue = -1;
        }

        //***************************************************************************
        //an iterations equal to 0 corresponds to a default errorrate of 10^5. A -1, -2, etc , -6 for iterations becomes an errorrate of 10^1,10^2,... ,10^6 respectively.
        if (iterations == 0)
            errorrate = Math.pow(10, -5);
        else if (iterations < 0)
            errorrate = Math.pow(10, iterations);
        else numOfIters = iterations;

        // authority
        double[] a = new double[numOfNodes];
        // hub
        double[] h = new double[numOfNodes];
        //previous authority
        double[] a_prev = new double[numOfNodes];
        //previous hub
        double[] h_prev = new double[numOfNodes];


        //****************************   adjacency List   ****************************
        int numOfCols = 2;
        int[][] graphEdges = new int[numOfEdges][numOfCols];

        //System.out.println("Input Graph Edges:");
        for (int i = 0; i < graphEdges.length; i++) {
            line = buffer.readLine();
            numOfLines++;
            for (int j = 0; j < numOfCols; j++) {
                graphEdges[i][j] = Integer.parseInt(line.split(" ")[j]);
                //System.out.print(graphEdges[i][j] + "\t");
            }
            //System.out.println();
        }

        //System.out.println("Number of Lines:");
        //System.out.println(numOfLines);


        if (numOfEdges < numOfLines)
            System.out.println("graph has more edges than entered value in the first line.");
        else if (numOfEdges > numOfLines)
            System.out.println("graph has less edges than entered value in the first line.");


        //graph adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(numOfNodes);
        for (int i = 0; i < numOfNodes; i++)
            adj.add(new ArrayList<>());

        for (int i = 0; i < numOfLines; i++) {
            adj.get(graphEdges[i][0]).add(graphEdges[i][1]);
        }

        //****************************   initialization   ****************************
        switch (initialValue) {
            case 0:
                for (int i = 0; i < numOfNodes; i++) {
                    a[i] = 0;
                    h[i] = 0;
                    a_prev[i] = 0;
                    h_prev[i] = 0;
                }
                break;
            case 1:
                for (int i = 0; i < numOfNodes; i++) {
                    a[i] = 1;
                    h[i] = 1;
                    a_prev[i] = 1;
                    h_prev[i] = 1;
                }
                break;

            case -1:
                for (int i = 0; i < numOfNodes; i++) {
                    a[i] = 1.0 / numOfNodes;
                    h[i] = 1.0 / numOfNodes;
                    a_prev[i] = 1.0 / numOfNodes;
                    h_prev[i] = 1.0 / numOfNodes;
                }
                break;

            case -2:
                for (int i = 0; i < numOfNodes; i++) {
                    a[i] = 1.0 / Math.sqrt(numOfNodes);
                    h[i] = 1.0 / Math.sqrt(numOfNodes);
                    a_prev[i] = 1.0 / Math.sqrt(numOfNodes);
                    h_prev[i] = 1.0 / Math.sqrt(numOfNodes);
                }
                break;
        }


        int iter = 0;
        //****************************   adj matrix   ****************************

//        boolean[][] incident = new boolean[numOfNodes][numOfNodes];
//
//        int[] indegree = new int[numOfNodes];
//
//        for (int i = 0; i < numOfNodes; i++){
//            for (int j = 0; j < numOfNodes; j++) {
//                if (adj.get(i).contains(j) == true) {
//                    incident[i][j] = true;
//                    indegree[j]++;
//                }
//            }
//        }


        //****************************   print first line  ****************************
        System.out.printf("Base\t:%5d :", iter);
        for (int i = 0; i < numOfNodes; i++) {
            System.out.print("A/H[ " + i + "]=" +  order.format(a[i]) + "/" +order.format(h[i]) + " ");
        }
        System.out.println();


        //****************************   update authority and hub  ****************************
        iter = 1;

        do {
			a_prev = a.clone();
			h_prev = h.clone();
			
            //authority update
            for (int i = 0; i < numOfNodes; i++) {
                double sum = 0;
                for (int j = 0; j < numOfNodes; j++)
                    //if (incident[j][i] == true)
                    if (adj.get(j).contains(i) == true)
                        sum = sum + h_prev[j];
                a[i] = sum;
            }

            //hub update
            for (int i = 0; i < numOfNodes; i++) {
                double sum = 0;
                for (int j = 0; j < numOfNodes; j++)
                    //if (incident[i][j] == true)
                    if (adj.get(i).contains(j) == true)
                        sum = sum + a[j];
                h[i] = sum;
            }
            //authority Euclidean scale
            double a_sumOfSquares = 0;
            double h_sumOfSquares = 0;

            //denuminators
            for (int i = 0; i < numOfNodes; i++) {
                a_sumOfSquares = a_sumOfSquares + (a[i] * a[i]);
                h_sumOfSquares = h_sumOfSquares + (h[i] * h[i]);
            }
            for (int i = 0; i < numOfNodes; i++) {
                a[i] = a[i] / Math.sqrt(a_sumOfSquares);
                h[i] = h[i] / Math.sqrt(h_sumOfSquares);
            }

            //****************************   print for lower iterations  ****************************
            if (numOfNodes <= 9) {
                System.out.printf("Iter\t:%5d :", iter);
                for (int i = 0; i < numOfNodes; i++) {
                    System.out.print("A/H[ " + i + "]=" +  order.format(a[i]) + "/" + order.format(h[i]) + " ");
                }
                System.out.println();
                iter++;
                if (iterations > 0)
                    numOfIters--;
            }
            //****************************   print for higher iterations  ****************************
            else if (numOfNodes > 10) {
                System.out.printf("Iter\t:%5d", iter);
                System.out.println();
                for (int i = 0; i < numOfNodes; i++) {
                    System.out.println("A/H[ " + i + "]=" + order.format(a[i]) + "/" + order.format(h[i]));
                }
                iter++;
                if (iterations > 0)
                    numOfIters--;
            }

        } while (enough(a, a_prev, h, h_prev)== false && numOfIters > 0); // check when to stop

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //****************************   check error rate  ****************************
    private static boolean enough(double[] a, double[] a_prev, double[] h, double[] h_prev) {
        int counter = 0;
        for (int i = 0; i < numOfNodes; i++) {
            if ((abs(a[i] - a_prev[i]) < errorrate) && (abs(h[i] - h_prev[i]) < errorrate))
                counter++;
        }
        return counter == numOfNodes;
    }
}


