package AutoFramework.Utilities;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ReadFromXml {

    public static List<String> readFromXmlFile(String path, String email) throws IOException {

        List<String> result =  new ArrayList<>();
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);

        //Creating a Sheet object using the sheet Name
        XSSFSheet sheet = wb.getSheetAt(0);

        int rows= sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        Iterator<Row> it =sheet.iterator();
        while (it.hasNext()){
            Row row= it.next();
            if (row.getCell(0).getStringCellValue().equals(email));
            {
                for (int i = 1; i < cols; i++) {
                    try  {
                        result.add(row.getCell(i).getStringCellValue());
                    }catch (RuntimeException e ){
                        double num =  row.getCell(i).getNumericCellValue();
                        result.add(String.valueOf(num));
                    }

                }
            }

        }
        if (result.size()==0){
            throw new InvalidObjectException("Personal details are null");
        }

        return  result;
    }

    public static Collection readXml(String path) throws IOException {
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        // OPCPackage pkg = OPCPackage.open(new File(XML_PATH));
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);

        //Creating a Sheet object using the sheet Name
        XSSFSheet sheet = wb.getSheetAt(0);


        int rows= sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        String[][] obg = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                obg[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
            }
        }

        return Arrays.asList(obg.clone());

    }
}
