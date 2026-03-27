package everytable.main.controller;

import java.util.HashMap;
import java.util.Map;

import everytable.main.dao.DAO;
import everytable.main.service.Service;
import everytable.notice.controller.NoticeController;
import everytable.notice.dao.NoticeDAO;
import everytable.notice.service.NoticeDeleteService;
import everytable.notice.service.NoticeListService;
import everytable.notice.service.NoticeUpdateService;
import everytable.notice.service.NoticeViewService;
import everytable.notice.service.NoticeWriteService;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

/**
 * Servlet implementation class Init
 */
// DB 클래스 확인, 객체 생성 / 저장 / 조립
// @WebServlet("/Init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 생성된 객체를 저장하는 변수 선언 ----------------------------------
	// Controller를 저장하는 변수 - 모듈명으로 저장
	private static Map<String, Controller> controllerMap = new HashMap<>();
	// Service를 저장하는 변수 - URI로 저장
	private static Map<String, Service> serviceMap = new HashMap<>();
	// DAO를 저장하는 변수 - 클래스이름 앞 부분 소문자로 저장
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	// Controller 저장변수에서 Controller를 꺼내가는 메서드
	public static Controller getController(String module) {
		return controllerMap.get(module);
	}
	
	// Service 저장변수에서 Service를 꺼내가는 메서드
	public static Service getService(String uri) {
		return serviceMap.get(uri);
	}
	
    /**
     * Default constructor. 
     */
    public Init() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    // 서버가 돌아갈 때 실행되도록 하고 싶다.
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Init.init()-----------------------------------------------------");
		// 1. 생성하고, 2 저장 - map, 3. 조립
		// 생성해서 저장해 놓는다.
		
		// ***공지사항 생성/저장/조립
		// -- Controller 저장
		controllerMap.put("/notice", new NoticeController());
		// -- Service 저장
		serviceMap.put("/notice/list.do", new NoticeListService());
		serviceMap.put("/notice/write.do", new NoticeWriteService());
		serviceMap.put("/notice/view.do", new NoticeViewService());
		serviceMap.put("/notice/update.do", new NoticeUpdateService());
		serviceMap.put("/notice/delete.do", new NoticeDeleteService());
		// -- DAO 저장
		daoMap.put("noticeDAO", new NoticeDAO());
		// -- service에 dao 조립
		serviceMap.get("/notice/list.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/write.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/view.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/update.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/delete.do").setDAO(daoMap.get("noticeDAO"));
		
		
		System.out.println("Init.init() - 객체 로딩 완료 -----------------------------------");
	}

}
