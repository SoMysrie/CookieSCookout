package annotationParser;

public enum NIVEAU {
	   MINEUR("Action mineure"), 
	   AMELIORATION("Am�lioration possible"),
	   BUG("Bug � corriger rapidement"),
	   CRITIQUE("Bug critique � corriger d'urgence !");
	   
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