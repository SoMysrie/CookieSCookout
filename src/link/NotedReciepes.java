package link;

/**
 * Created by Nicolas_Travail on 17/03/2016.
 */
public class NotedReciepes {
    private String url;
    private int vote;

    public NotedReciepes(String url){
        this.url=url;
        this.vote=0;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
