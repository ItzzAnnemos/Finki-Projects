package mk.ukim.finki.NP.lab8.MP3Player;

import java.util.ArrayList;
import java.util.List;

public class MP3Player {
    private List<Song> songs;
    private Song current;
    private boolean isPlaying;
    private boolean isStopped;

    public MP3Player(List<Song> songs) {
        this.songs = songs;
        this.current = songs.get(0);
        this.isPlaying = false;
        this.isStopped = false;
    }

    public void pressPlay() {
        if (!isPlaying) {
            System.out.println("Song " + songs.indexOf(current) + " is playing");
            isPlaying = true;
            isStopped = false;
        } else {
            System.out.println("Song is already playing");
        }
    }

    public void pressStop() {
        if (isPlaying) {
            System.out.println("Song " + songs.indexOf(current) + " is paused");
            isPlaying = false;
            isStopped = true;
        } else if (isStopped){
            isStopped = false;
            current = songs.get(0);
            System.out.println("Songs are stopped");
        } else {
            System.out.println("Songs are already stopped");
        }
    }

    public void pressFWD() {
        System.out.println("Forward...");
        isPlaying = false;
        isStopped = true;
        if (current == songs.get(songs.size() - 1)) {
            current = songs.get(0);
        } else {
            current = songs.get(songs.indexOf(current) + 1);
        }
    }

    public void pressREW() {
        System.out.println("Reward...");
        isPlaying = false;
        isStopped = true;
        if (current == songs.get(0)) {
            current = songs.get(songs.size() - 1);
        } else {
            current = songs.get(songs.indexOf(current) - 1);
        }
    }

    public void printCurrentSong() {
        System.out.println(current);
    }

    @Override
    public String toString() {
        return "MP3Player{" +
                "currentSong = " + songs.indexOf(current) + ", " +
                "songList = " + songs +
                '}';
    }
}
