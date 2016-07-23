package annotationParser;

public enum NIVEAU {
	   MINEUR("Action mineure"), 
	   AMELIORATION("Amélioration possible"),
	   BUG("Bug à corriger rapidement"),
	   CRITIQUE("Bug critique à corriger d'urgence !");
	   
	   private String description;
	   NIVEAU(String desc){
	      description = desc;
	   }
	   
	   public String toString(){
	      return description;
	   }

	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
	}