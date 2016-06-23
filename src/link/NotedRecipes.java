package link;

/**
 * Created by Nicolas_Travail on 17/03/2016.
 */
public class NotedRecipes {
    
	private String name;
	private String url;
    private int vote;
    private int note;
    private String[] ingredients;
    private String recipe;

    public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public NotedRecipes(String url){
        this.url=url;
        this.vote=0;
    }
    public NotedRecipes(String name,String url, int vote, int note, String[] ingredients,
			String recipe) {
		super();
		this.name= name;
		this.url = url;
		this.vote = vote;
		this.note = note;
		this.ingredients = ingredients;
		this.recipe = recipe;
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
