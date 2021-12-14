package de.hszg.umgebindehaus.backend.components;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class UniqueWordGenerator {
    private Integer runThrough = 0;
    private ArrayList<String> wordList;

    private final Resource res;


    public UniqueWordGenerator() {
        res = new ClassPathResource("wordlist.txt");
        wordList = readWordListFile();
    }

    public String nextWord() {
        if (wordList.size() == 0) {
            wordList = readWordListFile();
            runThrough++;
        }

        Random random = new Random();
        int number = random.nextInt(wordList.size());

        String word = wordList.get(number) + runThrough.toString();
        wordList.remove(number);

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
