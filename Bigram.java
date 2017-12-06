import java.io.FileNotFoundException;

/**
 * Created by eray on 04.01.2017.
 */
public interface Bigram<T> {

    public void readFile(String filename) throws FileNotFoundException,MyExceptions.EmptyFileException,
                                                MyExceptions.IncorrecDataTypeEx;
    public int numGrams();
    public int numOfGrams(T input1, T input2);
    public String toString();
}