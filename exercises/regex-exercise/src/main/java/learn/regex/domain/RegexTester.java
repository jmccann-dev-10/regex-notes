package learn.regex.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {

    private final String filePath;

    private List<String> fileContents = new ArrayList<>();

    public RegexTester(String filePath) throws IOException {
        this.filePath = filePath;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String nextLine = reader.readLine();
            do {
                fileContents.add(nextLine);
                nextLine = reader.readLine();
            } while (nextLine != null);
        }
    }

    public List<String> findAll(String regex) {
        List<String> content = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        for (String line : fileContents) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                content.add(matcher.group());
            }
        }
        return content;
    }



}
