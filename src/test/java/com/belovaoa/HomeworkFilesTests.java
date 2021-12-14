package com.belovaoa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.FileContentReaderClass.FileContentReader1.readDocxFile;
import static helpers.FileContentReaderClass.FileContentReader1.readFileContent;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


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
}
