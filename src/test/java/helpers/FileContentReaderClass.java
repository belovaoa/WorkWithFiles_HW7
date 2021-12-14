package helpers;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileContentReaderClass {

    public static class FileContentReader1 {
        // распарсить файл в строку
        public static String readFileContent(File file) throws IOException {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        }

        // указать путь к файлу
        public static File getFileFromPath(String path) {
            return new File(path);
        }

        // Вернуть текст файла .docx
        public static String readDocxFile(String path) throws IOException {
            FileInputStream inputStream = new FileInputStream(getFileFromPath(path));
            XWPFDocument docx = new XWPFDocument(inputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
            return extractor.getText();
        }
    }
}
