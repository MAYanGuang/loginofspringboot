package com.mayanguang.myspringboot.mapper;

import com.mayanguang.myspringboot.bean.MainBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: MA
 * @Date: 2021/11/04 18:53
 */
@Repository
public interface MainDao extends JpaRepository<MainBean, Integer> {
    /**
     * 根据名字查找
     * @param name
     * @return
     */
    @Query(value = "from MainBean where name = ?1")
    MainBean getBeanByName(String name);

    /**
     * 实现分页返回所有的用户
     * @param start
     * @param number
     * @return 分页后的每一页用户信息
     */
    @Query(value = "select * from test.springofpeople limit :start,:number", nativeQuery = true)
    List<MainBean> getAll(@Param("start") Integer start,
                          @Param("number") Integer number);

    /**
     *
     * @param name
     * @return 相同用户名的数量
     */
    @Query(value = "select count(*) from test.springofpeople where f_name=?1", nativeQuery = true)
    int sameName(@Param("name") String name);

    /**
     * 模糊查询 根据用户名
     * @param name
     * @param start
     * @param number
     * @return 符合模糊查询的所有用户+分页结果
     */
    @Query(value = "select * from  test.springofpeople where f_name like :name limit :start,:number",
            nativeQuery = true)
    List<MainBean> selectLikeUsers(@Param("name") String name,
                                   @Param("start") Integer start,
                                   @Param("number") Integer number);


}
