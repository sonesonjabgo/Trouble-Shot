package com.orientalSalad.troubleShot.tag.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.orientalSalad.troubleShot.global.dto.RequestGPTDTO;
import com.orientalSalad.troubleShot.global.dto.ResponseGPTDTO;
import com.orientalSalad.troubleShot.global.dto.ResponseGPTMessageDTO;
import com.orientalSalad.troubleShot.global.dto.ResultDTO;
import com.orientalSalad.troubleShot.tag.dto.RequestMostUsedTagDTO;
import com.orientalSalad.troubleShot.tag.dto.ResponseTagListDTO;
import com.orientalSalad.troubleShot.tag.serivice.TagService;
import com.orientalSalad.troubleShot.tag.serivice.UserTagService;
import com.orientalSalad.troubleShot.troubleShooting.entity.TroubleShootingEntity;
import com.orientalSalad.troubleShot.troubleShooting.repository.TroubleShootingRepository;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/members/{userSeq}")
@RequiredArgsConstructor
@Log4j2
public class UserTagController {
	private final UserTagService userTagService;

	@Operation(summary = "유저가 많이 사용한 태그 검색")
	@GetMapping("/most-used")
	public ResponseEntity<?> MostUsedTagList(
		@ModelAttribute RequestMostUsedTagDTO requestMostUsedTagDTO) {
		log.info("====== 유저가 많이 사용한 태그 검색 시작 =====");

		List<String> tagList = userTagService.findMostUsedTagByUserSeq(requestMostUsedTagDTO);

		ResponseTagListDTO resultDTO = ResponseTagListDTO.builder()
			.success(true)
			.message("유저가 많이 사용한 태그 검색을 성공했습니다.")
			.tagList(tagList)
			.build();

		log.info("====== 유저가 많이 사용한 태그 검색 끝 =====");
		return new ResponseEntity<>(resultDTO,HttpStatus.OK);
	}
}
