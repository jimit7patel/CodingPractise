package leetcode.epi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import leetcode.epi.test_framework.EpiTest;
import leetcode.epi.test_framework.GenericTest;
import leetcode.epi.test_framework.TestFailure;
import leetcode.epi.test_framework.TimedExecutor;

public class SudokuSolve {

    public static boolean solveSudoku(List<List<Integer>> partialAssignment) {
        // Implement this placeholder.
        return helper(partialAssignment, 0, 0);
    }

    private static boolean helper(List<List<Integer>> partialAssignment, int col, int row) {

        if (col == 9) {
            col = 0;
            if (++row == 9)
                return true;

        }
        if (partialAssignment.get(row).get(col) == 0) {
            for (int val = 1; val < 10; val++) {
                if (isValid(row, col, val, partialAssignment)) {
                    partialAssignment.get(row).set(col, val);
                    if (helper(partialAssignment, col + 1, row))
                        return true;
                }
            }
            partialAssignment.get(row).set(col, 0);
            return false;
        } else {
            if (helper(partialAssignment, col + 1, row))
                return true;
        }
        return false;
    }

    private static boolean isValid(int row, int col, int val, List<List<Integer>> partialAssignment) {

        // check if row is valid
        if (partialAssignment.get(row).contains(val))
            return false;

        // check if col is valid
        if (partialAssignment.stream().anyMatch(j -> j.get(col) == val))
            return false;

        // for (int j = 0; j < 9; j++) {
        //
        // if (partialAssignment.get(j).get(col) == val)
        // return false;
        //
        // }

        // check if valid sub matrix

        int rowSubIndex = row / 3;
        int colSubIndex = col / 3;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (partialAssignment.get(rowSubIndex * 3 + i).get(colSubIndex * 3 + j) == val) {

                    return false;
                }
            }
        }
        return true;
    }

    @EpiTest(testfile = "sudoku_solve.tsv")
    public static void solveSudokuWrapper(TimedExecutor executor,
            List<List<Integer>> partialAssignment)
                    throws Exception {
        List<List<Integer>> solved = new ArrayList<>();
        for (List<Integer> row : partialAssignment) {
            solved.add(new ArrayList<>(row));
        }

        executor.run(() -> solveSudoku(solved));

        if (partialAssignment.size() != solved.size()) {
            throw new TestFailure("Initial cell assignment has been changed");
        }

        for (int i = 0; i < partialAssignment.size(); i++) {
            List<Integer> br = partialAssignment.get(i);
            List<Integer> sr = solved.get(i);
            if (br.size() != sr.size()) {
                throw new TestFailure("Initial cell assignment has been changed");
            }
            for (int j = 0; j < br.size(); j++)
                if (br.get(j) != 0 && !Objects.equals(br.get(j), sr.get(j)))
                    throw new TestFailure("Initial cell assignment has been changed");
        }

        int blockSize = (int)Math.sqrt(solved.size());
        for (int i = 0; i < solved.size(); i++) {
            assertUniqueSeq(solved.get(i));
            assertUniqueSeq(gatherColumn(solved, i));
            assertUniqueSeq(gatherSquareBlock(solved, blockSize, i));
        }
    }

    private static void assertUniqueSeq(List<Integer> seq) throws TestFailure {
        Set<Integer> seen = new HashSet<>();
        for (Integer x : seq) {
            if (x == 0) {
                throw new TestFailure("Cell left uninitialized");
            }
            if (x < 0 || x > seq.size()) {
                throw new TestFailure("Cell value out of range");
            }
            if (seen.contains(x)) {
                throw new TestFailure("Duplicate value in section");
            }
            seen.add(x);
        }
    }

    private static List<Integer> gatherColumn(List<List<Integer>> data, int i) {
        List<Integer> result = new ArrayList<>();
        for (List<Integer> row : data) {
            result.add(row.get(i));
        }
        return result;
    }

    private static List<Integer> gatherSquareBlock(List<List<Integer>> data,
            int blockSize, int n) {
        List<Integer> result = new ArrayList<>();
        int blockX = n % blockSize;
        int blockY = n / blockSize;
        for (int i = blockX * blockSize; i < (blockX + 1) * blockSize; i++) {
            for (int j = blockY * blockSize; j < (blockY + 1) * blockSize; j++) {
                result.add(data.get(i).get(j));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
