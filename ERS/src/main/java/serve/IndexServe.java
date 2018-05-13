package serve;

import javax.servlet.http.HttpServletRequest;

public class IndexServe {

	public static String index(HttpServletRequest request) {
		return "index.jsp";
	}

}
