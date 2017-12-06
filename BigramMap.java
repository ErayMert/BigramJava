
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by eray on 05.01.2017.
 */

public class BigramMap<T extends Comparable<? super T>> implements Bigram<T>  {


    private ArrayList<T> fileList;
    private Map<TwoTypePair<T,T>, Integer> mapBigram;
    int dataType;

    @SuppressWarnings("unchecked")
    /**
     * Scanner ile okuma yapip buradan gelen string dataType degerine gore
     * int veya double a donusturme yapilir.Eger yapilamaz ise exception
     * firlatilir.Daha sonra bu string T tipine cast edilerek fileList e koyulur.
     * @param filename
     * @throws FileNotFoundException
     * @throws NumberFormatException
     */
    private void storeAndCheckType(String filename)throws FileNotFoundException,MyExceptions.EmptyFileException, MyExceptions.IncorrecDataTypeEx
    {
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        if(dataType == 1)
        {
            String st = new String();
            while (sc.hasNext()) {

                st=sc.next();
                if(isIntConvert(st)==false)
                    throw new MyExceptions.IncorrecDataTypeEx("--   Incorrect data type in file  --");
                else
                {
                    Integer a= Integer.parseInt(st);
                    fileList.add((T)a);
                }
            }
        }

        if(dataType == 2)
        {
            while (sc.hasNext()) {
                fileList.add((T)sc.next());
            }
        }

        // data type double
        if(dataType == 3)
        {
            String st = new String();
            while (sc.hasNext()) {

                st=sc.next();
                if(isDoubleConvert(st)==false)
                    throw new MyExceptions.IncorrecDataTypeEx("--   Incorrect data type in file  --");
                else
                {
                    Double a = Double.parseDouble(st);
                    fileList.add((T)a);

                }
            }
        }
        sc.close();

    }


    /**
     * Dosya icindekiler integer tipinde degil ise yakaliyor ve boolean deger donduruyor
     * @param input
     * @return
     */
    private static boolean isIntConvert(String input){
        boolean parsable = true;
        try{
            Integer.parseInt(input);
        }catch(Exception e){
            parsable = false;
        }
        return parsable;
    }

    /**
     * Dosya icindekiler double tipinde degil ise yakaliyor ve boolean deger donduruyor
     * @param input
     * @return
     */
    private static boolean isDoubleConvert(String input){
        boolean parsable = true;
        try{
            Double.parseDouble(input);
        }catch(Exception e){
            parsable = false;
        }
        return parsable;
    }

    public BigramMap()
    {
        fileList = new ArrayList<T>();
        dataType =1;
        mapBigram = new HashMap<TwoTypePair<T, T>, Integer>();

    }

    public BigramMap(int theDataType)
    {
        fileList = new ArrayList<T>();
        dataType = theDataType;
        mapBigram = new HashMap<TwoTypePair<T, T>, Integer>();

    }


    /**
     * storeAndCheckType methodunu cagirir ve burada fileList in bos olup olmadigini
     * kontrol eder.Eger bos ise Exception firlatir.
     * @param filename
     * @throws FileNotFoundException
     * @throws MyExceptions.EmptyFileException
     * @throws MyExceptions.IncorrecDataTypeEx
     */
    @Override
    public void readFile(String filename) throws FileNotFoundException, MyExceptions.EmptyFileException, MyExceptions.IncorrecDataTypeEx {

        storeAndCheckType(filename);

        if(fileList.isEmpty())
            throw new MyExceptions.EmptyFileException("----   EMPTY FILE   ----");

        /*for (int i=0; i<fileList.size(); i++)
            System.out.print(fileList.get(i)+ " ");
        System.out.println();*/
    }

    /**
     * bigram sayilarinin toplamini dondurur.
     * @return
     */
    @Override
    public int numGrams() {
        return fileList.size()-1;
    }

    /**
     * Verilen T tipindeki ikiliden kac tane oldugunu dondurur.
     * @param input1
     * @param input2
     * @return count
     */
    @Override
    public int numOfGrams(T input1, T input2) {

        // c++ daki std::pair methodunu kendim TwoTypePair.java dosyasinda implement ettim.
        TwoTypePair<T,T> pair = new TwoTypePair<>();

        //Burada pair in listesini olusturarak arrayList ikili eleman tutmus oldu.
        ArrayList<TwoTypePair<T,T>> pairList = new ArrayList<TwoTypePair<T, T>>();


        int count=0;

        for (int i=0, j=1; i< fileList.size() && j< fileList.size(); i++,j++)
            pairList.add(pair.createPair(fileList.get(i),fileList.get(j)));


        for (int i=0; i<pairList.size(); i++)
        {
            if(pairList.get(i).getFirst().equals(input1) && pairList.get(i).getSecond().equals(input2))
                count++;
            mapBigram.put(pairList.get(i),count);
        }

        return count;
    }

    /**
     * mapBigramda bulunan tum bigram ikililerini ekrana basar.
     * @return printBigram
     */
    @Override
    public String toString()
    {
        String printBigram = new String();

        for (Map.Entry<TwoTypePair<T,T>, Integer> entry : mapBigram.entrySet())
        {
            printBigram += entry.getKey().getFirst() +" "+ entry.getKey().getSecond()+ "\n";
        }

        return printBigram;
    }
}