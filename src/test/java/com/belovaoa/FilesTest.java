package com.belovaoa;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты на файлы")
public class FilesTest {
    @Test
    @DisplayName("Имя файла должно отображаться после загрузки по относительному пути")
    void filenameShouldDisplayedUploadActionTest() {
        open("https://the-internet.herokuapp.com/upload");
        $("input[type='file']").uploadFromClasspath("example.txt");
        $("#file-submit").click();
        $("#uploaded-files").shouldHave(text("example.txt"));
    }

    @Test
    @DisplayName("Скачивание текстового файла и проверка его содержимого")
    void downloadSimpleTextFileTest() throws IOException {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File download = $("#raw-url").download();
        //System.out.println(download.getAbsolutePath());
        String fileContent = IOUtils.toString(new FileReader(download));
        assertTrue(fileContent.contains("This repository is the home of the next generation of JUnit, _JUnit 5_."));
    }
}
