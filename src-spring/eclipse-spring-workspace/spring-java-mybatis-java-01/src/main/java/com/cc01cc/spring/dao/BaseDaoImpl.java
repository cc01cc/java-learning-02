//package cn.edu.zjweu.spring.dao;
//
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.stereotype.Repository;
//
//import cn.edu.zjweu.spring.pojo.User;
//
//
////@Repository
//@Mapper
//public class BaseDaoImpl implements BaseDao{
//
//    private SqlSession sqlSession;
//
//    public void setSqlSession(SqlSession sqlSession) {
//      this.sqlSession = sqlSession;
//    }
//    
////  @Select("select pk_user_id, user_password from user_account where pk_user_id=#{userId} and user_password=#{userPassword}")
//  public User findByIdAndPassword(User user) {
//      return sqlSession.selectOne("cn.zjweu.spring.mapper.UserMapper.findByIdAndPassword", user);
//  }
////  @Insert("insert into busertable(pk_user_id,user_password) value (#{userId},#{userPassword})")
//  public int addUser(User user) {
//      return sqlSession.insert("mappser.UserMapper.insert", user);
//  }
////  @Select("select pk_user_id from user_account where user_id=#{userId}")
//  public User findById(User user) {
//      return sqlSession.selectOne("cn.zjweu.spring.mapper.UserMapper.findById", user);
//  }
//}
