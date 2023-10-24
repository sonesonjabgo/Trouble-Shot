package com.orientalSalad.troubleShot.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orientalSalad.troubleShot.global.dto.ResultDTO;
import com.orientalSalad.troubleShot.member.dto.MemberDTO;
import com.orientalSalad.troubleShot.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = "/members")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
	private final MemberService memberService;

	@PostMapping()
	public ResponseEntity<?> insertMember(@RequestBody MemberDTO memberDTO){
		log.info("==== 유저 회원가입 시작 ====");
		log.info(memberDTO);
		Boolean success = memberService.insertMember(memberDTO);
		
		//회원가입이 실패하면(이메일 중복)
		if(!success){
			ResultDTO resultDTO = ResultDTO.builder()
				.success(false)
				.message(memberDTO.getEmail()+"은 이미 존재하는 이메일 입니다.")
				.build();

			return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
		}

		//회원가입이 성공하면
		ResultDTO resultDTO = ResultDTO.builder()
			.success(true)
			.message(memberDTO.getEmail()+" 유저의 회원가입이 성공했습니다.")
			.build();

		log.info("==== 유저 회원가입 끝 ====");
		return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
	}
	@GetMapping("/{userSeq}")
	public ResponseEntity<?> findMemberBySeq (@PathVariable(name = "userSeq")Long userSeq){
		log.info("==== 유저 찾기 시작 ====");

		MemberDTO memberDTO = memberService.findMemberBySeq(userSeq);

		log.info(memberDTO);

		log.info("==== 유저 찾기 끝 ====");
		return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
	}
}