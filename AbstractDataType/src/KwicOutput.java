import java.util.ArrayList;

public class KwicOutput implements Output {

	@Override
	public void display(ArrayList<ArrayList<String>> titles, ArrayList<Address> addresses) {
		for (Address address: addresses) {
			ArrayList<String> title = titles.get(address.getLineIndex());
			int titleLength = title.size();
			String displayTitle = "";
			
			for (int i=address.getStartIndex(); i<=titleLength; i++) {
				if (i==titleLength) {
					i %= titleLength;
				}
				displayTitle = displayTitle + " " + title.get(i);
			}
			
			System.out.println(displayTitle.trim());
        }
	}

}
