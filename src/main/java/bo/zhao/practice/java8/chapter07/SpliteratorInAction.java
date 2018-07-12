package bo.zhao.practice.java8.chapter07;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by zhaobo on 2018/7/1.
 */
public class SpliteratorInAction {
    private final String SENTENCE = " Nel mezzo del cammin di nostra vita " +
            "mi ritrovai in una selva oscura" +
            " ché la dritta via era smarrita ";

    @Test
    public void test() {
        int i = countWordsIteratively(SENTENCE);
        Assert.assertEquals(i, 19);
        Assert.assertEquals(countWordsFunction(SENTENCE), 19);
//        Assert.assertEquals(countWordsFunctionParallel(SENTENCE), 19);
        Assert.assertEquals(test3(), 19);

    }

    /**
     * 一个迭代式字数统计方法
     */
    private int countWordsIteratively(String str) {
        int counter = 0;
        boolean lastSpace = true;

        for (char c : str.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }


    private int countWordsFunction(String str) {
        Stream<Character> characterStream = IntStream.range(0, str.length()).mapToObj(str::charAt);
        return countWords(characterStream);
    }

    private int countWordsFunctionParallel(String str) {
        Stream<Character> characterStream = IntStream.range(0, str.length()).mapToObj(str::charAt).parallel();
        return countWords(characterStream);
    }

    private int test3() {
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, Boolean.TRUE);
        return countWords(stream);
    }


    private int countWords(Stream<Character> stream) {
        return stream
                .reduce(new WordCounter(0, true),
                        WordCounter::accumulate,
                        WordCounter::combine)
                .getCounter();
    }


    private static class WordCounter {
        private final int counter;

        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return lastSpace ? this : new WordCounter(counter, true);
            } else {
                return lastSpace ? new WordCounter(counter + 1, false) : this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(wordCounter.counter + this.counter, this.lastSpace);
        }

        public int getCounter() {
            return counter;
        }
    }


    private static class WordCounterSpliterator implements Spliterator<Character> {

        private final String str;

        private int currentChar = 0;

        public WordCounterSpliterator(String str) {
            this.str = str;
        }

        /**
         * tryAdvance方法把String中当前位置的Character传给了Consumer，
         * 并让位置加一。作为参数传递的Consumer是一个Java内部类，
         * 在遍历流时将要处理的Character传给了一系列要对其执行的函数。
         * 这里只有一个归约函数，即WordCounter类的accumulate方法。
         * 如果新的指针位置小于String 的总长，且还有要遍历的Character，
         * 则tryAdvance返回true。
         */
        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(str.charAt(currentChar++));
            return currentChar < str.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = str.length() - currentChar;
            if (currentSize <= 10) {
                return null;
            }
            for (int splitPos = currentSize / 2 + currentChar; splitPos < str.length(); splitPos++) {
                if (Character.isWhitespace(str.charAt(splitPos))) {
                    Spliterator<Character> spliterator = new WordCounterSpliterator(str.substring(currentChar, splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return str.length() - currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }
}
