package Project_0;

import java.util.logging.Logger;

public class Main {
	
	public static void main(String[] args) {	
		Initialization.initializeData();
		UserLogin.userLogin();
		OnClose.programHouseKeeping();
	}
}
