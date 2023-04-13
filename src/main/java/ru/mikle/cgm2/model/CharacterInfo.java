package ru.mikle.cgm2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CharacterInfo {

    @JsonIgnore
    private final Character character;
    @JsonProperty("количество_запросов")
    private int requestCount;
    @JsonIgnore
    private int totalCharsAtAllRequests;
    @JsonIgnore
    private int maxSequence;

    public CharacterInfo(Character character, int requestLength, int maxSequence) {
        this.character = character;
        requestCount = 1;
        this.totalCharsAtAllRequests = requestLength;
        this.maxSequence = maxSequence;

    }

    public void incrementTotalRequest() {
        requestCount++;
    }

    public void addTotalCharsAtAllRequests(int requestLength) {
        totalCharsAtAllRequests += requestLength;
    }

    public void checkAndSetMaxSequence(int maxRequestSequence) {
        if (maxSequence < maxRequestSequence) {
            maxSequence = maxRequestSequence;
        }
    }


    public Character getCharacter() {
        return character;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public int getTotalCharsAtAllRequests() {
        return totalCharsAtAllRequests;
    }

    public int getMaxSequence() {
        return maxSequence;
    }

    @JsonProperty("cреднее_количество_символов_в_строке")
    public double getAverageCharsInRequest() {
        return requestCount > 0 ? (double) totalCharsAtAllRequests / requestCount : 0;
    }

    @JsonProperty("cредняя_длинна_максимальной_непрерывной_последовательности")
    public double getAverageMaxSequenseInRequest() {
        return requestCount > 0 ? (double) maxSequence / requestCount : 0;
    }
}
