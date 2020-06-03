package capstone.domain;

import java.util.ArrayList;
import java.util.List;

public class BasePage {
  public Pageable pageable;

  public int totalElements;

  public int totalPages;

  public int size;

  public boolean last;

  public boolean first;

  public int numberOfElements;

  public int number;

  public List<Integer> getPages() {
    List<Integer> pages = new ArrayList<>();
    for (int i = 1; i <= totalPages; i++) {
      pages.add(i);
    }
    return pages;
  }

  public boolean isCurrentPage(int page) {
    return pageable.pageNumber == page - 1;
  }

  public static class Pageable {
    public int offset;

    public int pageNumber;

    public int pageSize;
  }
}
