public class Calculator {

    // IEEE754
    private double decimal_1 = 0.010204081; //0x3C272F05
    private double decimal_2 = 0.005050505; //0x3BA57EB5

    public int status(int species_A, int species_B, int level, int individual, int bonus){

        double status = 0;

        status = species_A + (individual +species_B -species_A) * decimal_1 * (level -1) + bonus * (1 + level +decimal_2);

        return (int)status;
    }
}
