package utils.util;

import org.apache.poi.ss.usermodel.*;
import utils.propertyManager.PropertyManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Utils {
    private static String environment_value;

    static {
        environment_value = PropertyManager.getInstance().environmentVariable();
    }

    private static Map<String, String> list_to_be_returned;
    private Workbook workBook;
    private Sheet workSheet;
    private String path;

    public Utils() throws IOException {
    }

    public static Map<String, String> fetch_env_variables(List<Map<String, String>> table_to_be_converted, String environment) {
        if (environment == null || environment.isEmpty()) {
            environment = environment_value;
        }

        if (environment.equals("dev")) {
            System.out.println(table_to_be_converted.get(0));
            list_to_be_returned = table_to_be_converted.get(0);
        } else if (environment.equals("preprod")) {
            System.out.println(table_to_be_converted.get(1));
            Map<String, String> list_to_be_returned = table_to_be_converted.get(1);
        }
        return list_to_be_returned;
    }

    public static String getDate() {
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return dateObj.format(formatDate);
    }

    public void ExcelUtil(String path, String sheetName) {//This Constructor is to open and access the excel file
        this.path = path;
        try {
            // Opening the Excel file
            FileInputStream fileInputStream = new FileInputStream(path);
            // accessing the workbook
            workBook = WorkbookFactory.create(fileInputStream);
            //getting the worksheet
            workSheet = workBook.getSheet(sheetName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //This will get the list of the data in the excel file
    //This is a list of map of string. This takes the data as string and will return the data as a Map of String
    public List<Map<String, String>> getDataList() {
        // getting all columns
        List<String> columns = getColumnsNames();
        // method will return this
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 1; i < rowCount(); i++) {
            // get each row
            Row row = workSheet.getRow(i);
            // creating map of the row using the column and value
            // key=column, value=cell
            Map<String, String> rowMap = new HashMap<String, String>();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.toString());
            }
            data.add(rowMap);
        }
        return data;
    }

    //===============Getting the number of columns in a specific single row=================
    public int columnCount() {
        //getting how many numbers in row 1
        return workSheet.getRow(0).getLastCellNum();
    }

    //===============how do you get the last row number?Index start at 0.====================
    public int rowCount() {
        return workSheet.getLastRowNum() + 1;
    }//adding 1 to get the actual count

    //==============When you enter row and column number, then you get the data==========
    public String getCellData(int rowNum, int colNum) {
        Cell cell;
        try {
            cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            return cellData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //============getting all data into two dimentional array and returning the data===
    public String[][] getDataArray() {
        String[][] data = new String[rowCount()][columnCount()];
        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i][j] = value;
            }
        }
        return data;
    }

    //==============going to the first row and reading each column one by one==================//
    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();
        for (Cell cell : workSheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    //=========When you enter the row and column number, returning the value===============//
    public void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;
        try {
            row = workSheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if (cell == null) {//if there is no value, create a cell.
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy"); // Formatı belirtiyoruz
        try {
            return dateFormat.parse(dateString); // String'i Date'e çeviriyoruz
        } catch (ParseException e) {
            System.err.println("Geçersiz tarih formatı: " + e.getMessage());
            return null; // Hata durumunda null dönebiliriz
        }
    }
//
//    public void setCellData(String value, String columnName, int row) {
//        int column = getColumnsNames().indexOf(columnName);
//        setCellData(value, row, column);
//    }
//
//    //this method will return data table as 2d array
//    //so we need this format because of data provider.
//    public String[][] getDataArrayWithoutFirstRow() {
//        String[][] data = new String[rowCount() - 1][columnCount()];
//        for (int i = 1; i < rowCount(); i++) {
//            for (int j = 0; j < columnCount(); j++) {
//                String value = getCellData(i, j);
//                data[i - 1][j] = value;
//            }
//        }
//        return data;
//    }
//
//
//    //exceldeki datayı mape dönüştürür
//    public static Map<String, String> mapOlustur(String path, String sayfaAdi) {
//        Map<String, String> excelMap = new HashMap();
//
//        Workbook workbook = null;
//        try {
//            FileInputStream fis = new FileInputStream(path);
//            workbook = WorkbookFactory.create(fis);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int satirSayisi = workbook.getSheet(sayfaAdi).getLastRowNum();
//
//        String key = "";
//        String value = "";
//        for (int i = 0; i <= satirSayisi; i++) {
//            //ikinci adim tablodaki hucreleri mapa uygun hale donusturmek
//            key = workbook.getSheet(sayfaAdi).getRow(i).getCell(0).toString();
//            value = workbook.getSheet(sayfaAdi).getRow(i).getCell(1) + ", " +
//                    workbook.getSheet(sayfaAdi).getRow(i).getCell(2) + ", " +
//                    workbook.getSheet(sayfaAdi).getRow(i).getCell(3).toString();
//            //key value haline getirdigimiiz satirlari mapa ekliyoruz
//            excelMap.put(key, value);
//        }
//
//        return excelMap;
//    }
//
//    //This method will take two parameter: WebElement, and WebDriver
//    //When you pass the element, JS will click on that element
//    public static void clickElementByJS(WebElement element) {
//        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
//        jsexecutor.executeScript("arguments[0].click();", element);
//    }
//
//    //to get the page title with JS
//    public static String getTitleByJS() {
//        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
//        String title = jsexecutor.executeScript("return document.title;").toString();
//        return title;
//    }
//
//    public static void scroolToLeft() {
//        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
//        Long currentScrollPosition = (Long) js.executeScript("return window.scrollX");
//        System.out.println("currentScrollPosition = " + currentScrollPosition);
//        js.executeScript("window.scrollTo(" + (currentScrollPosition - 700) + ", 0)");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    //Scroll into view with JS. THIS IS VERY USEFULL
//    public static void scrollIntoVIewJS() {
//        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
//        jsexecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
//        //    jsexecutor.executeScript("window.scrollBy(0,250)", "");
//    }
//
//    public static void changeColor(String color, WebElement element) {
//        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) Driver.getDriver());
//        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //Flashing teh background color
//    public static void flash(WebElement element) {
//        String bgColor = element.getCssValue("backgroundcolor");
//        for (int i = 0; i < 10; i++) {
//            changeColor("rgb(0,200,0", element);
//            changeColor(bgColor, element);
//        }
//    }
//
//    //this willg enerate an alert when needed
//    public static void generateAlert(String message) throws InterruptedException {
//        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) Driver.getDriver());
//        javascriptExecutor.executeScript("alert('" + message + "')");
//        Thread.sleep(3000);
//    }
//
//    /*
//     * executes the given JavaScript command on given web element
//     */
//    public static void executeJScommand(WebElement element, String command) {
//        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
//        jse.executeScript(command, element);
//    }
//
//    /*
//     * executes the given JavaScript command on given web element
//     */
//    public static void executeJScommand(String command) {
//        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
//        jse.executeScript(command);
//    }
//
//    public static void scrollIntoView(WebElement element) {
//        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
//        jse.executeScript("arguments[0].scrollIntoView()", element);
//        jse.executeScript("arguments[0].click();", element);
//    }
//
//    public static List<String> getSSNIDs() {
//        List<String> list = new ArrayList<>();
//
//
//        try {
//
//            //identify file location
//            FileReader fileReader = new FileReader(ConfigReader.getProperty("api_registrant_data"));
//
//            //Read the records of the file in given location
//            BufferedReader br = new BufferedReader(fileReader);
//
//            String line = br.readLine();
////Registrant{firstName='patient', lastName='Endtoend', ssn='603-42-3689', login='patientEndtoend274', langKey='null', password='Endtoend603-42-3689', email='debera.kirlin@hotmail.com'}
//
//
//            while (line != null) {
//
//                String ssn = line.split(",")[2];
//                String ssnNumber = ssn.substring(6, 17);
//                list.add(ssnNumber);
//
//                line = br.readLine();
//
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//        return list;
//    }
//
//
//    public static List<String> getTestItemIDs() {
//        List<String> list = new ArrayList<>();
//
//
//        try {
//
//            //identify file location
//            FileReader fileReader = new FileReader(ConfigReader.getProperty("database_test_items_data"));
//
//            //Read the records of the file in given location
//            BufferedReader br = new BufferedReader(fileReader);
//
//            String line = br.readLine();//856-45-6789,
//
//            while (line != null) {
//
//                String id = line.split(",")[0];
//                list.add(id);
//
//                line = br.readLine();
//
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//        return list;
//    }
}
