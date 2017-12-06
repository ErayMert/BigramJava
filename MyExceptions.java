/**
 * Created by eray on 05.01.2017.
 */
public class MyExceptions {

    public static class EmptyFileException extends Exception {
        String message;

        public EmptyFileException(String newMessage) {
            message = newMessage;
        }

        public String toString() {
            return message;
        }

    }

    public static class IncorrecDataTypeEx extends Exception {
        String message;

        public IncorrecDataTypeEx(String newMessage) {
            message = newMessage;
        }

        public String toString() {
            return message;
        }

    }
}