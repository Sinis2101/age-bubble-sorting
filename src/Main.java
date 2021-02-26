import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class Main {

    public final static String INPUT_PATH = "data/Input2.txt";
    public final static String OUTPUT_PATH = "data/Output2.txt";

    private static ArrayList<ArrayList<Double>> doublesArray;
    private static ArrayList<String> countList;
    private static int mods;
    private static int steps;
    private static DecimalFormat format;

    public static void main(String[] args) throws Exception {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        format = new DecimalFormat("#.##", symbols);

        doublesArray = new ArrayList<>();
        countList = new ArrayList<>();
        mods = 0;
        steps = 0;

        importData();

        for (int i = 0; i < doublesArray.size(); i++) {

            ArrayList<Double> temporalArray = bubbleSorting(doublesArray.get(i));
            doublesArray.set(i, temporalArray);

        }

        exportData();

    }

    // IMPORT - EXPORT

    private static void importData() throws IOException {

        BufferedReader br;

        br = new BufferedReader(new FileReader(INPUT_PATH));

        String line = br.readLine();
        line = br.readLine();

        while (line != null) {

            String[] parts = line.split(" ");
            ArrayList<Double> temporalArray = new ArrayList<>();

            for (String part : parts) {
                temporalArray.add(Double.parseDouble(part));
            }

            doublesArray.add(temporalArray);

            line = br.readLine();

        }

        br.close();

        System.out.println("Data was imported successfully.");

    }

    private static void exportData() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_PATH));

        for (String line : countList) {
            bw.write(line + "\n");
        }

        bw.close();

        System.out.println("Data was exported successfully.");

    }

    // SORTING

    private static ArrayList<Double> bubbleSorting(ArrayList<Double> arrayList) {

        mods = 0;
        steps = 0;

        StringBuilder content = new StringBuilder();

        for (int i = arrayList.size() - 1; i > 0; i--) {

            for (int j = 0; j < arrayList.size() - 1; j++) {

                if (arrayList.get(j) > arrayList.get(j + 1)) {

                    double temporalDouble = arrayList.get(j);

                    arrayList.set(j, arrayList.get(j + 1));
                    arrayList.set((j + 1), temporalDouble);

                    mods++;

                }
            }

            steps++;

        }

        for (int i = 0; i < arrayList.size(); i++) {

            if (i == arrayList.size() - 1) {
                content.append(arrayList.get(i));
            } else {
                content.append(arrayList.get(i)).append(" ");
            }

        }

        format.setRoundingMode(RoundingMode.DOWN);

        String temporalString = format.format(getAverage(mods, steps)) + "";

        if (!temporalString.contains(".")) {
            temporalString += ".0";
        }

        countList.add(temporalString + "-" + content);

        return arrayList;

    }

    // OPERATIONS

    private static double getAverage(int changes, int steps) {
        return (double) changes / steps;
    }

}