// importing junit libraries
import static org.junit.Assert.*;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;



public class MarkdownParseTest {

    // javac -cp ".;lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar" MarkdownParseTest.java
    // java -cp ".;lib/junit-4.12.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore MarkdownParseTest
    
    @Test 
    public void newTest() {
        assertTrue(true);
    }
    @Test 
    public void addition() {
        // Passes if 2 equals 1 + 1, fails if they are not equal
        assertEquals(2, 1 + 1);
    }

    @Test
    public void TestOne() throws IOException, NoSuchFileException {
        //passes if running Markdown parse returns the correct text for "test-file.md"
        List<String> correctOutput = List.of("https://something.com","some-page.html");
        Path fileName = Path.of("Group-test-file.md");
        // read the file contents into a string
	    String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput,links);
    }

    @Test
    public void TestTwo() throws IOException, NoSuchFileException {
        //passes if running Markdown parse returns the correct text for "test-file2.md"
        List<String> correctOutput = List.of("https://google.com");
        Path fileName = Path.of("Group-test-file2.md");
        // read the file contents into a string
	    String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput,links);
    }

    @Test
    public void TestThree() throws IOException, NoSuchFileException {
        //passes if running Markdown parse returns the correct text for "test-file3.md"
        ArrayList<String> correctOutput = new ArrayList<>();
        //List<String> correctOutput = List.of("");
        Path fileName = Path.of("Group-test-file3.md");
        // read the file contents into a string
	    String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput.size(),links.size());
    }

    @Test
    public void TestFour() throws IOException, NoSuchFileException {
        //passes if running Markdown parse returns the correct text for "test-file4.md"
        List<String> correctOutput = List.of("https://something.com", "");
        Path fileName = Path.of("Group-test-file4.md");
        // read the file contents into a string
	    String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput,links);
    }
    @Test
    public void TestFive() throws IOException, NoSuchFileException {
        //passes if running Markdown parse returns the correct text for "test-file4.md"
        ArrayList<String> correctOutput = new ArrayList<>();
        Path fileName = Path.of("Group-test-file5.md");
        // read the file contents into a string
	    String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput.size(),links.size());
    }

    /*
    @Test
    public void testImageAtEOF() throws IOException, NoSuchFileException {
        //passes if running Markdown parse returns the correct text for "test-file4.md"
        ArrayList<String> correctOutput = new ArrayList<>();
        Path fileName = Path.of("Group-test-file5.md");
        // read the file contents into a string
	    String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput.size(),links.size());
    }
    */
    @Test
    public void testFiles1to8() throws IOException, NoSuchFileException {
        //passes if running Markdown parse returns the correct text for "test-file4.md"
        ArrayList<String> correctOutput1 = new ArrayList<>();
        ArrayList<String> correctOutput2 = new ArrayList<>();
        ArrayList<String> correctOutput3 = new ArrayList<>();
        ArrayList<String> correctOutput4 = new ArrayList<>();
        ArrayList<String> correctOutput5 = new ArrayList<>();
        ArrayList<String> correctOutput6 = new ArrayList<>();
        ArrayList<String> correctOutput7 = new ArrayList<>();
        ArrayList<String> correctOutput8 = new ArrayList<>();

        correctOutput1.addAll(Arrays.asList("https://something.com","some-page.html"));
        correctOutput2.addAll(Arrays.asList("https://something.com", "some-page.html", "https://something.com", "some-page.html"));

        Path fileName1 = Path.of("test-file.md");
        Path fileName2 = Path.of("test-file2.md");
        Path fileName3 = Path.of("test-file3.md");
        Path fileName4 = Path.of("test-file4.md");
        Path fileName5 = Path.of("test-file5.md");
        Path fileName6 = Path.of("test-file6.md");
        Path fileName7 = Path.of("test-file7.md");
        Path fileName8 = Path.of("test-file8.md");

        // read the file contents into a string
	    String contents1 = Files.readString(fileName1);
	    String contents2 = Files.readString(fileName2);
	    String contents3 = Files.readString(fileName3);
	    String contents4 = Files.readString(fileName4);
	    String contents5 = Files.readString(fileName5);
	    String contents6 = Files.readString(fileName6);
	    String contents7 = Files.readString(fileName7);
	    String contents8 = Files.readString(fileName8);

        // run getLinks on the contents of the file
        ArrayList<String> links1 = MarkdownParse.getLinks(contents1);
        ArrayList<String> links2 = MarkdownParse.getLinks(contents2);
        ArrayList<String> links3 = MarkdownParse.getLinks(contents3);
        ArrayList<String> links4 = MarkdownParse.getLinks(contents4);
        ArrayList<String> links5 = MarkdownParse.getLinks(contents5);
        ArrayList<String> links6 = MarkdownParse.getLinks(contents6);
        ArrayList<String> links7 = MarkdownParse.getLinks(contents7);
        ArrayList<String> links8 = MarkdownParse.getLinks(contents8);

        assertEquals(correctOutput1,links1);
        assertEquals(correctOutput2,links2);
        assertEquals(correctOutput3,links3);
        assertEquals(correctOutput4,links4);
        assertEquals(correctOutput5,links5);
        assertEquals(correctOutput6,links6);
        assertEquals(correctOutput7,links7);
        assertEquals(correctOutput8,links8);
    }

    /*Single Issue Tests
    @Test
    public void testMissingParen(){
        String contents= "[link](a.com";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void testOpenBracketInLink(){
        String contents= "[link](a.c(om)";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void testSpacesAroundLink(){
        String contents= "[link](   a.com    )";
        List<String> expect = List.of("a.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void testOpenBracketInTextOfImage(){
        String contents= "![l[ink](a.com)";
        List<String> expect = List.of("a.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void testEscapedBracketInLinkText(){
        String contents= "[This\\]is](a.com)";
        List<String> expect = List.of("a.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    */
}



