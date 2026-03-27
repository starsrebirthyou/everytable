package everytable.review.vo;

public class ReviewVO {

	private String id; //아이디
	private String name;  //이름,작성자
	private String writeDate;  //작성일
	private String content;  //작성일
	private long no;  //번호
	private int sameId; // 댓글 작성아이디와 로그인한 아이디가 같으면 1, 같지 않으면 0 을 넣어 둔다.
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public int getSameId() {
		return sameId;
	}
	public void setSameId(int sameId) {
		this.sameId = sameId;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ReviewVO [id=" + id + ", name=" + name + ", writeDate=" + writeDate + ", content=" + content + ", no="
				+ no + ", sameId=" + sameId + "]";
	}

}
