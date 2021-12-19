package com.belovaoa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.FileContentReaderClass.FileContentReader1.readDocxFile;
import static helpers.FileContentReaderClass.FileContentReader1.readFileContent;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Тесты на файлы")
public class HomeworkFilesTests {
    @Test
    @DisplayName("Скачивание текстового файла и проверка его содержимого")
    void downloadTextFileTest() throws IOException {
        Configuration.downloadsFolder = "downloads";
        open("http://school135.ru/priem-v-10-j-klass");
        File downloadedFile = $(byText("Заявление о приеме в 10-й класс в 2020 году (форма)")).download();
        String fileContent = readFileContent(downloadedFile);
        System.out.println();
       // assertThat(fileContent, Matchers.containsString(""));
    }

    @Test
    void docxTest() throws IOException {
        String path = "src/test/resources/forma-zayavlenie-priem-10kl-1.docx";
        String expextedData = "ЗАЯВЛЕНИЕ";
        String actualData = readDocxFile(path);
        System.out.println(actualData);
        assertThat(actualData,
                containsString(expextedData));
    }

    @Test
    @DisplayName("Скачивание .pdf и проверка его содержимого")
    void downloadPdfFileTest() throws IOException {
        open("http://school135.ru/priem-v-10-j-klass");
        File pdf = $("a[href*='/files/priem-10-klass/priem-10klass-2021.pdf']").download();
        PDF parsedPdf = new PDF(pdf);
        System.out.println();
        Assertions.assertEquals(3, parsedPdf.numberOfPages);
    }

    @Test
    @DisplayName("Скачивание .xls и проверка содержимого файла")
    void downloadXlsFileTest() throws FileNotFoundException {
        open("https://ckmt.ru/price-download.html");
        File file = $(byText("Скачать")).parent().download();
        XLS parsedXls = new XLS(file);
        System.out.println();
        boolean checkPassed = parsedXls.excel
                .getSheetAt(0)
                .getRow(4)
                .getCell(3)
                .getStringCellValue()
                .contains("www.сkmt.ru, msk@ckmt.ru");
        assertTrue(checkPassed);
    }
}
