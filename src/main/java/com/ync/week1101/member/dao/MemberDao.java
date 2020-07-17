package com.ync.week1101.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ync.week1101.member.Member;

@Repository
public class MemberDao implements IMemberDao {
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userid = "c##1505101";
	private String userpw = "1505101";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs;
	
	//private HashMap<String, Member> dbMap;
	
	public MemberDao() {
		//dbMap = new HashMap<String, Member>();
	}	

	@Override
	public int memberInsert(Member member) {
		int result = 0;
		//dbMap.put(member.getMemId(), member);
		//return dbMap;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,userid,userpw);
			String sql = "INSERT INTO member (memId, memPw, memMail) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			pstmt.setString(3, member.getMemMail());
			result = pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}

	@Override
	public Member memberSelect(Member member) {
		Member mem = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,userid,userpw);
			String sql = "SELECT * FROM member WHERE memId = ? AND memPw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String memId = rs.getString("memid");
				String memPw = rs.getString("mempw");
				String memMail = rs.getString("memMail");
				
				mem = new Member();
				mem.setMemId(memId);
				mem.setMemPw(memPw);
				mem.setMemMail(memMail);
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mem;	
	}

	@Override
	public int memberUpdate(Member member) {

		int result = 0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,userid,userpw);
			String sql = "UPDATE member SET memPw = ?, memMail = ? WHERE memId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemPw());
			pstmt.setString(2, member.getMemMail());
			pstmt.setString(3, member.getMemId());
			result = pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;	
	}

	@Override
	public int memberDelete(Member member) {
		
		int result = 0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,userid,userpw);
			String sql = "DELETE member WHERE memId = ? AND memPw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			result = pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;	
	}

}
