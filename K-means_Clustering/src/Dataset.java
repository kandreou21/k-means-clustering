import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Dataset {

    public static final String FILE_PATH = "Dataset.txt";


    public static void main(String[] args){

        try {
            File file = new File(FILE_PATH);
            file.createNewFile();
            FileWriter myWriter = new FileWriter(FILE_PATH);

            for (int i = 0; i < 150; i++){
                float x1 = RandomPoint(0.8f, 1.2f);
                float x2 = RandomPoint(0.8f, 1.2f);
                myWriter.write(x1 + "\t" + x2 + "\n");
            }

            for (int i = 0; i < 150; i++){
                float x1 = RandomPoint(0.0f, 0.5f);
                float x2 = RandomPoint(0.0f, 0.5f);
                myWriter.write(x1 + "\t" + x2 + "\n");
            }

            for (int i = 0; i < 150; i++){
                float x1 = RandomPoint(0.0f, 0.5f);
                float x2 = RandomPoint(1.5f, 2.0f);
                myWriter.write(x1 + "\t" + x2 + "\n");
            }

            for (int i = 0; i < 150; i++){
                float x1 = RandomPoint(1.5f, 2.0f);
                float x2 = RandomPoint(0.0f, 0.5f);
                myWriter.write(x1 + "\t" + x2 + "\n");
            }

            for (int i = 0; i < 150; i++){
                float x1 = RandomPoint(1.5f, 2.0f);
                float x2 = RandomPoint(1.5f, 2.0f);
                myWriter.write(x1 + "\t" + x2 + "\n");
            }

            for (int i = 0; i < 75; i++){
                float x1 = RandomPoint(0.8f, 1.2f);
                float x2 = RandomPoint(0.0f, 0.4f);
                myWriter.write(x1 + "\t" + x2 + "\n");
            }

            for (int i = 0; i < 75; i++){
                float x1 = RandomPoint(0.8f, 1.2f);
                float x2 = RandomPoint(1.6f, 2.0f);
                myWriter.write(x1 + "\t" + x2 + "\n");
            }

            for (int i = 0; i < 75; i++){
                float x1 = RandomPoint(0.3f, 0.7f);
                float x2 = RandomPoint(0.8f, 1.2f);
                myWriter.write(x1 + "\t" + x2 + "\n");
            }

            for (int i = 0; i < 75; i++){
                float x1 = RandomPoint(1.3f, 1.7f);
                float x2 = RandomPoint(0.8f, 1.2f);
                myWriter.write(x1 + "\t" + x2 + "\n");
            }

            for (int i = 0; i < 150; i++){
                float x1 = RandomPoint(0.0f, 2.0f);
                float x2 = RandomPoint(0.0f, 2.0f);
                myWriter.write(x1 + "\t" + x2 + "\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }

    public static float RandomPoint(float min, float max){
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }
}
