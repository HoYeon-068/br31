package mvc.domain.search;

import java.util.Date;

public class BoardSearchDTO {

    /* =========================
     * 공통 구분용
     * ========================= */
    private String type;     // NOTICE / PRESS

    /* =========================
     * 게시글 공통
     * ========================= */
    private int id;          // NOTICE_ID / pr_id
    private String no;       // NOTICE_NO / pr_no
    private String title;    // TITLE
    private Date regDate;    // REG_DATE

    /* =========================
     * getter / setter
     * ========================= */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
