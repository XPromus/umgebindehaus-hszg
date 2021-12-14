package de.hszg.umgebindehaus.backend.components;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UniqueWordGenerator {

    private final ArrayList<Pair<String, AtomicInteger>> wordList;

    public UniqueWordGenerator() {
        var res = new ClassPathResource("wordlist.txt");
        wordList = readWordListFile(res);
    }

    public String nextWord() {
        Random random = new Random();
        int number = random.nextInt(wordList.size());

        Pair<String, AtomicInteger> pair = wordList.get(number);

        return pair.getFirst() + "-" + pair.getSecond().getAndIncrement();
    }

    private ArrayList<Pair<String, AtomicInteger>> readWordListFile(Resource res) {
        ArrayList<Pair<String, AtomicInteger>> wordList = new ArrayList<>();

        try(var reader = new BufferedReader(new InputStreamReader(res.getInputStream()))){
            String line;
            while((line = reader.readLine()) != null){
                wordList.add(Pair.of(line, new AtomicInteger(1)));
            }
        } catch (IOException e) {
            throw new AssertionError(new RuntimeException("unable to read WordList (this should not happen)", e));
        }

        return wordList;
    }
}
