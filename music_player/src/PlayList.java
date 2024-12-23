import java.io.IOException;

public class PlayList {

    private String name;

    private DoublyLinkedList<Song> playlist;

    private Node<Song> current;

    public PlayList(String name) {
        this.name = name;
        playlist = new DoublyLinkedList<>();
    }

    public PlayList(){
        playlist = new DoublyLinkedList<>();
    }

    @Override
    public String toString() {
        return name ;
    }

    public void add_song_to_play_list(Song song) throws IOException {
        playlist.addLast(song);
        System.out.println("added successfully");
    }

    public void remove_from_play_list(int number){
        playlist.remove(number);
        System.out.println("remove successfully");
    }
    public void print_songs() {
        current = playlist.getHead().getNext();
        if (!playlist.isEmpty()) {
            while (current != null) {
                System.out.println(current.getElement());
                current = current.getNext();
            }
        } else {
            System.out.println("The playlist is empty.");
        }
    }
}
