package everytable.reservation.controller;

import everytable.main.controller.Controller;
import everytable.main.controller.Init;
import everytable.main.service.Execute;
import everytable.util.page.PageObject;
import jakarta.servlet.http.HttpServletRequest;

public class ReservationController implements Controller {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("url", request.getRequestURL());

		try {
			String uri = request.getServletPath();

			switch (uri) {
			case "/reservation/list.do":
				PageObject pageObject = PageObject.getInstance(request);
				
				request.setAttribute("list", Execute.execute(Init.getService(uri), pageObject));
				
				System.out.println("ReservationControllr.execute().pageObject - " + pageObject);
				request.setAttribute("pageObject", pageObject);
				
				return "reservation/list";
				
			} // switch~case 끝

		} catch (Exception e) {
			e.printStackTrace();
			return "error/err_500";
		} // try~catch 끝

		return null;
	} // execute() 끝

}
