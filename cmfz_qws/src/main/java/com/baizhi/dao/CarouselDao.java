package com.baizhi.dao;
import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CarouselDao {
    //插入一条数据
    void insert(Carousel carousel);

    //删除一条数据
    void delete(String id);

    //修改一条数据
    void update(Carousel carousel);

    //查询一条数据，根据ID
    Carousel selectById(String id);

    // 总行数
    Integer selectTotalCount();

    // 当前页的数据行
    List<Carousel> selectByPage(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    //修改图片路径
    void updateimgPath(Carousel carousel);
}
