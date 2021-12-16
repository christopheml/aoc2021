package year2021.day16;

public class ConsumableString {

    private String content;

    public ConsumableString(String content) {
        this.content = content;
    }

    public ConsumableString take(int length) {
        var chunk = content.substring(0, length);
        content = content.substring(length);
        return new ConsumableString(chunk);
    }

    @Override
    public String toString() {
        return content;
    }

    public int length() {
        return content.length();
    }

}
