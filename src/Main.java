import java.util.Map;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for mst spreadsheet
        System.out.println("Enter full path to csv file or enter test1 (or test2) to use the test file");
        String pathToFile = scanner.nextLine().trim();
        if (pathToFile.equalsIgnoreCase("test1")){
            pathToFile = ExcelReader.testPathA;
        } else if (pathToFile.equalsIgnoreCase("test2")){
            pathToFile = ExcelReader.testPathB;
        }

        System.out.println("Enter full path to csv file or enter test1 (or test2) to use the test file");
        String pathToFile2 = scanner.nextLine().trim();
        if (pathToFile2.equalsIgnoreCase("test1")){
            pathToFile2 = ExcelReader.testPathA;
        } else if (pathToFile2.equalsIgnoreCase("test2")){
            pathToFile2 = ExcelReader.testPathB;
        }

        Map<String, Map<String, String>> geneMapA = ExcelReader.readExcelCsv(pathToFile, scanner);
        Map<String, Map<String, String>> geneMapB = ExcelReader.readExcelCsv(pathToFile2, scanner);

        

        // HashMap firstGeneMst = GeneMapsHandler.getMstFromUser("Enter the first file path", scanner);
        // HashMap secondGeneMst = GeneMapsHandler.getMstFromUser("Enter the second file path", scanner);

        // GeneMapsHandler.printComputedComparison(firstGeneMst, secondGeneMst);
        // GeneMapsHandler.printMatrix("First MST Matrix: ", firstGeneMst);
        // GeneMapsHandler.printMatrix("Second MST Matrix: ", secondGeneMst);

        // GeneMapsHandler.printAllToExcel();
    }
}
