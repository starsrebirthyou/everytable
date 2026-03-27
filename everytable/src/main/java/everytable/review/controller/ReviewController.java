package everytable.review.controller;

import java.io.BufferedReader;
import java.util.List;

import org.json.JSONObject;

import everytable.main.controller.Controller;
import everytable.main.controller.Init;
import everytable.main.service.Execute;
import everytable.member.vo.LoginVO;
import everytable.review.vo.ReviewVO;
import everytable.util.page.PageObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String id = null;
		if(login != null) id = login.getId();
		
		request.setAttribute("url", request.getRequestURL());
		
		try {
			String uri = request.getServletPath();
			switch (uri) {
			case "/review/list.do":
				System.out.println("ReviewController.execute() - 댓글 리스트 처리");
				
				PageObject pageObject = PageObject.getInstance(request);

				request.setAttribute("list", Execute.execute(Init.getService(uri), pageObject));

				// 작성자와 로그인한 사람이 같은지 확인하는 처리 : sameId
				List<ReviewVO> list 
					= (List<ReviewVO>) request.getAttribute("list");
				for(ReviewVO vo : list) {
					// 작성된 댓글의 작성자와 로그인한 사람 같은 경우만 처리할 수 있도록 버튼을 보여준다.
					if(id != null && id.equals(vo.getId())) vo.setSameId(1);
					// vo.setSameId(1);
					// content의 특수 문자 처리
					vo.setContent(vo.getContent()
							.replace("\\", "\\\\").replace("\n", "\\n")
							.replace("\"", "\\\"")); 
					System.out.println("ReviewController.execute().pageObject : 처리 후 - "
							+ pageObject);
							
							request.setAttribute("pageObject", pageObject);
							
							return "review/list";
				}

						// 댓글 등록
						case "/review/write.do":
							// 데이터 수집 - no, content -> JSON : 라이브러리 사용 - 카페 참조
							// body로 넘어오는 문자열을 저장하기 위한 객체
							StringBuilder sb = new StringBuilder();
							BufferedReader reader = request.getReader();
							String line;
							
							// post로 넘어온데이터를 읽어오기
							while((line = reader.readLine()) != null) {
								sb.append(line);
							}
							
							// 읽어온 데이터를 문자열로 만든다.
							String jsonData = sb.toString();
							
							// JSON 형식에 맞는 자바 객체로 만들기
							JSONObject jsonObject = new JSONObject(jsonData);
							
							// 데이터를 꺼내서 BoardReplyVO에 넣는다.
							ReviewVO vo = new ReviewVO();
							vo.setNo(jsonObject.getLong("no"));
							vo.setContent(jsonObject.getString("content"));
							vo.setId(id); // 로그인 정보에서 아이디 꺼내기 - 코드가 위에 있음.
							
							// 데이터 수집 확인
							System.out.println("ReviewController.exeute().vo = " + vo);
							
							// DB에 등록하러 가기
							Execute.execute(Init.getService(uri), vo);
							
							return "review/write";
							
						// 댓글 수정
						case "/review/update.do":
							// 데이터 수집 - no, content -> JSON : 라이브러리 사용 - 카페 참조
							// body로 넘어오는 문자열을 저장하기 위한 객체
							sb = new StringBuilder();
							reader = request.getReader();
							line = null;
							
							// post로 넘어온데이터를 읽어오기
							while((line = reader.readLine()) != null) {
								sb.append(line);
							}
							
							// 읽어온 데이터를 문자열로 만든다.
							jsonData = sb.toString();
							
							// JSON 형식에 맞는 자바 객체로 만들기
							jsonObject = new JSONObject(jsonData);
							
							// 데이터를 꺼내서 ReviewVO에 넣는다.
							vo = new ReviewVO();
							vo.setContent(jsonObject.getString("content"));
							vo.setId(id); // 로그인 정보에서 아이디 꺼내기 - 코드가 위에 있음.
							
							// 데이터 수집 확인
							System.out.println("ReviewController.exeute().vo = " + vo);
							
							// DB에 수정하러 가기 & 결과를 request에 담기 -> jsp로 넘긴다.
							request.setAttribute("result", Execute.execute(Init.getService(uri), vo));
							
							return "review/update";
							
						// 댓글 삭제
						case "/review/delete.do":
							// 데이터 수집 - rno / id
							vo = new ReviewVO();
							vo.setId(id); // 로그인 정보에서 아이디 꺼내기 - 코드가 위에 있음.
							
							// DB에 삭제하러 가기 & 결과를 request에 담기 -> jsp로 넘긴다.
							request.setAttribute("result", Execute.execute(Init.getService(uri), vo));
							
							return "review/delete";
							
						default:
							break;
				}
				
		} catch(Exception e) {
			e.printStackTrace();
			
			request.setAttribute("moduleName", "메뉴 주문 리뷰");
			request.setAttribute("e", e);
			return "error/err_500";
		}
		
		return null;
	}
}
