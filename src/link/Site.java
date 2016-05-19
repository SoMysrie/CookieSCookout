package link;

import java.util.ArrayList;

public class Site {
    String mainSite;
    String searchSite;
    String divVote;
    String divMark;
    String divURLS;
    ArrayList<NotedReciepes> urls = new ArrayList<NotedReciepes>();

    public String getDivVote() {
        return divVote;
    }


    public void setDivVote(String divVote) {
        this.divVote = divVote;
    }


    public String getDivURLS() {
        return divURLS;
    }


    public void setDivURLS(String divURLS) {
        this.divURLS = divURLS;
    }

    public String getMainSite() {
        return mainSite;
    }


    public void setMainSite(String mainSite) {
        this.mainSite = mainSite;
    }


    public String getSearchSite() {
        return searchSite;
    }


    public void setSearchSite(String searchSite) {
        this.searchSite = searchSite;
    }


    


    public ArrayList<NotedReciepes> getUrls() {
        return urls;
    }


    public void replaceSearchSite(String s) {
        this.searchSite = this.searchSite.replace(Messages.getString("Site.ReplaceKey"), s);
    }

    public void addURL(String s) {
        this.urls.add(new NotedReciepes(this.mainSite + s));
        System.out.println(this.mainSite + s);
    }


	public Site(String mainSite, String searchSite, String divVote,
			String divMark, String divURLS) {
		super();
		this.mainSite = mainSite;
		this.searchSite = searchSite;
		this.divVote = divVote;
		this.divMark = divMark;
		this.divURLS = divURLS;
	}

 
}