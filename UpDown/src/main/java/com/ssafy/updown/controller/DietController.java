package com.ssafy.updown.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.updown.model.dto.Diet;
import com.ssafy.updown.model.service.DietService;


@RestController
@RequestMapping("/updown")
public class DietController {

	@Autowired
	DietService dietService;

	// 식단 등록
	@PostMapping("/diet/upload")
	public ResponseEntity<Diet> uploadDiet(@RequestBody Diet diet) {
		dietService.uploadDiet(diet);
		return new ResponseEntity<Diet>(diet, HttpStatus.CREATED);
	}

	// 식단 수정
	@PutMapping("/diet/modify")
	public ResponseEntity<?> modifyDiet(@RequestBody Diet diet) {
		// 만약 식단이 잘 수정되었다면
		if (dietService.modifyDiet(diet)) {
			return new ResponseEntity<Diet>(diet, HttpStatus.OK);
		}
		// 식단이 잘 수정되지 않았다면
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	// 식단 삭제
	@DeleteMapping("/diet/remove/{no}")
	public ResponseEntity<Void> removeDiet(@PathVariable int no){
		dietService.removeDiet(no);
		return new ResponseEntity<Void>(HttpStatus.OK);		
	}

	// 하루 식단 기록 조회
	@GetMapping("/diet")
	public ResponseEntity<List<Diet>> getDietOneday(String loginUserId, String regDate){
		List<Diet> dietList = dietService.getDietOneday(loginUserId, regDate);
		return new ResponseEntity<List<Diet>>(dietList, HttpStatus.OK);		
	}
	
	// 하루 중 아침 식단 기록 조회
	@GetMapping("/diet/breakfast")
	public ResponseEntity<List<Diet>> getDietOnedayBreakfast(String loginUserId, String regDate){
		List<Diet> dietList = dietService.getDietOnedayBreakfast(loginUserId, regDate);
		return new ResponseEntity<List<Diet>>(dietList, HttpStatus.OK);		
	}
	
	// 하루 중 점심 식단 기록 조회
	@GetMapping("/diet/lunch")
	public ResponseEntity<List<Diet>> getDietOnedayLunch(String loginUserId, String regDate){
		List<Diet> dietList = dietService.getDietOnedayLunch(loginUserId, regDate);
		return new ResponseEntity<List<Diet>>(dietList, HttpStatus.OK);		
	}
	
	// 하루 중 저녁 식단 기록 조회
	@GetMapping("/diet/dinner")
	public ResponseEntity<List<Diet>> getDietOnedayDinner(String loginUserId, String regDate){
		List<Diet> dietList = dietService.getDietOnedayDinner(loginUserId, regDate);
		return new ResponseEntity<List<Diet>>(dietList, HttpStatus.OK);		
	}
	
	// 하루 중 간식 식단 기록 조회
	@GetMapping("/diet/snack")
	public ResponseEntity<List<Diet>> getDietOnedaySnack(String loginUserId, String regDate){
		List<Diet> dietList = dietService.getDietOnedaySnack(loginUserId, regDate);
		return new ResponseEntity<List<Diet>>(dietList, HttpStatus.OK);		
	}
		
	// 식단 하나 조회
	@GetMapping("/diet/{no}")
	public ResponseEntity<Diet> getDietOne(@PathVariable int no){
		Diet diet = dietService.getDietOne(no);
		return new ResponseEntity<Diet>(diet, HttpStatus.OK);
	}

}
