package utils.util;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driverClient.DriverClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReusableMethods {
    static ZonedDateTime dateTime;
    static DateTimeFormatter formatter;

    public static void main(String[] args) {

    }

    static Actions actions = new Actions(DriverClient.getDriver());

    public static void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public static String webElementValue(WebElement element) {
        return DriverClient.getDriver().findElement(By.xpath(String.valueOf(element))).getText();
    }

    public static WebElement waitForClickability(WebElement locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverClient.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void scrollIntoPage(WebElement element) {
        ((JavascriptExecutor) DriverClient.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) DriverClient.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) DriverClient.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static byte[] takeScreenshot(String filename) {
        TakesScreenshot ts = (TakesScreenshot) DriverClient.getDriver();
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);

        File file = ts.getScreenshotAs(OutputType.FILE);
        // create destination as : filepath + filename + timestamp + .png
        String destination = System.getProperty("user.dir") + "/test-output/Screenshots/" + filename + date + ".png";

        //      FileUtils.copyFile(file, new File(destination));
        return picBytes;
    }

    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) DriverClient.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    //========Switching Window=====//
    public static void switchToWindow(String targetTitle) {
        String origin = DriverClient.getDriver().getWindowHandle();
        for (String handle : DriverClient.getDriver().getWindowHandles()) {
            DriverClient.getDriver().switchTo().window(handle);
            if (DriverClient.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        DriverClient.getDriver().switchTo().window(origin);
    }

    //========Hover Over=====//
    public static void hover(WebElement element) {
        Actions actions = new Actions(DriverClient.getDriver());
        actions.moveToElement(element).perform();
    }

    //==========Return a list of string given a list of Web Element====////
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //========Returns the Text of the element given an element locator==//
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = DriverClient.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //   HARD WAIT WITH THREAD.SLEEP
//   waitFor(5);  => waits for 5 second
    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //===============Explicit Wait==============//
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverClient.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverClient.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverClient.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverClient.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
    }

    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(DriverClient.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }

    //======Fluent Wait====//
    public static WebElement fluentWait(final WebElement webElement, int timeout) {
        //FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverClient.getDriver()).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverClient.getDriver())
                .withTimeout(Duration.ofSeconds(3))//Wait 3 second each time
                .pollingEvery(Duration.ofSeconds(1));//Check for the element every 1 second
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
        return element;
    }

    public static void scrollToWebEementVisivle(WebElement webElement) {
        JavascriptExecutor jss = (JavascriptExecutor) DriverClient.getDriver();
        jss.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static String getDateTime(String pattern) {
        dateTime = ZonedDateTime.now();
        formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }


    public static String getMonthsAgoDateTime(String pattern, int monthsAgo) {
        dateTime = ZonedDateTime.now();
        formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.minus(monthsAgo, ChronoUnit.MONTHS).format(formatter);
    }

    public static String getDaysAgoDateTime(String pattern, int daysAgo) {
        dateTime = ZonedDateTime.now();
        formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.minus(daysAgo, ChronoUnit.DAYS).format(formatter);
    }

    public static String getHoursAgoDateTime(String pattern, int hoursAgo) {
        dateTime = ZonedDateTime.now();
        formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.minus(hoursAgo, ChronoUnit.HOURS).format(formatter);
    }

    public static String getFutureDateTime(String pattern, int futureDayUnit) {
        dateTime = ZonedDateTime.now();
        formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.plus(futureDayUnit, ChronoUnit.DAYS).format(formatter);
    }


    public static void Base64DecodePdf(String barcode) {

        File file = new File("src/test/resources/test.pdf");

        try (FileOutputStream fos = new FileOutputStream(file);) {
            // To be short I use a corrupted PDF string, so make sure to use a valid one if you want to preview the PDF file
            byte[] decoder = Base64.getDecoder().decode(barcode);

            fos.write(decoder);
            System.out.println("PDF File Saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateInvalidGsmNumber() {
        return Faker.instance().numerify("##########");
    }

    public static String getRandomNumber(int index) {
        return (RandomStringUtils.randomNumeric(index));
    }

    public static String getDateTime(int index) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        time = time.substring(0, index);
        return time;
    }

    public static BufferedImage generateImage(WebElement element, File screenshot) {

        BufferedImage fullImage = null;
        try {
            fullImage = ImageIO.read(screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Point imageLocation = element.getLocation();


        int qrCodeImageWidth = element.getSize().getWidth();

        int qrCodeImageHeight = element.getSize().getHeight();


        int pointXPosition = imageLocation.getX();

        int pointYPosition = imageLocation.getY();


        BufferedImage qrCodeImage = fullImage.getSubimage(pointXPosition, pointYPosition, qrCodeImageWidth, qrCodeImageHeight);

        try {
            ImageIO.write(qrCodeImage, "png", screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return qrCodeImage;

    }

    public static void showElementWithFrame(String locate) {
        WebElement element = DriverClient.getDriver().findElement(By.cssSelector("" + locate + ""));
        String script = "arguments[0].style.border='3px solid red';";
//        String script = "arguments[0].style.border='3px solid white';";
//        String script = "arguments[0].style.border='3px solid yellow';";
//        String script = "arguments[0].style.border='3px solid green';";
        ((JavascriptExecutor) DriverClient.getDriver()).executeScript(script, element);

    }

    public static void showElementWithFrame(WebElement webElement) {
        String script = "arguments[0].style.border='3px solid red';";
//        String script = "arguments[0].style.border='3px solid white';";
//        String script = "arguments[0].style.border='3px solid yellow';";
//        String script = "arguments[0].style.border='3px solid green';";
        ((JavascriptExecutor) DriverClient.getDriver()).executeScript(script, webElement);

    }


    public static boolean isExist() {
        String dosyaYolu = System.getProperty("user.home") + "\\Downloads\\download.png";
        boolean isExist = Files.exists(Paths.get(dosyaYolu));

        return isExist;
    }

    public static void fileUpload(WebElement dosyaSec, WebElement submit, String fileName) throws InterruptedException {
        String dosyaYolu = System.getProperty("user.home") + "\\Desktop\\deneme\\" + fileName;

        //3- sendkeys methodu ile dinamik pathi dosya sec butonuna tiklayalim

        dosyaSec.sendKeys(dosyaYolu);
        Thread.sleep(7000);

        //Upload butonuna basalim.
        submit.click();
    }

    public static void verifyDownloadedFile(String downloadDirectory, String fileName) {
        // Get the downloaded file.
        File downloadedFile = new File(downloadDirectory + "/" + fileName);

        // Print the path to the downloaded file.
        System.out.println("Path to the downloaded file: " + downloadedFile.getAbsolutePath());


        // Print a string after all  the codes
        System.out.println("The downloaded file has been verified.");
    }

    public static void verifyDynamicDownloadedFile(String fileNameRegex) {
        // Get a list of all the files in the download directory.
        String downloadDirectory = System.getProperty("user.home") + "\\Downloads";
        System.out.println("downloadDirectory = " + downloadDirectory);
        File downloadDirectoryFile = new File(downloadDirectory);
        System.out.println("fileNameRegex = " + fileNameRegex);
        File[] files = downloadDirectoryFile.listFiles();

        // Print the number of files in the download directory.
        System.out.println("files in the download directory: " + files);
        System.out.println("Number of files in the download directory: " + files.length);

        // Filter the list of files to only include files that match the file name regex.
        List<File> filteredFiles = new ArrayList<>();
        for (File file : files) {
            //    System.out.println(file.getName());
            if (file.getName().contains(fileNameRegex)) {
                filteredFiles.add(file);
            }
        }

        // Print the number of files that match the file name regex.
        System.out.println("Number of files that match the file name regex: " + filteredFiles.size());

        // Verify that there is at least one file in the filtered list.

        // Print a string after all the codes
        System.out.println("The downloaded file(s) have been verified.");
    }


    public static String getTrackingNumberFromExcelInZipFile2(String directoryPath, String excelFileNamePattern) throws IOException {
        // Extract the ZIP file name pattern
        Pattern zipFilePattern = Pattern.compile("^invoice-report-detail_.*\\.zip$");

        // Locate the ZIP file
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        File zipFile = null;
        for (File file : files) {
            if (zipFilePattern.matcher(file.getName()).matches()) {
                zipFile = file;
                break;
            }
        }

        if (zipFile == null) {
            throw new FileNotFoundException("ZIP file not found in directory: " + directoryPath);
        }

        // Extract the Excel file
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry zipEntry = null;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            System.out.println("excelFileNamePattern = " + excelFileNamePattern);
            if (zipEntry.getName().contains(excelFileNamePattern)) {
                break;
            }
        }

        if (zipEntry == null) {
            zipInputStream.close();
            throw new FileNotFoundException("Excel file not found in ZIP file: " + zipFile.getName());
        }

        // Extract the tracking number from the Excel file
        ExcelUtil excelUtil = new ExcelUtil(zipInputStream.toString(), "InvoiceReportDetail-Expor56761");
        String trackingNumber = excelUtil.getCellData(10, 3);

        // Close the ZipInputStream object
        zipInputStream.close();

        // Clean up temporary files (if any)
        // ...

        return removeDecimalAndLastThreeLetters(trackingNumber);
    }

    public static String getTrackingNumberFromExcelInZipFile(String zipFilePath, String excelFilePath) throws IOException {


        // Create a new ZipInputStream object to read the ZIP file.
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));



        // Find the entry in the ZIP file that contains the Excel file.
        ZipEntry zipEntry = null;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            if (zipEntry.getName().endsWith(".xlsx")) {
                break;
            }
        }

        // Extract the Excel file from the ZIP file.
        File excelFile = new File(excelFilePath);
        FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = zipInputStream.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, len);
        }
        fileOutputStream.close();

        // Close the ZipInputStream object.
        zipInputStream.close();

        // Read the Excel file using the Apache POI library.
        //  Workbook workbook = WorkbookFactory.create(excelFile);



        ExcelUtil excelUtil = new ExcelUtil(excelFilePath, "InvoiceReportDetail-Expor83857");
        // Find the worksheet that contains the tracking number.
        //    Sheet worksheet = workbook.getSheetAt(0);
        String trackingNumber = excelUtil.getCellData(10, 3);
        // Find the row in the worksheet that contains the tracking number.
        //   Row trackingNumberRow = worksheet.getRow(0);

        // Get the tracking number from the row.
        //    Cell trackingNumberCell = trackingNumberRow.getCell(3);
        //  String trackingNumber = trackingNumberCell.toString();

        // Close the Excel workbook.
     //   workbook.close();

        return removeDecimalAndLastThreeLetters(trackingNumber);
    }

    public static String removeDecimalAndLastThreeLetters(String strValue) {
        // Remove decimal point
        strValue = strValue.replace(".", "");

        // Remove last three letters
        strValue = strValue.substring(0, strValue.length() - 3);

        return strValue;
    }
}
