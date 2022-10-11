package kr.or.ddit.sample.dao;

public class SampleDAOFactory {
	public SampleDAO getsampleDAO() {
		
		return new SampleDAOImpl_MariaDB();
	}

}
