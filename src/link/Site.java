package link;

import java.util.ArrayList;

public class Site {
    String mainSite;
    String searchSite;
    String divVote;
    String divMark;
    String divURLS;
    String divIngredients;
    String divRecipe;

    public String getDivRecipe() {
        return divRecipe;
    }

    public void setDivRecipe(String divRecipe) {
        this.divRecipe = divRecipe;
    }



    public String getTitleVote() {
        return titleVote;
    }

    public void setTitleVote(String titleVote) {
        this.titleVote = titleVote;
    }

    public String getTitleMark() {
        return titleMark;
    }

    public void setTitleMark(String titleMark) {
        this.titleMark = titleMark;
    }

    String titleVote;
    String titleMark;
    ArrayList<NotedRecipes> urls = new ArrayList<NotedRecipes>();

    public String getDivIngredients() {
        return divIngredients;
    }

    public void setDivIngredients(String divIngredients) {
        this.divIngredients = divIngredients;
    }



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


    public String getDivMark() {
        return divMark;
    }


    public void setDivMark(String divMark) {
        this.divMark = divMark;
    }


    public ArrayList<NotedRecipes> getUrls() {
        return urls;
    }


    public void replaceSearchSite(String s) {
        this.searchSite = this.searchSite.replace(Messages.getString("Site.ReplaceKey"), s);
    }

    public void reinitSearchSite(String s) {
        this.searchSite = this.searchSite.replace(s, Messages.getString("Site.ReplaceKey"));
    }

    public void addURL(String s) {
        this.urls.add(new NotedRecipes(this.mainSite + s));
        System.out.println(this.mainSite + s);
    }


    public Site(String mainSite, String searchSite, String divVote,
                String divMark, String divURLS, String divIngredients, String titleVote, String titleMark, String divRecipe) {
        super();
        this.mainSite = mainSite;
        this.searchSite = searchSite;
        this.divVote = divVote;
        this.divMark = divMark;
        this.divURLS = divURLS;
        this.divIngredients= divIngredients;
        this.titleVote=titleVote;
        this.titleMark=titleMark;
        this.divRecipe=divRecipe;
    }


}