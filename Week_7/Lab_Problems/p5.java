class Content {
    void show() { System.out.println("Generic content"); }
}
class Movie extends Content {
    void movieDetails() { System.out.println("Movie: Ratings, Duration, Subtitles"); }
}
class Series extends Content {
    void seriesDetails() { System.out.println("Series: Seasons, Episodes, Next Episode"); }
}
class Documentary extends Content {
    void docDetails() { System.out.println("Documentary: Educational Tags, Related Content"); }
}
public class p5 {
    public static void main(String[] args) {
        Content c = new Movie();
        c.show();
        if (c instanceof Movie) ((Movie)c).movieDetails();
    }
}
