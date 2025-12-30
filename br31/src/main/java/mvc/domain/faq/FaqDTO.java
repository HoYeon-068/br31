package mvc.domain.faq;

import java.util.Date;

public class FaqDTO {

    private int faq_id;
    private String question;
    private String answer;
    private Date reg_date;
    private int faq_category_id;
    private String category_name; // JOIN 결과용

    public int getFaq_id() { return faq_id; }
    public void setFaq_id(int faq_id) { this.faq_id = faq_id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public Date getReg_date() { return reg_date; }
    public void setReg_date(Date reg_date) { this.reg_date = reg_date; }

    public int getFaq_category_id() { return faq_category_id; }
    public void setFaq_category_id(int faq_category_id) {
        this.faq_category_id = faq_category_id;
    }

    public String getCategory_name() { return category_name; }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
