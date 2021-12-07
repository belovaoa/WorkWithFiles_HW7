package com.belovaoa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
}
