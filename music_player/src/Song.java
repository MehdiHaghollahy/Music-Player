public class Song implements Comparable<Song> {
    private String name;

    private String singer;

    private String width;

    private String length;

    private String genre;

    public Song(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int compareTo(Song o) {
        int thislenght = Integer.parseInt(this.length);
        int otherlenght = Integer.parseInt(o.getLength());
        return Integer.compare(thislenght , otherlenght);
    }


    @Override
    public String toString() {
        return
                 name + '\'' +
                 singer + '\'' +
                 width + '\'' +
                 length + '\'' +
                 genre + '\'';
    }

    public int getIntLength() {
        String [] len = width.split(":");
        return Integer.parseInt(len[0]) * 60 + Integer.parseInt(len[1]);
    }
}
