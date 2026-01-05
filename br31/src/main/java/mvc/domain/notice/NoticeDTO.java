package mvc.domain.notice;

import java.util.Date;

public class NoticeDTO {

    private int NOTICE_ID;
    private String NOTICE_NO;
    private String TITLE;
    private String CONTENT;   // CLOB (HTML img 태그 포함)
    private Date REG_DATE;

    public int getNOTICE_ID() {
        return NOTICE_ID;
    }
    public void setNOTICE_ID(int nOTICE_ID) {
        NOTICE_ID = nOTICE_ID;
    }

    public String getNOTICE_NO() {
        return NOTICE_NO;
    }
    public void setNOTICE_NO(String nOTICE_NO) {
        NOTICE_NO = nOTICE_NO;
    }

    public String getTITLE() {
        return TITLE;
    }
    public void setTITLE(String tITLE) {
        TITLE = tITLE;
    }

    public String getCONTENT() {
        return CONTENT;
    }
    public void setCONTENT(String cONTENT) {
        CONTENT = cONTENT;
    }

    public Date getREG_DATE() {
        return REG_DATE;
    }
    public void setREG_DATE(Date rEG_DATE) {
        REG_DATE = rEG_DATE;
    }
}
