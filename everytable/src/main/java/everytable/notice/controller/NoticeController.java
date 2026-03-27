package everytable.notice.controller;

import java.net.URLEncoder;

import everytable.main.controller.Controller;
import everytable.main.controller.Init;
import everytable.main.service.Execute;
import everytable.notice.vo.NoticeVO;
import everytable.util.page.PageObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// Controller
// - 웹에서 메뉴에 해당되는 URL -> 메뉴 처리

public class NoticeController implements Controller {

	// Controller 종류이므로 main을 복사해서 사용
	public String execute(HttpServletRequest request) {
		
		// 메시지 처리를 위한 세션 꺼내기
		HttpSession session = request.getSession();
		
		// 잘못된 URI 처리 / 오류를 위한 URL 저장
		request.setAttribute("url", request.getRequestURL());
		try {  // 정상처리
			// 요청 uri에 따라 처리된다.
			// list - /notice/list.do
			// 1. 공지사항 메뉴 입력
			String uri = request.getServletPath();
			
			// 2. 공지사항 처리
			// 사용 변수 선언
			NoticeVO vo;
			Integer result;
			long no;
			
			switch (uri) {
			// 1. 공지 리스트
			case "/notice/list.do" :
				// System.out.println("공지사항 리스트 처리");
				// 페이지 처리를 위한 객체
				// getInstance() - 객체를 생성해서 넘긴다
				// - 1. PageObject 생성  2. request에서 page / 검색 정보를 받아서 세팅
				PageObject pageObject = PageObject.getInstance(request);
				
				// 생성된 Service를 가져와서 실행 -> Execute가 실행하면 로그를 남긴다.
				// List<NoticeVO> list = new NoticeListService().service(null);.
				request.setAttribute("list", Execute.execute(Init.getService(uri), pageObject));
				// 처리된 후의 prageObject 데이터 확인
				System.out.println("NoticeController.execute().pageObject - " + pageObject);
				request.setAttribute("pageObject", pageObject);
				// jsp의 위치 정보 "/WEB-INF/view/" + "board/list" + ".jsp"
				return "notice/list";

				
			// 2. 공지 보기
			case "/notice/view.do":
				// 넘어오는 데이터 수집
				// 데이터 수집 - 번호
				no = Long.parseLong(request.getParameter("no"));
				
				// DB 데이터 가져오기. request에 저장
				request.setAttribute("list", Execute.execute(Init.getService(uri), no));
				
				return "notice/view";
				
				
			// 3-1. 등록 화면
			case "/notice/writeForm.do" :
				return "notice/writeForm";
				
				
			// 3-2. 등록 처리
			case "/notice/write.do" :
				// 데이터 수집 - NoticeVO : 제목, 내용, 시작일, 종료일
				vo = new NoticeVO();
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				
				// 처리 NoticeWriteService - NoticeDAO
				// 등록이 되거나 오류가 난다.
				Execute.execute(Init.getService(uri), vo);
				
				// 등록이 되면 메시지 처리를 한다.
				session.setAttribute("msg", "새로운 공지가 등록되었습니다.");
				
				return "redirect:list.do?perPageNum=" + request.getParameter("perPageNum");
				
				
			// 4-1. 공지 수정 폼
			case "/notice/updateForm.do":
				// 넘어오는 데이터 수집
				// 데이터 수집 - 번호
				no = Long.parseLong(request.getParameter("no"));
				
				// DB 데이터 가져오기. request에 저장
				request.setAttribute("vo", Execute.execute(Init.getService("/notice/view.do"), no));
				
				return "notice/updateForm";
				
				
			// 4-2. 공지 수정 처리
			case "/notice/update.do":
				// 넘어오는 데이터 수집 - vo : 제목, 내용, 시작일, 종료일
				vo = new NoticeVO();
				// 데이터 수집 - 번호
				vo.setNo(Long.parseLong(request.getParameter("no")));
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				
				// DB 데이터 수정 - update, insert, delete 쿼리를 실행하면 int 데이터가 나온다.
				result = (Integer) Execute.execute(Init.getService(uri), vo);
				
				// 메시지 처리
				if(result == 1) session.setAttribute("msg", "공지가 수정되었습니다.");
				
				return "redirect:view.do?no=" + vo.getNo() + "&page=" + request.getParameter("page")
					+ "&perPageNum=" + request.getParameter("perPageNum")
					+ "&key=" + request.getParameter("key")
					+ "&word=" + URLEncoder.encode(request.getParameter("word"), "utf-8")
					+ "&period=" + request.getParameter("period");
				
				
			// 5. 공지 삭제
			case "/notice/delete.do":
				// 넘어오는 데이터 수집 - vo : 제목, 내용, 시작일, 종료일
				// 데이터 수집 - 번호
				no = Long.parseLong(request.getParameter("no"));
				
				// DB 데이터 수정 - update, insert, delete 쿼리를 실행하면 int 데이터가 나온다.
				result = (Integer) Execute.execute(Init.getService(uri), no);
				
				// 메시지 처리
				if(result == 1) session.setAttribute("msg", "공지가 삭제되었습니다.");
				else session.setAttribute("msg", "이미 삭제된 공지입니다.");

				return "redirect:list.do?perPageNum=" + request.getParameter("perPageNum");
				
				
			default :
				// WEB-INF/views/ + error/noPage + .jsp
				return "error/noPage";
			}  // switch 끝
		} catch (Exception e) {
			e.printStackTrace();
			// 잘못된 URI 처리
			request.setAttribute("moduleName", "공지사항");
			request.setAttribute("e", e);
			// WEB-INF/views/ + error/noPage + .jsp
			return "error/err_500";
		}
	}  // execute() 끝

}
