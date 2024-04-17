import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Manager {

    private final int level = 60;
    public int getLevel() {
        return level;
    }

    private final int bonus = 0;
    public int getBonus() {
        return bonus;
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        Calculator calculate = new Calculator();

        String filename = "species.csv";

        //try-with-resources構文を用いてclose()を省略
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {

            String line;

            br.readLine(); //一行目を読み飛ばし

            while ((line = br.readLine()) != null) {

                String[] cols = line.split(",", -1);

                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(cols[0] + ".csv"))){

                    writer.write("individual,HP,ATK,Mgc,Def,Agi\n");

                    for (int individual=0; individual <= 40; individual++){

                        ArrayList<String> statusList = new ArrayList<>();

                        statusList.add(Integer.toString(individual));

                        for (int i=1; i <= 5; i++){
                            statusList.add(Integer.toString(calculate.status(Integer.parseInt(cols[i]), Integer.parseInt(cols[i +5]), manager.getLevel(), individual, manager.getBonus())));
                        }

                        writer.write(statusList.get(0) + "," +
                                statusList.get(1) + "," +
                                statusList.get(2) + "," +
                                statusList.get(3) + "," +
                                statusList.get(4) + "," +
                                statusList.get(5) +"\n");
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
