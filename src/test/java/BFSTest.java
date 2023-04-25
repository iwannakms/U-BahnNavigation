import org.example.Algorithm.BFS;
import org.example.ReadFiles.Reader;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BFSTest {
    private BFS bfs;

    @Before
    public void setUp() {
        Map<String, LinkedList<String>> web = new HashMap<>();
        LinkedList<String> line1 = new LinkedList<>(List.of("Station1", "Station2", "Station3"));
        LinkedList<String> line2 = new LinkedList<>(List.of("Station4", "Station5", "Station6"));
        LinkedList<String> line3 = new LinkedList<>(List.of("Station7", "Station8", "Station9"));
        web.put("Line1", line1);
        web.put("Line2", line2);
        web.put("Line3", line3);
        bfs = new BFS(web);
    }

    @Test
    public void testFindAllNeighbors() {
        LinkedList<String> neighbors = bfs.findAllNeighbors("Station2");
        LinkedList<String> expectedNeighbors = new LinkedList<>(List.of("Station1", "Station3"));
        assertEquals(expectedNeighbors, neighbors);
    }

    @Test
    public void testFindNeighborsLines() {
        LinkedList<String> neighborsLines = bfs.findNeighborsLines("Novza");
        LinkedList<String> expectedNeighborsLines = new LinkedList<>();
        assertEquals(expectedNeighborsLines, neighborsLines);
    }

    @Test
    public void testAdd() {
        bfs.add(new LinkedList<>(List.of("Pushkin", "Novza")), new LinkedList<>(List.of("Chilonzor Line", "Uzbekistan Line")), "Kosmonavtlar", "Chilonzor Line");
        LinkedList<LinkedList<String>> expectedStations = new LinkedList<>();
        expectedStations.addLast(new LinkedList<>(List.of("Pushkin", "Novza", "Kosmonavtlar")));
        LinkedList<LinkedList<String>> expectedLines = new LinkedList<>();
        expectedLines.addLast(new LinkedList<>(List.of("Chilonzor Line", "Uzbekistan Line", "Chilonzor Line")));
        assertEquals(expectedStations, bfs.getStations());
        assertEquals(expectedLines, bfs.getLines());
    }

    @Test
    public void testStart() {
        Reader reader = new Reader();
        reader.fullWeb();
        Map<String, LinkedList<String>> web = reader.getAllStations();
        BFS bfs = new BFS(web);
        bfs.add(new LinkedList<>(List.of("Olmazar")), new LinkedList<>(List.of("Chilonzor Line.txt")), "Bunyodkor", "Chilonzor Line.txt");
        bfs.add(new LinkedList<>(List.of("Bunyodkor")), new LinkedList<>(List.of("Chilonzor Line.txt")), "Olmazar", "Chilonzor Line.txt");
        bfs.add(new LinkedList<>(List.of("Olmazar")), new LinkedList<>(List.of("Chilonzor Line.txt")), "Pushkin", "Chilonzor Line.txt");

        bfs.start("Novza", "Pushkin");

        LinkedList<String> expectedStations = new LinkedList<>(List.of("Olmazar", "Pushkin"));
        LinkedList<String> expectedLines = new LinkedList<>(List.of("Chilonzor Line.txt", "Chilonzor Line.txt", "Chilonzor Line.txt"));
        assertEquals(expectedStations, bfs.getFindStation());
        assertEquals(expectedLines, bfs.getFindLines());
    }

}