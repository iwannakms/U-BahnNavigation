/**
 BFS class implements the breadth-first search algorithm to find the shortest path between two stations in a metro system.
 The class uses a graph represented by a Map with station names as keys and linked lists of adjacent stations as values.
 The class also uses linked lists to store the path from the starting station to the ending station.
 */

package org.example.Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BFS {
    private Map<String, LinkedList<String>> graph; // map of stations and their connected stations
    private LinkedList<LinkedList<String>> stations; // list of all stations
    private LinkedList<LinkedList<String>> lines; // list of all lines
    private List<String> visited; // list of visited stations during a search
    private LinkedList<String> findStation; // list of stations found during a search
    private LinkedList<String> findLines; // list of lines found during a search

    /**
     * Constructor for BFS class.
     * @param web - the graph representation of the metro system.
     */
    public BFS(Map<String, LinkedList<String>> web) {
        this.graph = web;
        stations = new LinkedList<>();
        lines = new LinkedList<>();
        visited = new ArrayList<>();
    }

    /**
     * Getter method for findStation field.
     * @return - linked list of stations in the shortest path.
     */
    public LinkedList<String> getFindStation() {
        return findStation;
    }

    /**
     * Getter method for findLines field.
     * @return - linked list of lines in the shortest path.
     */
    public LinkedList<String> getFindLines() {
        return findLines;
    }

    /**
     * Getter method for stations field.
     * @return - linked list of all visited stations.
     */
    public LinkedList<LinkedList<String>> getStations() {
        return stations;
    }

    /**
     * Getter method for lines field.
     * @return - linked list of all visited lines.
     */
    public LinkedList<LinkedList<String>> getLines() {
        return lines;
    }

    /**
     * Finds all the adjacent stations to a given station.
     * @param stationName - the name of the station to find adjacent stations for.
     * @return - linked list of adjacent stations.
     */
    public LinkedList<String> findAllNeighbors(String stationName) {
        LinkedList<String> currentLine;
        LinkedList<String> neighbors = new LinkedList<>();
        for (String key : graph.keySet()) {
            currentLine = graph.get(key);
            if (currentLine.contains(stationName)) {
                if (currentLine.indexOf(stationName) - 1 >= 0) {
                    neighbors.addLast(currentLine.get(currentLine.indexOf(stationName) - 1));
                }
                if (currentLine.indexOf(stationName) + 1 < currentLine.size()) {
                    neighbors.addLast(currentLine.get(currentLine.indexOf(stationName) + 1));
                }
            }
        }
        return neighbors;
    }

    /**
     * Finds all the lines adjacent to a given station.
     * @param stationName - the name of the station to find adjacent lines for.
     * @return - linked list of adjacent lines.
     */
    public LinkedList<String> findNeighborsLines(String stationName) {
        LinkedList<String> currentLine;
        LinkedList<String> neighbors = new LinkedList<>();
        for (String key : graph.keySet()) {
            currentLine = graph.get(key);
            if (currentLine.contains(stationName)) {
                if (currentLine.indexOf(stationName) - 1 >= 0) {
                    neighbors.addLast(key);
                }
                if (currentLine.indexOf(stationName) + 1 < currentLine.size()) {
                    neighbors.addLast(key);
                }
            }
        }
        return neighbors;
    }

    /**
     * Adds a new station to the list of visited stations.
     * @param station - the list of stations visited so far.
     * @param line - the list of lines traveled so far.
     * @param newStation - the new station to be added to the visited list.
     * @param newLine - the new line traveled to reach the new station.
     */
    public void add(LinkedList<String> station, LinkedList<String> line, String newStation, String newLine) {
        LinkedList<String> currentStations = new LinkedList<>(station);
        LinkedList<String> currentLines = new LinkedList<>(line);

        currentStations.addLast(newStation);
        currentLines.addLast(newLine);

        stations.addLast(currentStations);
        lines.addLast(currentLines);
        visited.add(newStation);
    }

    /**

     Starting point of the BFS algorithm to find the shortest path between start and end stations.
     @param startStation - the starting station.
     @param endStation - the destination station.
     */
    public void start(String startStation, String endStation) {
        boolean isChanged = true;
        LinkedList<String> neighbors = findAllNeighbors(startStation);
        LinkedList<String> neighborsLines = findNeighborsLines(startStation);
        for (int i = 0; i < neighbors.size(); i++) {
            add(new LinkedList<>(List.of(startStation)), new LinkedList<>(List.of(neighborsLines.get(i))), neighbors.get(i), neighborsLines.get(i));
        }
        while (isChanged) {
            isChanged = false;
            for (int i = 0; i < stations.size(); i++) {
                if (stations.get(i).getLast().equals(endStation)) {
                    findStation = stations.get(i);
                    findLines = lines.get(i);
                    findLines.addLast(findLines.getLast());
                    return;
                } else {
                    neighbors = findAllNeighbors(stations.get(i).getLast());
                    neighborsLines = findNeighborsLines(stations.get(i).getLast());
                    for (int j = 0; j < neighbors.size(); j++) {
                        if (!visited.contains(neighbors.get(j))) {
                            add(stations.get(i), lines.get(i), neighbors.get(j), neighborsLines.get(j));
                            isChanged = true;
                        }
                    }
                }
            }
        }
    }
}