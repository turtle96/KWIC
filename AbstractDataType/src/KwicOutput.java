import java.util.ArrayList;

public class KwicOutput implements Output {

	@Override
	public void display(ArrayList<ArrayList<String>> titles, ArrayList<Address> addresses) {
		for (Address address: addresses) {
			ArrayList<String> title = titles.get(address.getLineIndex());
			int titleLength = title.size();
			int startIndex = address.getStartIndex();
			
            String displayTitle = title.get(startIndex);
			int i = (startIndex + 1) % titleLength;
			
			while (i != startIndex) {
				displayTitle = displayTitle + " " + title.get(i);
				i++;
				if (i>=titleLength) {
                    i %= titleLength;
                }
			}
			
			System.out.println(displayTitle.trim());
        }
	}

}
