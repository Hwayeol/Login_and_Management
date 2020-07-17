package com.ync.week1101.member.service;

import com.ync.week1101.member.Member;

public interface IMemberService {
	void memberRegister(Member member);
	Member memberSearch(Member member);
	Member memberModify(Member member);
	int memberRemove(Member member);
}
