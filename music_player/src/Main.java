import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        RandomAccessFile file = new RandomAccessFile("Songs.txt" , "rw");


        MusicManagement musicManagement = new MusicManagement();
        musicManagement.add_to_song_list(file);

        while (true){
            System.out.println("welcome to music player");
            System.out.println("1-play song");
            System.out.println("2-song setting");
            System.out.println("3-play list setting");
            System.out.println("4-play history");
            System.out.println("5-sorting song");
            System.out.println("6-exit");
            int command = input.nextInt();
            switch (command){
                case 1:
                    musicManagement.show_song();
                    musicManagement.play_song();
                    musicManagement.back_to_menu();
                    break;
                case 2:
                    musicManagement.song_setting(input);
                    break;
                case 3:
                    musicManagement.play_list_setting(input);
                    break;
                case 4:
                    musicManagement.get_last_song_play();
                    musicManagement.back_to_menu();
                    break;
                case 5:
                    musicManagement.sorted_songs();
                    musicManagement.back_to_menu();
                    break;
                case 6:
                    return;
            }
        }
    }
}