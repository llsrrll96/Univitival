package festival.server.univ.common;

public class CommonUtil {
    public static String changeWithEmoji(String text) {
        String regex = "[^\\p{L}\\p{N}\\s]";
        return text.replaceAll(regex, "");
    }
}
