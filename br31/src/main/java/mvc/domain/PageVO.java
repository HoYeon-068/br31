package mvc.domain;

import lombok.Getter;

@Getter
public class PageVO {
	
	private final int currentPage;
    private final int numberPerPage;
    private final int numberOfPageBlock;
    private final int totalCount;
    private final int totalPages;

    private final int start;
    private final int end;
    private final boolean prev;
    private final boolean next;

    private final int offset;

    public PageVO(int currentPage, int numberPerPage, int numberOfPageBlock, int totalCount) {
        this.currentPage = Math.max(currentPage, 1);
        this.numberPerPage = numberPerPage;
        this.numberOfPageBlock = numberOfPageBlock;
        this.totalCount = totalCount;

        this.totalPages = (int) Math.ceil((double) totalCount / numberPerPage);

        int s = (this.currentPage - 1) / numberOfPageBlock * numberOfPageBlock + 1;
        int e = s + numberOfPageBlock - 1;
        if (e > totalPages) e = totalPages;

        this.start = s;
        this.end = e;
        this.prev = (start > 1);
        this.next = (end < totalPages);

        this.offset = (this.currentPage - 1) * numberPerPage;
    }
    
}
