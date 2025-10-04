interface Playable {
    void play();
    void pause();
}

class MusicPlayer implements Playable {
    @Override
    public void play() {
        System.out.println("Music is playing...");
    }

    @Override
    public void pause() {
        System.out.println("Music is paused.");
    }
}

class VideoPlayer implements Playable {
    @Override
    public void play() {
        System.out.println("Video is playing...");
    }

    @Override
    public void pause() {
        System.out.println("Video is paused.");
    }
}

public class p2 {
    public static void main(String[] args) {
        Playable p;

        p = new MusicPlayer();
        p.play();
        p.pause();

        p = new VideoPlayer();
        p.play();
        p.pause();
    }
}
