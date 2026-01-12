package mvc.domain.admin_faq;

import java.util.Date;

public class AdminFaqDTO {

    private Long faqId;
    private String question;
    private String answer;
    private Date regDate;
    private Long faqCategoryId;

    public Long getFaqId() {
        return faqId;
    }

    public void setFaqId(Long faqId) {
        this.faqId = faqId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Long getFaqCategoryId() {
        return faqCategoryId;
    }

    public void setFaqCategoryId(Long faqCategoryId) {
        this.faqCategoryId = faqCategoryId;
    }
}
