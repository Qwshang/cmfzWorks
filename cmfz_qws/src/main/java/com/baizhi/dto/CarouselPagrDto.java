package com.baizhi.dto;
import com.baizhi.entity.Carousel;
import java.io.Serializable;
import java.util.List;

public class CarouselPagrDto implements Serializable {
    private Integer page;//当前页
    private Integer total;//总页数
    private Integer records;//总行数
    private List<Carousel> rows;//该页的数据结果集

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public List<Carousel> getRows() {
        return rows;
    }

    public void setRows(List<Carousel> rows) {
        this.rows = rows;
    }
}
