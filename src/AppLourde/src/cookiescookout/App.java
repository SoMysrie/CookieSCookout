package cookiescookout;

import cookiescookout.ws.WsServer;

/**
 * @author SoMysrie
 * 
 * Main application class
 * 
 * Final class because it must not be inherited
 */
public final class App
{
	/**
	 * Main application entry
	 */
	public static void main( final String[] args )
	{
		System.out.println("CookieSCookout app...") ;
		
		WsServer ws = new WsServer() ;
		
		ws.start();
	} ;
} ;