package mvc.domain.press;

import java.util.Date;

public class PressDTO {

    private int pr_id;
    private String pr_no;
    private String title;
    private String content;
    private Date reg_date;

    public int getPr_id() {
        return pr_id;
    }
    public void setPr_id(int pr_id) {
        this.pr_id = pr_id;
    }

    public String getPr_no() {
        return pr_no;
    }
    public void setPr_no(String pr_no) {
        this.pr_no = pr_no;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Date getReg_date() {
        return reg_date;
    }
    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }
}
