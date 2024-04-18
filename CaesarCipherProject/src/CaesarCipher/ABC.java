package CaesarCipher;

import java.util.ArrayList;
import java.util.List;

public class ABC {
    static class Symbol{
        protected static final ArrayList<Character> LIST = new ArrayList<>(List.of(
                '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9','.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ' // Символы математических операций
        ));
    }


    static class Eng {

        protected static final ArrayList<Character> LIST = new ArrayList<>(
                List.of(
                        'A', 'B', 'C', 'D', 'E', 'F', 'G',
                        'H', 'I', 'J', 'K', 'L', 'M', 'N',
                        'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                        'V', 'W', 'X', 'Y', 'Z'));
    }

    static class Ua {
        protected static final ArrayList<Character> LIST = new ArrayList<>(
                List.of(
                        'А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є',
                        'Ж', 'З', 'И', 'І', 'Ї', 'Й', 'К', 'Л',
                        'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У',
                        'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я'));
    }



}
