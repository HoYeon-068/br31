package mvc.domain.inquiry;

import java.util.Date;

public class InquiryViewDTO {

    private Long inquiryId;   // ← Long으로 통일

    private String counselType;
    private String detailType;
    private String title;
    private Date occurDate;
    private Date regDate;

    private String content;   // 문의 내용
    private String answer;    // 관리자 답변 (nullable)

    /* ===== getter / setter ===== */

    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
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

    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
