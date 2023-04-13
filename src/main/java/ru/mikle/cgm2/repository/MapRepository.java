package ru.mikle.cgm2.repository;

import org.springframework.stereotype.Repository;
import ru.mikle.cgm2.model.CharacterInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapRepository {
    private final Map<Character, CharacterInfo> statisticsData = new ConcurrentHashMap<>();

    public void save(Map<Character, int[]> result, int requestLength) {
        result.keySet().forEach(c -> statisticsData.merge(c, new CharacterInfo(c, requestLength, result.get(c)[1]), (characterInfo, characterInfo2) -> {
            characterInfo.incrementTotalRequest();
            characterInfo.addTotalCharsAtAllRequests(requestLength);
            characterInfo.checkAndSetMaxSequence(result.get(c)[1]);
            return characterInfo;
        }));
    }

    public Map<Character, CharacterInfo> getStatistic() {
        return statisticsData;
    }
}
