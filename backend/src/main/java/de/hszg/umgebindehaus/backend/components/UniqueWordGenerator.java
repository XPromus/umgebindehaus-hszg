package de.hszg.umgebindehaus.backend.components;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UniqueWordGenerator {
    private ArrayList<Pair<String, AtomicInteger>> wordList = new ArrayList<>();

    private final Resource res;

    /*public static void main(String[] args) {
        UniqueWordGenerator uwg = new UniqueWordGenerator();
        while (true) {
            System.out.println(uwg.nextWord());
            try { System.in.read(); }
            catch (IOException e) { e.printStackTrace(); }
        }

    }*/

    public UniqueWordGenerator() {
        res = new ClassPathResource("wordlist.txt");
        ArrayList<String> words = readWordListFile();
        for (String word: words) {
            Pair<String, AtomicInteger> wordIntegerPair = Pair.of(word, new AtomicInteger());
            wordList.add(wordIntegerPair);
        }
    }

    public String nextWord() {
        Random random = new Random();
        int number = random.nextInt(wordList.size());

        Pair<String, AtomicInteger> pair = wordList.get(number);
        String word = pair.getFirst() + pair.getSecond().getAndIncrement();

        return word;
    }

    private ArrayList<String> readWordListFile() {
        ArrayList<String> wordList = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(res.getURI()), StandardCharsets.UTF_8);
            wordList = new ArrayList<>(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordList;
    }
}
