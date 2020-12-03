/*
 * This is the Util class, it contains helper methods that doesn't relate to the program's logic
 */

package cleanandgo;
import java.io.IOException;

public class Util {

//    /**
//     * Returns user's input
//     * @param prompt description to print for a user
//     * @throws IOException
//     * */
    public static String getUsersInput(String prompt) {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.print(prompt);
            System.out.flush();
            int c = System.in.read();
            while (c != '\n' && c != -1) {
                buffer.append((char) c);
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }
}
