/**
 * Created by eray on 05.01.2017.
 */

/**
 * Elemanlari çift halinde tutabilmek icin pair sinifini implement ettim
 * c++ daki std::pair sinifina benzemektedir.
 */
public class TwoTypePair<T1,T2> {
    private T1 first;
    private T2 second;
    public TwoTypePair()
    {
        first = null ;
        second = null ;
    }
    public TwoTypePair(T1 firstItem, T2 secondItem)
    {
        first = firstItem;
        second = secondItem;
    }
    public void setFirstSecond(T1 newFirst,T2 newSecond)
    {
        first = newFirst;
        second = newSecond;
    }

    public static <T1, T2> TwoTypePair<T1, T2> createPair(T1 element0, T2 element1) {
        return new TwoTypePair<T1,T2>(element0, element1);
    }

    public T1 getFirst()
    {
        return first;
    }

    public T2 getSecond()
    {
        return second;
    }

    public String toString()
    {
        return ( "first: " + first.toString() + "\n"
                + "second: " + second.toString() );
    }

    @SuppressWarnings("unchecked")
    /**
     * Object sınıfına ait olan equals methodu override edilirek
     * bu sınıfa göre tekrardan implement edilmistir.
     * @param otherObject
     * @return boolean
     */
    public boolean equals(Object otherObject)
    {
        if (otherObject == null )
            return false ;
        else if (getClass( ) != otherObject.getClass( ))
            return false ;
        else
        {
            TwoTypePair<T1, T2> otherPair =
            (TwoTypePair<T1, T2>)otherObject;
            return (first.equals(otherPair.first)
            && second.equals(otherPair.second));
        }
    }
}