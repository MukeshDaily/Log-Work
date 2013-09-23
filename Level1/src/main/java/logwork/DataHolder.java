package logwork;

public class DataHolder {
    private long id;
    private String title, content;

    public DataHolder(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = title;
        this.lastName = content;
    }

    @Override
    public String toString() {
        return String.format(
                "DataHolder[id=%d, title='%s', content='%s']",
                id, title, content);
    }
}

