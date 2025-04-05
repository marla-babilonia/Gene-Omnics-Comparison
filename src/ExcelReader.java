/* Reads the files assuming the files are .csv
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
public class ExcelReader {
    //pre-made excel file just for checking the code and making my life easier
    //can be changed as needed

    //hardcoded test files path to make my life easier (change it to make your life easier as well)
    public static final String testPathA = "C:/Users/Something/AnotherThing/testFileA.csv";
    public static final String testPathB = "C:/Users/Something/AnotherThing/testFileB.csv";


    /* ValidatePath method to validate that the user entered the correct file if not it will prompt the user 
        to enter a path until it is valid
        to be valid it must:
            1. have at least three columns 
            2. have at least two rows 
            3. end in .csv 
            4. check if the file exist duh! (almost forgot it)
            
        if the user enters exit it will return null */

    public static String validatePath(String path, Scanner scanner){

        while (true){
            //update file name
            File file = new File(path);

            if (!file.exists() || !path.endsWith(".csv")){
                System.out.println("File does not exist or does not end in .csv");
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                    int rowCount = 0;
                    String line;

                    while ((line = reader.readLine()) != null){
                        String[] parts = line.split(",");
                        if (parts.length != 3){
                            System.out.println("Your file does not have three columns");
                            break;
                        }
                        rowCount++;
                    }

                    if (rowCount < 2){
                        System.out.println("File must have a header and at least one additional row");
                    } else {
                        return path; //cause it's valid
                    }
                } catch (IOException e){
                    System.out.println("Could not validate file: " + e.getMessage());
                }
            }

            System.out.println("Enter a new path, or type \" exit \" to quit");
            path = scanner.nextLine().trim();
            if (path.equalsIgnoreCase("exit")){
                return null;
            }

        }
    }
    
    /* ReadExcelCsv Method to read a gene map and process it into a tree map (so it's alphabetically ordered and contains no duplicates:))
     * Each map contains a key being the gene and a value being another tree map with the genes it cnnects to as a key
     * and the respective weight of the connection as a value.
     * 
     * It is also reciprocal meaning it will store geneA with connection to geneB and geneB with connection
     * to gene A
     * 
     * for example if the file contains:
     * geneA, geneB, 0.25
     * geneC, geneA, 0.30
     * it generates:
     * {"GeneA" : {"GeneB" : 0.25, "geneC : 0.30"}}, "GeneB" : {"GeneA" : 0.25}, "GeneC" : {"GeneA" : 0.30}}
     */
    public static Map<String, Map<String, String>> readExcelCsv(String path, Scanner scanner){

        String validPath = validatePath (path, scanner);

        if (validPath == null){
            System.out.println("User chose to exit. No file was read.");
            return null;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(validPath))){
            Map<String, Map <String, String>> geneMap = new TreeMap<>();
            String line;
            int rowCount = 0;

            while ((line = reader.readLine()) != null){
                String[] columns = line.split(",");
                if (columns.length != 3){
                    continue;
                }

                if (rowCount == 0){
                    rowCount ++;
                    continue;
                }

                String gene1 = columns[0].trim();
                String gene2 = columns[1].trim();
                String weight = columns[2].trim();

                geneMap.putIfAbsent(gene1, new TreeMap<>());
                geneMap.get(gene1).put(gene1, weight);

                geneMap.putIfAbsent(gene2, new TreeMap<>());
                geneMap.get(gene2).put(gene1, weight);

                rowCount++;

            }
            return geneMap;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    
}
