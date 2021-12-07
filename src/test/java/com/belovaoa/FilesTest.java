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
    @DisplayName("Имя файла должно отображаться после загрузки по абсолютному пути (не рекомендуется)")
    void filenameShouldDisplayedUploadActionTest() {
        open("https://the-internet.herokuapp.com/upload");
        File exampleFile = new File("/Users/belova_oa/IdeaProjects/WorkWithFiles_HW7src/test/resources/example.txt");
        $("input[type='file']").uploadFile(exampleFile);
        $("#file-submit").click();
        $("#uploaded-files").shouldHave(text("example.txt"));
    }
}
