package com.kh.mountain_project.domain.managerBbs.svc;

import com.kh.mountain_project.domain.entity.ManagerBbs;
import com.kh.mountain_project.domain.managerBbs.dao.ManagerBbsDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ManagerBbsSVSImpl implements ManagerBbsSVC{
  private ManagerBbsDAO managerBbsDAO;
  ManagerBbsSVSImpl(ManagerBbsDAO managerBbsDAO){
    this.managerBbsDAO = managerBbsDAO;
  }
  @Override
  public List<ManagerBbs> mReadAll() {
    return managerBbsDAO.mReadAll();
  }

  @Override
  public List<ManagerBbs> mReadComplain() {
    return managerBbsDAO.mReadComplain();
  }

  @Override
  public int deleteComplain(List<Long> bbsIds) {
    return managerBbsDAO.deleteComplain(bbsIds);
  }

  @Override
  public List<ManagerBbs> viewBbsAll() {
    return managerBbsDAO.viewBbsAll();
  }

  @Override
  public List<ManagerBbs> searchByTitle(String title) {
    return managerBbsDAO.searchByTitle(title);
  }

  @Override
  public List<ManagerBbs> searchBymntnNm(String mntnNm) {
    return managerBbsDAO.searchBymntnNm(mntnNm);
  }

  @Override
  public List<ManagerBbs> searchBynickname(String nickname) {
    return managerBbsDAO.searchBynickname(nickname);
  }

  @Override
  public List<ManagerBbs> searchByAll(String keyword) {
    return managerBbsDAO.searchByAll(keyword);
  }
}
