package org.example.ReadFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Reader {

    /**
     * Constants for file path and extension
     */
    private static final String LINES_DIRECTORY = "src/main/java/org/example/ReadFiles/Lines";
    private static final String TXT_EXTENSION = ".txt";
    private LinkedList<String> lineNames;
    private Map<String, LinkedList<String>> allStations;

    /**
     * Getter method for lineNames field.
     * @return - linked list of all line names.
     */
    public LinkedList<String> getLineNames() {
        return lineNames;
    }

    /**
     * Find all text files in a given directory and store their names in a linked list.
     *
     * @param path path of directory to search
     * @throws FileNotFoundException if directory is not found
     * @throws NotDirectoryException if given path is not a directory
     */
    public void findLines(String path) throws FileNotFoundException, NotDirectoryException {
        File direction = new File(path);
        if (!direction.exists()) {
            throw new FileNotFoundException("Directory not found: " + path);
        }
        if (!direction.isDirectory()) {
            throw new NotDirectoryException("Expected a directory, but found a file: " + path);
        }
        lineNames = new LinkedList<>();
        for (File file : direction.listFiles()) {
            if (file.getName().endsWith(TXT_EXTENSION)) {
                lineNames.add(file.getName().replace(TXT_EXTENSION, ""));
            }
        }
    }

    /**
     * Read all stations from a text file and store them in a linked list.
     *
     * @param lineName name of the line file to read
     * @return a linked list of stations on the given line
     */
    private LinkedList<String> findStations(String lineName) {
        LinkedList<String> stations = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LINES_DIRECTORY + "/" + lineName + TXT_EXTENSION))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stations.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stations;
    }

    /**
     * Find all lines and stations in the directory and create a map of line names to station lists.
     */
    public void fullWeb() {
        try {
            findLines(LINES_DIRECTORY);
        } catch (FileNotFoundException | NotDirectoryException e) {
            throw new RuntimeException(e);
        }
        allStations = new HashMap<>();
        lineNames.forEach(lineName -> allStations.put(lineName, findStations(lineName)));
    }

    /**
     * Get a map of all stations on all lines.
     *
     * @return a map of line names to station lists
     */
    public Map<String, LinkedList<String>> getAllStations() {
        return allStations;
    }
}
