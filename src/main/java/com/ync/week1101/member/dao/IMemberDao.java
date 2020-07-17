package com.ync.week1101.member.dao;

import java.util.Map;

import com.ync.week1101.member.Member;

public interface IMemberDao {
	int memberInsert(Member member);
	Member memberSelect(Member member);
	int memberUpdate(Member member);
	int memberDelete(Member member);
}
