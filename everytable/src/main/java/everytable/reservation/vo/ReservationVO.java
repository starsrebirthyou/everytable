package everytable.reservation.vo;

public class ReservationVO {
	
	private long resNo;
	private long userId;
	private String resDate;
	private String resTime;
	private long resCount;
	private String resPhone;
	private String resStatus;
	private long storeId;
	private long totalPrice;
	private String cancelReason;
	private String resCreatedAt;
	private String resType;
	
	public long getResNo() {
		return resNo;
	}
	public void setResNo(long resNo) {
		this.resNo = resNo;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getResDate() {
		return resDate;
	}
	public void setResDate(String resDate) {
		this.resDate = resDate;
	}
	public String getResTime() {
		return resTime;
	}
	public void setResTime(String resTime) {
		this.resTime = resTime;
	}
	public long getResCount() {
		return resCount;
	}
	public void setResCount(long resCount) {
		this.resCount = resCount;
	}
	public String getResPhone() {
		return resPhone;
	}
	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}
	public String getResStatus() {
		return resStatus;
	}
	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getResCreatedAt() {
		return resCreatedAt;
	}
	public void setResCreatedAt(String resCreatedAt) {
		this.resCreatedAt = resCreatedAt;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	
	@Override
	public String toString() {
		return "ReservationVO [resNo=" + resNo + ", userId=" + userId + ", resDate=" + resDate + ", resTime=" + resTime
				+ ", resCount=" + resCount + ", resPhone=" + resPhone + ", resStatus=" + resStatus + ", storeId="
				+ storeId + ", totalPrice=" + totalPrice + ", cancelReason=" + cancelReason + ", resCreatedAt="
				+ resCreatedAt + ", resType=" + resType + "]";
	}

}
