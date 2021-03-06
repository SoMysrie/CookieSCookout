package xmlLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import link.Site;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLLoader {

	public ArrayList<Site> load(String xmlPath) throws Exception {
		ArrayList<Site> sites = new ArrayList<Site>();
		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();

		try {

			final DocumentBuilder builder = factory.newDocumentBuilder();

			final Document document = builder.parse(new File(xmlPath));
			final Element root = document.getDocumentElement();

			final NodeList nodeRoot = root.getChildNodes();
			final int nbNodeRoot = nodeRoot.getLength();

			for (int i = 0; i < nbNodeRoot; i++) {
				if (nodeRoot.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element site = (Element) nodeRoot.item(i);

					final Element mainSite = (Element) site.getElementsByTagName(
							"mainSite").item(0);
					if(mainSite.getTextContent()=="")
						throw new Exception();
					final Element searchSite = (Element) site.getElementsByTagName(
							"searchSite").item(0);
					final Element divURLS = (Element) site
							.getElementsByTagName("divURLS").item(0);
					final Element divVote = (Element) site
					.getElementsByTagName("divVote").item(0);
					final Element divMark = (Element) site
							.getElementsByTagName("divMark").item(0);
					final Element divIngredients = (Element) site
					.getElementsByTagName("divIngredients").item(0);
					final Element titleVote = (Element) site
							.getElementsByTagName("titleVote").item(0);
					final Element titleMark = (Element) site
							.getElementsByTagName("titleMark").item(0);
					final Element divRecipe = (Element) site
							.getElementsByTagName("divRecipe").item(0);
					final Element divImg = (Element) site
							.getElementsByTagName("divImg").item(0);
					final Element divTitle = (Element) site
							.getElementsByTagName("divTitle").item(0);
					if(mainSite==null || searchSite==null ||divURLS==null ||divVote==null ||divMark==null ||divIngredients==null || titleVote==null || titleMark==null ||divRecipe==null || divImg==null || divTitle==null)
						throw new NullPointerException("An element is not in xml");
					else
						sites.add(new Site(mainSite.getTextContent(), searchSite.getTextContent(), divVote.getTextContent(), divMark.getTextContent(), divURLS.getTextContent(),divIngredients.getTextContent(),titleVote.getTextContent(),titleMark.getTextContent(),divRecipe.getTextContent(),divImg.getTextContent(),divTitle.getTextContent()));
				}
			}
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return sites;
	}
}
