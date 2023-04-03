//4316 Konstantinos Andreou
//4416 Ioannis Manos
//4463 Miltiadis Papatheodoropoulos
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Kmeans {

    private static final int M = 9;
    private static final int TOTAL_POINTS = 1200;

    private static Point[] points = new Point[TOTAL_POINTS];
    private static Point[] centers = new Point[M];
    private static float[] sumX1 = new float[M];
    private static float[] sumX2 = new float[M];
    private static int[] pointsInTeam = new int[M];
    private static boolean teamChanged = true;


    private static float error = 0.0f;

    public static void main(String[] args){

        for(int i = 0; i < TOTAL_POINTS; i++){
            points[i] = new Point();
        }

        for(int i = 0; i < M; i++){
            centers[i] = new Point();
        }

        try {
            File centersFile = new File("Centers.txt");
            centersFile.createNewFile();
            FileWriter centerWriter = new FileWriter("Centers.txt");

            File teamsFile = new File("Teams.txt");
            teamsFile.createNewFile();
            FileWriter teamsWriter = new FileWriter("Teams.txt");

            readFile();
            setFirstCenters();
            for(int i = 0; i < TOTAL_POINTS; i++){
                points[i].setTeam(-1);
                points[i].setLastRUN(-5);
            }

            while(teamChanged){
                assignNewTeams();
            }
            errorCalculator();
            System.out.println("Error found: " + error / (float)TOTAL_POINTS);

            for(int i = 0; i < M; i++){
                centerWriter.write(centers[i].getX1() + "\t" + centers[i].getX2() + "\n");
            }

            for(int i = 0; i < TOTAL_POINTS; i++){
                teamsWriter.write(points[i].getTeam() + "\n");
            }

            centerWriter.close();
            teamsWriter.close();
        } catch (IOException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }

    private static void readFile(){
        try {
            File myObj = new File(Dataset.FILE_PATH);
            Scanner myReader = new Scanner(myObj);

            int index = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] pointsString = data.split("\t");

                points[index].setX1(Float.parseFloat(pointsString[0]));
                points[index].setX2(Float.parseFloat(pointsString[1]));

                index++;
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }

    private static void setFirstCenters(){
        int randomPoint;
        for(int i = 0; i < M; i++){
            randomPoint = (int)Dataset.RandomPoint(0f, (float)TOTAL_POINTS);
            centers[i].setX1(points[randomPoint].getX1());
            centers[i].setX2(points[randomPoint].getX2());
        }
    }


    private static void assignNewTeams(){
        teamChanged = false;
        for(int i = 0; i < TOTAL_POINTS; i++){
            double min = Double.MAX_VALUE;

            for(int j = 0; j < M; j++){
                double x1Pow = Math.pow(centers[j].getX1() - points[i].getX1(), 2);
                double x2Pow = Math.pow(centers[j].getX2() - points[i].getX2(), 2);
                double distance = Math.sqrt(x1Pow + x2Pow);

                if(distance < min){
                    points[i].setTeam(j);
                    min = distance;
                }
            }
            checkForEnd(i);

            points[i].setLastRUN(points[i].getTeam());
        }
        for(int i = 0; i < M; i++){
            sumX1[i] = 0.0f;
            sumX2[i] = 0.0f;
            pointsInTeam[i] = 0;
        }

        for (int i = 0; i < TOTAL_POINTS; i++){
            pointsInTeam[points[i].getTeam()]++;
            sumX1[points[i].getTeam()] += points[i].getX1();
            sumX2[points[i].getTeam()] += points[i].getX2();
        }

        for (int i = 0; i < M; i++){
            centers[i].setX1(sumX1[i] / pointsInTeam[i]);
            centers[i].setX2(sumX2[i] / pointsInTeam[i]);
        }
    }


    private static void checkForEnd(int pos) {
        if (points[pos].getTeam() != points[pos].getLastRUN()) {
            teamChanged = true;
        }

    }
    private static void errorCalculator(){
        for (int i = 0; i < TOTAL_POINTS; i++){
            double x1Pow = Math.pow(centers[points[i].getTeam()].getX1() - points[i].getX1(), 2);
            double x2Pow = Math.pow(centers[points[i].getTeam()].getX2() - points[i].getX2(), 2);
            error += Math.sqrt(x1Pow + x2Pow);
        }
    }

}
