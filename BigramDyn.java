import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by eray on 04.01.2017.
 */
public class BigramDyn<T extends Comparable<? super T> >implements Bigram<T> {

    private int dataType;
    private int size;
    private int capacity;
    private T [] fileArray;

    /**
     * Bu fonksiyonda array in size ini belirlemek icin tekrardan okuma yapip
     *  size data member ina array de ne kadar eleman olacagi kaydediliyor
     * @param filename
     */
    private void sizeFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        int i=0;
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            sc.next();
            i++;
        }
        sc.close();

        size = i;
    }

    @SuppressWarnings("unchecked")
    /**
     * Scanner ile okuma yapip buradan gelen string dataType degerine gore
     * int veya double a donusturme yapilir.Eger yapilamaz ise exception
     * firlatilir.Daha sonra bu string T tipine cast edilerek fileArray'e
     * koyulur.
     * @param filename
     * @throws FileNotFoundException
     * @throws NumberFormatException
     */
    private void storeAndCheckDataType(String filename) throws FileNotFoundException,MyExceptions.EmptyFileException, MyExceptions.IncorrecDataTypeEx {

        File file = new File(filename);
        Scanner sc = new Scanner(file);

        // fileArray e yer acmak icin bu fonskiyondan size alinir
        sizeFile(filename);

        if(getSize() == 0) {
            throw new MyExceptions.EmptyFileException("----   EMPTY FILE   ----");
        }

        while(getSize() >= capacity)
            capacity = capacity*2;

        fileArray =(T[]) new Comparable[capacity];

        // data type integer
        if(dataType == 1)
        {
            String st = new String();
            int i=0;
            while (sc.hasNext()) {

                st=sc.next();
                if(isIntConvert(st)==false)
                    throw new MyExceptions.IncorrecDataTypeEx("--   Incorrect data type in file  --");
                else
                {
                    Integer a= Integer.parseInt(st);
                    fileArray[i] =(T)a;
                    i++;
                }
            }
        }

        // data type string
        if(dataType == 2)
        {
            int i=0;
            while (sc.hasNext()) {
                fileArray[i] =(T)sc.next();
                i++;
            }
        }

        // data type double
        if(dataType == 3)
        {
            String st = new String();
            int i=0;
            while (sc.hasNext()) {

                st=sc.next();
                if(isDoubleConvert(st)==false)
                    throw new MyExceptions.IncorrecDataTypeEx("--   Incorrect data type in file  --");
                else
                {
                    Double a = Double.parseDouble(st);
                    fileArray[i] =(T)a;
                    i++;
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

    public BigramDyn(){
        capacity = 50;
        size = 0;
        dataType = 1;
    }

    public BigramDyn(int theDataType){
        capacity = 50;
        size = 0;
        dataType = theDataType;
    }

    @SuppressWarnings("unchecked")
    public BigramDyn(BigramDyn bDynObject)
    {
        if(bDynObject == null)
        {
            System.out.println("Fatal Error: aborting program");
            System.exit(0);
        }
        capacity = bDynObject.capacity;
        size = bDynObject.size;

        fileArray =(T[]) new Comparable[capacity];

        for(int i=0; i< getSize(); i++)
            fileArray[i] = (T)bDynObject.fileArray[i];
    }

    /**
     * dosyadaki eleman sayisini dondurur.
     * @return size
     */
    public int getSize()
    {
        return size;
    }


    @Override
    public void readFile(String filename) throws FileNotFoundException, MyExceptions.EmptyFileException, MyExceptions.IncorrecDataTypeEx {

        storeAndCheckDataType(filename);

        /*for (int i=0; i<size; i++)
            System.out.print(fileArray[i] + " ");
        System.out.println();*/
    }

    /**
     * toplamda kac tane bigram ikilisi oldugunu dondurur.
     * @return bigram number
     */
    @Override
    public int numGrams() {

        return getSize()-1;
    }

    /**
     * Verilen T tipindeki ikilinin kac tane oldugunu dondurur.
     * @param input1
     * @param input2
     * @return count
     */
    @Override
    public int numOfGrams(T input1, T input2) {

        int count=0;

        for (int i = 0 ,j=1; i < getSize() && j<getSize(); i++, j++)
        {
           // System.out.println(i+" " +j);

                //System.out.println(fileArray[i]+" "+ fileArray[j]);
                if(fileArray[i].equals(input1) && fileArray[j].equals(input2))
                {
                    count++;
                }

        }
        return count;
    }

    /**
     * FileArray daki tüm ikilileri ekrana basar.
     * @return printBigram
     */
    @Override
    public String toString()
    {
        String displayBigram = new String();

        for (int i = 0 ,j=1; i < getSize() && j<getSize(); i++, j++)
        {
            displayBigram += fileArray[i]+" "+ fileArray[j]+"\n";
        }

        return displayBigram;
    }
}