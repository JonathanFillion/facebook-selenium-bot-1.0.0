package dataStructures;

import java.util.ArrayList;

public class DataTemp {

	ArrayList<String> potentialTargets = new ArrayList<String>();
	ArrayList<Profile> profile = new ArrayList<Profile>();

	public String getNewTarget() {

		if (potentialTargets.size() < 1)
			return "https://www.facebook.com/mazin.abduladhim";
		else
			return "";

	}

	public void saveProfile(Profile profile) {

	}

}
