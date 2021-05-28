package engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class JobAssignmentTest {
	
	EasyJobAssignment jobAssignment = new EasyJobAssignment();

    @Test
    public void emptyArray_shouldReturn_emptyArrayList() {

        int[] jobs = new int[0];

        Map<Integer, ArrayList<Integer>> result = jobAssignment.assignJobs(jobs, true);

        assertTrue(result.isEmpty());
    }


    @Test
    public void nullInput_shouldReturn_emptyArrayList() {
        Map<Integer, ArrayList<Integer>> result = jobAssignment.assignJobs(null, true);

        assertTrue(result.isEmpty());
    }

    @Test
    public void validInput_shouldReturn_optimumWorkerJobMap() {

        int[] jobs = {12, 10, 5, 5};

        Map<Integer, ArrayList<Integer>> result = jobAssignment.assignJobs(jobs, true);
        Map<Integer, ArrayList<Integer>> expectedResult = new HashMap<>();
        expectedResult.put(1, new ArrayList<>(Arrays.asList(5, 5)));
        expectedResult.put(2, new ArrayList<>(Arrays.asList(10)));
        expectedResult.put(3, new ArrayList<>(Arrays.asList(12)));

        assertEquals(expectedResult, result);

    }

    @Test
    public void descendingValidInput_shouldReturn_desiredOptimumWorkerJobMap(){
        int[] jobs = {12, 10, 5, 5};

        Map<Integer, ArrayList<Integer>> result = jobAssignment.assignJobs(jobs, false);
        Map<Integer, ArrayList<Integer>> expectedResult = new HashMap<>();
        expectedResult.put(1, new ArrayList<>(Arrays.asList(5, 5)));
        expectedResult.put(2, new ArrayList<>(Arrays.asList(10)));
        expectedResult.put(3, new ArrayList<>(Arrays.asList(12)));

        assertEquals(expectedResult, result);
    }


    @Test
    public void ascendingSorted13Input_shouldReturn_optimumWorkerJobMap() {
    	
        int[] jobs = {234, 250, 400, 525, 545, 120, 120, 100, 699, 121, 120, 50, 302};

        Map<Integer, ArrayList<Integer>> result = jobAssignment.assignJobs(jobs, true);
        Map<Integer, ArrayList<Integer>> expectedResult = new HashMap<>();
        expectedResult.put(1, new ArrayList<>(Arrays.asList(5, 5)));
        expectedResult.put(2, new ArrayList<>(Arrays.asList(10)));
        expectedResult.put(3, new ArrayList<>(Arrays.asList(12)));

        assertEquals(expectedResult, result);

    }

    @Test
    public void descendingSorted13Input_shouldReturn_optimumWorkerJobMap(){

        int[] jobs = {234, 250, 400, 525, 545, 120, 120, 100, 699, 121, 120, 50, 302};

        Map<Integer, ArrayList<Integer>> result = jobAssignment.assignJobs(jobs, false);
        Map<Integer, ArrayList<Integer>> expectedResult = new HashMap<>();
        expectedResult.put(1, new ArrayList<>(Arrays.asList(5, 5)));
        expectedResult.put(2, new ArrayList<>(Arrays.asList(10)));
        expectedResult.put(3, new ArrayList<>(Arrays.asList(12)));

        assertEquals(expectedResult, result);

    }

}
