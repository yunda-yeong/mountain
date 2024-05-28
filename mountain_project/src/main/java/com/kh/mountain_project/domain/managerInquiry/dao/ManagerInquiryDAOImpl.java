package com.kh.mountain_project.domain.managerInquiry.dao;

import com.kh.mountain_project.domain.entity.ManagerInquiry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class ManagerInquiryDAOImpl implements ManagerInquiryDAO{
  private final NamedParameterJdbcTemplate template;
  ManagerInquiryDAOImpl(NamedParameterJdbcTemplate template){
    this.template = template;
  }
  @Override
  public List<ManagerInquiry> viewInquiryAll() {
    String query = " SELECT " +
            " mb.member_ID as member_id, " +
            " mb.id as member_email, " +
            " mb.NICKNAME AS member_nickname, " +
            " m.INQUIRY_ID AS inquiry_id, " +
            " m.INQUIRY_TITLE AS inquiry_title, " +
            " m.INQUIRY_CONTENT AS inquiry_content, " +
            " m.INQUIRY_COMMENT AS inquiry_comment, " +
            " m.INQUIRY_STATE AS inquiry_state, " +
            " m.INQUIRY_CDATE AS inquiry_cdate, " +
            " m.INQUIRY_UDATE AS inquiry_udate, " +
            " m.INQUIRY_COMMENT_CDATE AS inquiry_comment_cdate, " +
            " m.INQUIRY_COMMENT_UDATE AS inquiry_comment_udate " +
            " FROM " +
            " manager m " +
            " LEFT JOIN " +
            " member mb ON m.MEMBER_ID = mb.MEMBER_ID " +
            " order by INQUIRY_CDATE desc " ;

    List<ManagerInquiry> list = template.query(query, (ResultSet rs) -> {
      List<ManagerInquiry> resultList = new ArrayList<>();
      while (rs.next()) {
        ManagerInquiry managerInquiry = new ManagerInquiry();
        managerInquiry.setInquiryId(rs.getLong("inquiry_id"));
        managerInquiry.setMemberId(rs.getLong("member_id"));
        managerInquiry.setInquiryTitle(rs.getString("inquiry_title"));
        managerInquiry.setInquiryContent(rs.getString("inquiry_content"));
        managerInquiry.setInquiryComment(rs.getString("inquiry_comment"));
        managerInquiry.setInquiryState(rs.getString("inquiry_state"));
        Timestamp inquiryCdateTimestamp = rs.getTimestamp("inquiry_cdate");
        managerInquiry.setInquiryCdate(inquiryCdateTimestamp != null ? inquiryCdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryUdateTimestamp = rs.getTimestamp("inquiry_udate");
        managerInquiry.setInquiryUdate(inquiryUdateTimestamp != null ? inquiryUdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryCommentCdateTimestamp = rs.getTimestamp("inquiry_comment_cdate");
        managerInquiry.setInquiryCommentCdate(inquiryCommentCdateTimestamp != null ? inquiryCommentCdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryCommentUdateTimestamp = rs.getTimestamp("inquiry_comment_udate");
        managerInquiry.setInquiryCommentUdate(inquiryCommentUdateTimestamp != null ? inquiryCommentUdateTimestamp.toLocalDateTime() : null);
        managerInquiry.setNickname(rs.getString("member_nickname"));
        resultList.add(managerInquiry);
      }
      return resultList;
    });
    return list;
  }

  //문의글 검색

  // 처리 전인 상태(status => null) 조회
  @Override
  public List<ManagerInquiry> viewInquiryNull() {
    String query = " SELECT " +
            " mb.member_ID as member_id, " +
            " mb.id as member_email, " +
            " mb.NICKNAME AS member_nickname, " +
            " m.INQUIRY_ID AS inquiry_id, " +
            " m.INQUIRY_TITLE AS inquiry_title, " +
            " m.INQUIRY_CONTENT AS inquiry_content, " +
            " m.INQUIRY_COMMENT AS inquiry_comment, " +
            " m.INQUIRY_STATE AS inquiry_state, " +
            " m.INQUIRY_CDATE AS inquiry_cdate, " +
            " m.INQUIRY_UDATE AS inquiry_udate, " +
            " m.INQUIRY_COMMENT_CDATE AS inquiry_comment_cdate, " +
            " m.INQUIRY_COMMENT_UDATE AS inquiry_comment_udate " +
            " FROM " +
            " manager m " +
            " LEFT JOIN " +
            " member mb ON m.MEMBER_ID = mb.MEMBER_ID " +
            " where INQUIRY_STATE is null " +
            " order by INQUIRY_CDATE desc " ;

    List<ManagerInquiry> list = template.query(query, (ResultSet rs) -> {
      List<ManagerInquiry> resultList = new ArrayList<>();
      while (rs.next()) {
        ManagerInquiry managerInquiry = new ManagerInquiry();
        managerInquiry.setMemberemail(rs.getString("member_email"));
        managerInquiry.setNickname(rs.getString("member_nickname"));
        managerInquiry.setMemberId(rs.getLong("member_id"));
        managerInquiry.setInquiryId(rs.getLong("inquiry_id"));
        managerInquiry.setInquiryTitle(rs.getString("inquiry_title"));
        managerInquiry.setInquiryContent(rs.getString("inquiry_content"));
        managerInquiry.setInquiryComment(rs.getString("inquiry_comment"));
        managerInquiry.setInquiryState(rs.getString("inquiry_state"));
        Timestamp inquiryCdateTimestamp = rs.getTimestamp("inquiry_cdate");
        managerInquiry.setInquiryCdate(inquiryCdateTimestamp != null ? inquiryCdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryUdateTimestamp = rs.getTimestamp("inquiry_udate");
        managerInquiry.setInquiryUdate(inquiryUdateTimestamp != null ? inquiryUdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryCommentCdateTimestamp = rs.getTimestamp("inquiry_comment_cdate");
        managerInquiry.setInquiryCommentCdate(inquiryCommentCdateTimestamp != null ? inquiryCommentCdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryCommentUdateTimestamp = rs.getTimestamp("inquiry_comment_udate");
        managerInquiry.setInquiryCommentUdate(inquiryCommentUdateTimestamp != null ? inquiryCommentUdateTimestamp.toLocalDateTime() : null);
        resultList.add(managerInquiry);
      }
      return resultList;
    });
    return list;
  }

  @Override
  public List<ManagerInquiry> viewInquiryComplete() {
    String query = " SELECT " +
            " mb.member_ID as member_id, " +
            " mb.id as member_email, " +
            " mb.NICKNAME AS member_nickname, " +
            " m.INQUIRY_ID AS inquiry_id, " +
            " m.INQUIRY_TITLE AS inquiry_title, " +
            " m.INQUIRY_CONTENT AS inquiry_content, " +
            " m.INQUIRY_COMMENT AS inquiry_comment, " +
            " m.INQUIRY_STATE AS inquiry_state, " +
            " m.INQUIRY_CDATE AS inquiry_cdate, " +
            " m.INQUIRY_UDATE AS inquiry_udate, " +
            " m.INQUIRY_COMMENT_CDATE AS inquiry_comment_cdate, " +
            " m.INQUIRY_COMMENT_UDATE AS inquiry_comment_udate " +
            " FROM " +
            " manager m " +
            " LEFT JOIN " +
            " member mb ON m.MEMBER_ID = mb.MEMBER_ID " +
            " where INQUIRY_STATE = 'C' " +
            " order by INQUIRY_CDATE desc " ;

    List<ManagerInquiry> list = template.query(query, (ResultSet rs) -> {
      List<ManagerInquiry> resultList = new ArrayList<>();
      while (rs.next()) {
        ManagerInquiry managerInquiry = new ManagerInquiry();
        managerInquiry.setMemberemail(rs.getString("member_email"));
        managerInquiry.setNickname(rs.getString("member_nickname"));
        managerInquiry.setMemberId(rs.getLong("member_id"));
        managerInquiry.setInquiryId(rs.getLong("inquiry_id"));
        managerInquiry.setInquiryTitle(rs.getString("inquiry_title"));
        managerInquiry.setInquiryContent(rs.getString("inquiry_content"));
        managerInquiry.setInquiryComment(rs.getString("inquiry_comment"));
        managerInquiry.setInquiryState(rs.getString("inquiry_state"));
        Timestamp inquiryCdateTimestamp = rs.getTimestamp("inquiry_cdate");
        managerInquiry.setInquiryCdate(inquiryCdateTimestamp != null ? inquiryCdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryUdateTimestamp = rs.getTimestamp("inquiry_udate");
        managerInquiry.setInquiryUdate(inquiryUdateTimestamp != null ? inquiryUdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryCommentCdateTimestamp = rs.getTimestamp("inquiry_comment_cdate");
        managerInquiry.setInquiryCommentCdate(inquiryCommentCdateTimestamp != null ? inquiryCommentCdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryCommentUdateTimestamp = rs.getTimestamp("inquiry_comment_udate");
        managerInquiry.setInquiryCommentUdate(inquiryCommentUdateTimestamp != null ? inquiryCommentUdateTimestamp.toLocalDateTime() : null);
        resultList.add(managerInquiry);
      }
      return resultList;
    });
    return list;
  }

  @Override
  public List<ManagerInquiry> viewInquiryProgress() {
    String query = " SELECT " +
            " mb.member_ID as member_id, " +
            " mb.id as member_email, " +
            " mb.NICKNAME AS member_nickname, " +
            " m.INQUIRY_ID AS inquiry_id, " +
            " m.INQUIRY_TITLE AS inquiry_title, " +
            " m.INQUIRY_CONTENT AS inquiry_content, " +
            " m.INQUIRY_COMMENT AS inquiry_comment, " +
            " m.INQUIRY_STATE AS inquiry_state, " +
            " m.INQUIRY_CDATE AS inquiry_cdate, " +
            " m.INQUIRY_UDATE AS inquiry_udate, " +
            " m.INQUIRY_COMMENT_CDATE AS inquiry_comment_cdate, " +
            " m.INQUIRY_COMMENT_UDATE AS inquiry_comment_udate " +
            " FROM " +
            " manager m " +
            " LEFT JOIN " +
            " member mb ON m.MEMBER_ID = mb.MEMBER_ID " +
            " where INQUIRY_STATE = 'P' " +
            " order by INQUIRY_CDATE desc " ;

    List<ManagerInquiry> list = template.query(query, (ResultSet rs) -> {
      List<ManagerInquiry> resultList = new ArrayList<>();
      while (rs.next()) {
        ManagerInquiry managerInquiry = new ManagerInquiry();
        managerInquiry.setMemberemail(rs.getString("member_email"));
        managerInquiry.setNickname(rs.getString("member_nickname"));
        managerInquiry.setMemberId(rs.getLong("member_id"));
        managerInquiry.setInquiryId(rs.getLong("inquiry_id"));
        managerInquiry.setInquiryTitle(rs.getString("inquiry_title"));
        managerInquiry.setInquiryContent(rs.getString("inquiry_content"));
        managerInquiry.setInquiryComment(rs.getString("inquiry_comment"));
        managerInquiry.setInquiryState(rs.getString("inquiry_state"));
        Timestamp inquiryCdateTimestamp = rs.getTimestamp("inquiry_cdate");
        managerInquiry.setInquiryCdate(inquiryCdateTimestamp != null ? inquiryCdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryUdateTimestamp = rs.getTimestamp("inquiry_udate");
        managerInquiry.setInquiryUdate(inquiryUdateTimestamp != null ? inquiryUdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryCommentCdateTimestamp = rs.getTimestamp("inquiry_comment_cdate");
        managerInquiry.setInquiryCommentCdate(inquiryCommentCdateTimestamp != null ? inquiryCommentCdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryCommentUdateTimestamp = rs.getTimestamp("inquiry_comment_udate");
        managerInquiry.setInquiryCommentUdate(inquiryCommentUdateTimestamp != null ? inquiryCommentUdateTimestamp.toLocalDateTime() : null);
        resultList.add(managerInquiry);
      }
      return resultList;
    });
    return list;
  }

  @Override
  public Optional<ManagerInquiry> mViewByInquiryId(Long inquiryId) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select ");
    sql.append(" mb.member_ID AS member_id, ");
    sql.append(" mb.id AS member_email, ");
    sql.append(" mb.NICKNAME AS member_nickname, ");
    sql.append(" m.INQUIRY_ID AS inquiry_id, ");
    sql.append(" m.INQUIRY_TITLE AS inquiry_title, ");
    sql.append(" m.INQUIRY_CONTENT AS inquiry_content, ");
    sql.append(" m.INQUIRY_COMMENT AS inquiry_comment, ");
    sql.append(" m.INQUIRY_STATE AS inquiry_state, ");
    sql.append(" m.INQUIRY_CDATE AS inquiry_cdate, ");
    sql.append(" m.INQUIRY_UDATE AS inquiry_udate, ");
    sql.append(" m.INQUIRY_COMMENT_CDATE AS inquiry_comment_cdate, ");
    sql.append(" m.INQUIRY_COMMENT_UDATE AS inquiry_comment_udate ");
    sql.append(" FROM  ");
    sql.append(" manager m ");
    sql.append(" LEFT JOIN ");
    sql.append(" member mb ON m.MEMBER_ID = mb.MEMBER_ID ");
    sql.append(" WHERE ");
    sql.append(" inquiry_id = :inquiryId ");
    try {
      Map<String,Long> map = Map.of("inquiryId",inquiryId);
      ManagerInquiry managerInquiry = template.queryForObject(sql.toString(), map, BeanPropertyRowMapper.newInstance(ManagerInquiry.class));
      return Optional.of(managerInquiry);

    }catch (EmptyResultDataAccessException e){
      //조회결과가 없는경우
      return Optional.empty();
    }
  }
}
