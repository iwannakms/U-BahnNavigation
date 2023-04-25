package org.example.Display;

import java.util.LinkedList;

/**
    This class represents the result display for the path finding algorithm
 */
public class ResultDisplay {
    private LinkedList<String> findStation; // Stores the list of station names in the path
    private LinkedList<String> findLines; // Stores the list of line names in the path

    /**
     Constructor to create a new ResultDisplay object
     @param findStation the list of station names in the path
     @param findLines the list of line names in the path
     */
    public ResultDisplay(LinkedList<String> findStation, LinkedList<String> findLines) {
        this.findStation = findStation;
        this.findLines = findLines;
    }

    /**
     Method to display the path result
     */
    public void displayResult() {
        StringBuilder result = new StringBuilder();             // StringBuilder to construct the result message
        String curLine = findLines.getFirst();                  // Stores the first line name in the path
        for (int i = 0; i < findStation.size(); i++) {          // Loop through each station in the path
            result.append(findStation.get(i)).append(" (");     // Append the current station name to the result message
            if (!curLine.equals(findLines.get(i + 1))) {        // If the current line name is different from the next one
                result.append("Change to ");                    // Append a message indicating that a line change is needed
            }
            result.append(findLines.get(i + 1)).append(")\n");  // Append the next line name to the result message
            curLine = findLines.get(i + 1);                     // Update the current line name
        }
        System.out.println("\nAusgabe: \n\n"+result);           // Print the result message to the console
    }
}
