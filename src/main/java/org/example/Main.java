package org.example;

import org.example.Algorithm.BFS;
import org.example.Display.ResultDisplay;
import org.example.Display.UserInput;
import org.example.ReadFiles.Reader;

import java.util.LinkedList;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Reader reader = new Reader();
        reader.fullWeb();
        Map<String, LinkedList<String>> graph = reader.getAllStations();

        BFS bfs = new BFS(graph);

        UserInput userInput = new UserInput();
        String startStation;
        String endStation;

        while (true) {
            startStation = userInput.getStartStation();
            endStation = userInput.getEndStation();
            if(startStation.equals(endStation)){
                System.out.println("Der Versandort kann nicht mit dem Ankunftsort übereinstimmen.\n" +
                                   "-----------------------------------------------------");
                continue;
            }

            try {
                bfs.start(startStation, endStation);
                ResultDisplay displayResult = new ResultDisplay(bfs.getFindStation(), bfs.getFindLines());
                displayResult.displayResult();
                break;
            } catch (Exception e) {
                System.out.println("""
                        Ein Fehler ist aufgetreten:\s
                        Bitte geben Sie die richtigen Daten ein und versuchen Sie es erneut.
                        -----------------------------------------------------""");
            }
        }
    }
}
