package ru.mikle.cgm2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mikle.cgm2.model.CharacterInfo;
import ru.mikle.cgm2.repository.MapRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StringAnalyzeController {

    @Autowired
    MapRepository repository;

    @GetMapping("/analyze/{input}")
    public Map<Character, int[]> analyze(@PathVariable String input) {
        Map<Character, int[]> result = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int count = countChar(input, c);
            int maxSequence = maxSequence(input, c);
            int[] data = {count, maxSequence};
            result.put(c, data);
        }
        repository.save(result, input.length());
        return result;
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<Character, CharacterInfo>> getStatisticsData() {
        return ResponseEntity.ok(repository.getStatistic());
    }

    private int countChar(String input, char c) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    private int maxSequence(String input, char c) {
        int maxSequence = 0;
        int currentSequence = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == c) {
                currentSequence++;
                if (currentSequence > maxSequence) {
                    maxSequence = currentSequence;
                }
            } else {
                currentSequence = 0;
            }
        }
        return maxSequence;
    }
}
