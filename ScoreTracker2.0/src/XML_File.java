import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XML_File {
	public static boolean create_XMLFile(Round round, String file_name) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
	
		
		// for start time
		Element r = doc.createElement("Round");
		Element el = doc.createElement("Start_Time");
		Text elText = doc.createTextNode(round.getStart_time().toString());
		el.appendChild(elText);
		r.appendChild(el);

		// for end time
		el = doc.createElement("End_Time");
		elText = doc.createTextNode(round.getEnd_time().toString());
		el.appendChild(elText);
		r.appendChild(el);

		// for score
		el = doc.createElement("Score");
		elText = doc.createTextNode("" + round.getTotal_score());
		el.appendChild(elText);
		r.appendChild(el);

		// for totalpar
		el = doc.createElement("TotalPar");
		elText = doc.createTextNode("" + Round.TOTAL_HOLE_NUMBER);
		el.appendChild(elText);
		r.appendChild(el);

		// for holes
		for (int i = 0; i < round.getHole_list().size(); i++) {
			Hole hole = round.getHole_list().get(i);

			Element h = doc.createElement("Hole");
			Element s_time = doc.createElement("Start_Time");
			Text t_s_time = doc
					.createTextNode(hole.getStart_time().toString());
			s_time.appendChild(t_s_time);
			Element e_time = doc.createElement("End_Time");
			Text t_e_time = doc
					.createTextNode(hole.getEnd_time().toString());
			e_time.appendChild(t_e_time);
			Element hole_score = doc.createElement("Score");
			Text t_hole_score = doc.createTextNode("" + hole.getTotal_score());
			hole_score.appendChild(t_hole_score);
			Element par = doc.createElement("Par");
			Text t_par = doc.createTextNode("" + (i + 1));
			par.appendChild(t_par);

			h.appendChild(s_time);
			h.appendChild(e_time);
			h.appendChild(hole_score);
			h.appendChild(par);

			// for all strikes
			for (int j = 0; j < hole.getStrike_list().size(); j++) {
				Strike strike = hole.getStrike_list().get(j);

				Element s = doc.createElement("Strike");
				Element number = doc.createElement("Number");
				Text t_number = doc.createTextNode("" + (j + 1));
				number.appendChild(t_number);
				Element club = doc.createElement("Club");
				Text t_club = doc.createTextNode(strike.getClub());
				club.appendChild(t_club);
				Element sLocation = doc.createElement("Start_Location");
				Text t_sLocation = doc.createTextNode(
						strike.getStart_location().getLatitude() + "," + strike.getStart_location().getLongtitude());
				sLocation.appendChild(t_sLocation);
				Element eLocation = doc.createElement("End_Location");
				Text t_eLocation = doc.createTextNode(
						strike.getEnd_location().getLatitude() + "," + strike.getEnd_location().getLongtitude());
				eLocation.appendChild(t_eLocation);

				s.appendChild(number);
				s.appendChild(club);
				s.appendChild(sLocation);
				s.appendChild(eLocation);
				h.appendChild(s);
			}
			r.appendChild(h);
		}
		doc.appendChild(r);

		boolean flag = doc2XmlFile(doc, file_name);

		return flag;

	}

	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;

	}

}
