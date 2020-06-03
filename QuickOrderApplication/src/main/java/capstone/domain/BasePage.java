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

  public List<String> getPages() {
    List<String> pages = new ArrayList<>();
    for (int i = 1; i <= totalPages; i++) {
      pages.add(Integer.toString(i));
    }
    return pages;
  }

  public static class Pageable {
    public int offset;

    public int pageNumber;

    public int pageSize;
  }
}
