
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;

public class MarkdownParse {
    public static boolean containsSpace(String link) {
        return link.contains(" ");
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();

        int currentIndex = 0;
        Stack<Character> bracketTracker = new Stack<>();
        boolean findLink = false;
        int start = 0;
        int end = 0;
        while (currentIndex < markdown.length()) {
            char curr = markdown.charAt(currentIndex);
            // if an escape char is found, skip it and the
            // character it is escaping
            if (curr == '\\') {
                currentIndex += 2;
                continue;
            }
            // if we are potentially looking at a link after finding []
            if (findLink) {
                // if there arent any other brackets on the bracket tracker we 
                // should check what we are looking at and if it is an open 
                // braket and mark the current index as the start of a link
                if (bracketTracker.isEmpty()) {
                    if (curr == '(') {
                        bracketTracker.push(curr);
                        start = currentIndex;
                    } 
                    // something else came after the ] that wasn't (
                    else { 
                        findLink = false;
                    }
                } else {
                    // if ) then we should note the end of the link down
                    if (curr == ')') {
                        end = currentIndex;
                        //create substring with only the text of the link
                        String link = markdown.substring(start + 1, end);
                        //check the link for any spaces
                        if (!containsSpace(link)) {
                            toReturn.add(link);
                        }
                        // take the ( off the bracket tracker
                        bracketTracker.pop();
                        findLink = false;
                    }
                }
            } 
            else { 
                // looking for [ in file and adding it to the tracker if so
                if (curr == '[') {
                    bracketTracker.push(curr);
                } else if (curr == ']') { 
                    // if found a close bracket and there is a [ on the tracker
                    // we potentially found a link and should move onto that
                    if (!bracketTracker.isEmpty()) {
                        bracketTracker.clear();
                        findLink = true;
                    }
                } 
                else {
                    // if we find an potential image tag
                    if (curr == '!') {
                        //and it is actually before a '[' and not at the end
                        if (currentIndex < markdown.length() - 1 && 
                            markdown.charAt(currentIndex + 1) == '[') {
                            
                            currentIndex += 2;
                            }
                    }
                }
            }
            // move to next char
            currentIndex++;
        }

        return toReturn;

    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}