import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MusicManagement {

    private Arraylist<Song> song_list;
    private Stack<Song> play_history;
    private Arraylist<PlayList> playlists;

    public Arraylist<Song> getSong_list() {
        return song_list;
    }

    public void setSong_list(Arraylist<Song> song_list) {
        this.song_list = song_list;
    }

    public Stack<Song> getPlay_history() {
        return play_history;
    }

    public void setPlay_history(Stack<Song> play_history) {
        this.play_history = play_history;
    }

    public Arraylist<PlayList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Arraylist<PlayList> playlists) {
        this.playlists = playlists;
    }

    public MusicManagement() throws FileNotFoundException {
        song_list = new Arraylist<>();
        play_history = new Stack<>(1);
        playlists = new Arraylist<>();
    }

    public void add_to_song_list(RandomAccessFile file) throws IOException {
        while (file.length() != file.getFilePointer()){
            String[] strings = file.readLine().split(" - ");
            Song song = new Song();
            song.setName(strings[0]);
            song.setSinger(strings[1]);
            song.setWidth(strings[2]);
            song.setLength(strings[3]);
            song.setGenre(strings[4]);
            song_list.add(song);
        }
    }

    public void get_last_song_play(){
        if (play_history.isEmpty()){
            System.out.println("there is no song");
        }else{
            System.out.println(play_history.peek());
        }
    }



    public void play_list_setting(Scanner input) throws IOException {
        System.out.println("1-show and edit playlists");
        System.out.println("2-creat play list");
        System.out.println("3-back");
        int command = input.nextInt();
        switch (command){
            case 1:
                show_playlists();
                break;
            case 2:
                creat_play_lists();
                break;
            case 3:
                return;
        }
    }

    public void show_playlists() throws IOException {
        Scanner input = new Scanner(System.in);
        if (playlists.isEmpty()){
            System.out.println("there is no play list");
        }else {
            for (int i = 0; i < playlists.size(); i++) {
                System.out.println((i) + "-" + playlists.get(i));
            }
        }
        while(true) {
            System.out.println("-------------------------------");
            System.out.println("1-edit play list");
            System.out.println("0-back");
            int command = input.nextInt();
            switch (command){
                case 1:
                    edit_play_list();
                    break;
                case 0:
                    return;
            }
        }
    }

    public void edit_play_list() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("please choose your play list:");
        int command = input.nextInt();
        PlayList playList = new PlayList();
        playList = playlists.get(command);
       playList.print_songs();


        while (true){
            System.out.println("----------------------");
            System.out.println("1-add song to play list");
            System.out.println("2-remove song from play list");
            System.out.println("0-back");
            int command1 = input.nextInt();


            switch (command1){
                case 1:
                    add_song_to_play_list(playList);
                    break;
                case 2:
                    remove_from_play_list(playList);
                    break;
                case 0:
                    return;
            }
        }
    }

    public void remove_from_play_list(PlayList playList){
        Scanner input = new Scanner(System.in);
        playList.print_songs();
        System.out.println("please enter the number :");
        int number = input.nextInt();
        playList.remove_from_play_list(number);
    }

    public void add_song_to_play_list(PlayList playList) throws IOException {
        Scanner input = new Scanner(System.in);
        show_song();
        System.out.println("please enter the number:");
        int number = input.nextInt();
        playList.add_song_to_play_list(song_list.get(number));
    }

    public void creat_play_lists(){
        Scanner input = new Scanner(System.in);
        System.out.println("please enter name of playlist:");
        String name = input.nextLine();
        PlayList playList = new PlayList(name);
        playlists.add(playList);
    }

    public void play_song(){
        System.out.println("please choose your song:");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        System.out.println(song_list.get(number));
        if (!play_history.isEmpty()){
            play_history.pop();
            play_history.push(song_list.get(number));
        }else {
            play_history.push(song_list.get(number));
        }
    }

    public void back_to_menu(){
        Scanner input = new Scanner(System.in);
        System.out.println("0-back");
        int number = input.nextInt();
    }
    public void show_song() throws IOException {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < song_list.size(); i++)
            System.out.println(i  + "." + song_list.get(i));
    }



    public void song_setting(Scanner input) throws IOException {
        while (true){
            System.out.println("1-add song to list");
            System.out.println("2-delete song from list");
            System.out.println("3-back");
            int command = input.nextInt();
            switch (command){
                case 1:
                    add_song_to_list();
                    break;
                case 2:
                    remove_from_list();
                    break;
                case 3:
                    return;
            }
        }
    }

    public void add_song_to_list() throws IOException {
        Scanner input = new Scanner(System.in);
        Song song = new Song();
        System.out.println("enter name:");
        song.setName(input.nextLine());
        System.out.println("enter singer:");
        song.setSinger(input.nextLine());
        System.out.println("enter width:");
        song.setWidth(input.next());
        System.out.println("enter length");
        song.setLength(input.next());
        System.out.println("enter genre:");
        song.setGenre(input.next());
        song_list.add(song);
        add_to_file(song);
    }

    public void add_to_file(Song song) throws IOException {
        RandomAccessFile file = new RandomAccessFile("Songs.txt" , "rw");
        file.seek(file.length());
        file.writeChars(song.getName());
        file.writeChars("- ");
        file.writeChars(song.getSinger());
        file.writeChars("- ");
        file.writeChars(song.getWidth());
        file.writeChars("- ");
        file.writeChars(song.getLength());
        file.writeChars("- ");
        file.writeChars(song.getGenre());
        System.out.println("added successfully\n");
    }

    public void remove_from_list() throws IOException {
        show_song();
        Scanner input = new Scanner(System.in);
        System.out.println("enter your song number:");
        int number = input.nextInt();
        song_list.remove(number);
        System.out.println("removed successfully");
    }

    public void sorted_songs(){
        PriorityQueue<Song> priorityQueue = new PriorityQueue<>();
        Arraylist<Song> sorted_songs = new Arraylist<>();
        for (int i = 0; i < song_list.size(); i++){
            priorityQueue.add(song_list.get(i) , song_list.get(i).getIntLength());
        }
        while (!priorityQueue.isEmpty())
            sorted_songs.add(priorityQueue.remove());
        song_list = sorted_songs;
        System.out.println("sorted successfully");
    }


    @Override
    public String toString() {
        return "MusicManagement{" +
                "song_list=" + song_list +
                ", play_history=" + play_history +
                ", playlists=" + playlists +
                '}';
    }
}

