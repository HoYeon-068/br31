package mvc.domain.faq;

public class FaqCategoryDTO {

    private int faq_category_id;
    private String name;

    public int getFaq_category_id() { return faq_category_id; }
    public void setFaq_category_id(int faq_category_id) {
        this.faq_category_id = faq_category_id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
