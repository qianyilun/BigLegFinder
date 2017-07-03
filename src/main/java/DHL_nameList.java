import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 02/07/17.
 *
 */
public class DHL_nameList {
    public static void main(String[] args) {
        String addr = "http://www.sfu.ca/students/honour-rolls/deans-honour-roll.html";
        DHL_nameList dhl = new DHL_nameList();
        dhl.openConnection(addr);

        System.out.println("Totally has " + dhl.getFullNameList().size() + " students, they are: ");
        for (String s : dhl.getFullNameList()) {
            System.out.println(s);
        }
    }

    private List<String> lst = new ArrayList<>();

    public void addNames(String names) {
        lst.add(names);
    }

    public List<String> getFullNameList () {
        return lst;
    }

    // Open connection
    private void openConnection(String addr) {
        try {
            // Open connection
            Document doc = Jsoup.connect(addr).get();

            // Make selection
            for (int i = 0; i < 18 ; i +=2) {
                makeSelection(doc, ""+i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeSelection(Document doc, String index) {
        Elements div = doc.getElementsByClass("toggleContent item" + index);
        Elements ul = div.select("ul");
        Elements li = ul.select("li");

        for (Element element : li) {
            String name = element.ownText();
            this.addNames(name);
        }
    }
}