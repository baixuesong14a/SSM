package com.cedar.worklog.dao;

import com.cedar.worklog.entity.DayTableWork;
import org.apache.ibatis.annotations.*;


import java.util.Date;
import java.util.List;

@Mapper
public interface DTableDao {
    //增 插入一条数据
    @Insert("insert into dtablework(d_tab,d_num,d_type,d_content,d_basis,d_time,d_principal,d_way,d_remarks,c_time,user_id) " +
            "values(#{tab},#{num},#{type},#{content},#{basis},#{time},#{principal},#{way},#{remarks},#{c_time},#{user_id})")
    boolean InsertDTable(@Param("tab") int tab, @Param("num") int num, @Param("type") int type,
                         @Param("content") String content, @Param("basis") String basis, @Param("time") String time,
                         @Param("principal") String principal, @Param("way") String way, @Param("remarks") String remarks,
                         @Param("c_time") String c_time, @Param("user_id") int user_id);

    //删 根据Id删除一条数据
    @Delete("delete from dtablework where dt_id = #{id}")
    boolean DeleteDTableById(@Param("id") double id);

    //改 根据Id修改数据

    //查 根据Id查询一条数据
    @Select("select * from dtablework where dt_id = #{id}")
    DayTableWork SelectDTableById(@Param("id") double id);

    //查 查询本周全部数据

    //查 查询今日全部数据
    @Select("select * from dtablework where c_time = #{c_time} ")
    List<DayTableWork> SelectDtableByDay(@Param("c_time") String c_time);

    @Select("select * from dtablework where c_time = #{c_time} and user_id = #{userid}")
    List<DayTableWork> SelectDtableByDaI(@Param("c_time") String c_time, @Param("userid") int userid);

    @Select("select * from dtablework where d_tab = 0 and c_time = #{c_time} and user_id = #{userid}")
    List<DayTableWork> SelDTableByDIJ(@Param("c_time") String c_time, @Param("userid") int userid);

    @Select("select * from dtablework where d_tab = 1 and c_time = #{c_time} and user_id = #{userid}")
    List<DayTableWork> SelDTableByDIM(@Param("c_time") String c_time, @Param("userid") int userid);

    @Select("select count(*) from dtablework where d_tab = #{num} and c_time = #{c_time} and user_id = #{userid}")
    public int GetNumCur(@Param("c_time") String c_time, @Param("userid") int userid, @Param("num") int num);

    @Update("update dtablework set d_num = #{newnum} where dt_id = #{dt_id} ")
    public boolean UpdataNum(@Param("newnum") int newnum, @Param("dt_id") double num);

    @Select("select * from dtablework where user_id = #{user_id} and d_tab = 0 and c_time >= #{startday}")
    public List<DayTableWork>SelWeek(@Param("user_id") int user_id, @Param("startday") String startday);
}
