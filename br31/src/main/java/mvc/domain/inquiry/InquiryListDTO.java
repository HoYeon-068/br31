package mvc.domain.inquiry;

import java.util.Date;

public class InquiryListDTO {

    private Long inquiryId;     // PK
    private Date regDate;       // 접수일
    private Date occurDate;     // 발생일시
    private String counselType;
    private String detailType;
    private String title;
    private String status;      // 등록중 / 답변완료

    public Long getInquiryId() {
        return inquiryId;
    }
    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }

    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getOccurDate() {
        return occurDate;
    }
    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    public String getCounselType() {
        return counselType;
    }
    public void setCounselType(String counselType) {
        this.counselType = counselType;
    }

    public String getDetailType() {
        return detailType;
    }
    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
