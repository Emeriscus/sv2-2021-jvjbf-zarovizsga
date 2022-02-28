package sentences;

import java.util.List;

public class SentenceTransformer {

    public static final List<Character> PUNCTUATION = List.of('.', '?', '!');

    public String shortenSentence(String sentence) {
        isSentence(sentence);
        String[] words = sentence.split(" ");
        if (words.length >= 5) {
            return words[0] + " ... " + words[words.length - 1];
        } else {
            return sentence;
        }
    }

    private boolean isSentence(String sentence) {
        if (!Character.isUpperCase(sentence.charAt(0))) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
        if (!PUNCTUATION.contains(sentence.charAt(sentence.length() - 1))) {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
        return true;
    }
}

