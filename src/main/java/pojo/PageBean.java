package pojo;

import java.util.List;

public class PageBean<E> {
    private  int totalCount;
    private List<E> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

}
