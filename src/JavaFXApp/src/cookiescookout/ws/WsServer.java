package cookiescookout.ws;

public final class WsServer extends Thread
{
	public WsServer()
	{
		System.out.println("Web Service Server...");
	} ;
	
	/**
	 * Main process
	 * */
	public void run()
	{
		try
		{
			System.out.println("On Web Service Main Process...");
		}
		catch( final Exception e )
		{
			
		}
		finally
		{
			System.out.println("Exit Web Service Main Process...");
		}
	} ;
} ;