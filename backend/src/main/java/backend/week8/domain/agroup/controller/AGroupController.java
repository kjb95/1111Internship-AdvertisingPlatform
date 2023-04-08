package backend.week8.domain.agroup.controller;

import backend.week8.domain.agroup.dto.UpdateAdGroupUseConfigRequestDto;
import backend.week8.domain.agroup.dto.request.FindAdGroupRequestDto;
import backend.week8.domain.agroup.dto.request.RegisterAdGroupRequestDto;
import backend.week8.domain.agroup.dto.request.UpdateAdGroupActOffRequestDto;
import backend.week8.domain.agroup.dto.request.UpdateAdGroupNameRequestDto;
import backend.week8.domain.agroup.dto.response.FindAdGroupResponseDto;
import backend.week8.domain.agroup.dto.response.FindAllAdGroupByAdGroupNameResponseDto;
import backend.week8.domain.agroup.dto.response.FindAllAdGroupResponseDto;
import backend.week8.domain.agroup.service.AGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/agroup")
public class AGroupController {
	private final AGroupService aGroupService;

	/**
	 * 모든 광고 그룹의 아이디와 이름만 조회
	 */
	@GetMapping("/all")
	public ResponseEntity<FindAllAdGroupResponseDto> findAllAdGroup() {
		FindAllAdGroupResponseDto findAllAdGroupResponseDto = aGroupService.findAllAdGroup();
		return ResponseEntity.ok()
				.body(findAllAdGroupResponseDto);
	}

	/**
	 * 조건에 따른 광고 그룹 검색
	 */
	@GetMapping("/all/condition")
	public ResponseEntity<FindAllAdGroupByAdGroupNameResponseDto> findAllAdGroupsByAdGroupName(@RequestParam String groupName) {
		FindAllAdGroupByAdGroupNameResponseDto findAllAdGroupByAdGroupNameResponseDto = aGroupService.findAllAdGroupsByAdGroupName(groupName);
		return ResponseEntity.ok()
				.body(findAllAdGroupByAdGroupNameResponseDto);
	}

	/**
	 * 광고 그룹 한개 조회
	 */
	@PostMapping("/search")
	public ResponseEntity<FindAdGroupResponseDto> findAdGroup(@RequestBody FindAdGroupRequestDto findAdGroupRequestDto) {
		FindAdGroupResponseDto findAdGroupResponseDto = aGroupService.findAdGroup(findAdGroupRequestDto);
		return ResponseEntity.ok()
				.body(findAdGroupResponseDto);
	}


	/**
	 * 광고그룹 사용 설정 변경
	 */
	@PutMapping("/useConfig")
	public ResponseEntity<Void> updateAdGroupUseConfig(@RequestBody UpdateAdGroupUseConfigRequestDto updateAdGroupUseConfigRequestDto) {
		aGroupService.updateAdGroupUseConfig(updateAdGroupUseConfigRequestDto);
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 광고그룹 등록
	 */
	@PostMapping
	public ResponseEntity<Void> registerAdGroup(@RequestBody RegisterAdGroupRequestDto registerAdGroupRequestDto) {
		aGroupService.registerAGroupByName(registerAdGroupRequestDto.getAdGroupName());
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 광고 그룹 활성 여부 끄기
	 */
	@PutMapping("/actOff")
	public ResponseEntity<Void> updateAdGroupActOff(@RequestBody UpdateAdGroupActOffRequestDto updateAdGroupActOffRequestDto) {
		aGroupService.updateAdGroupActOff(updateAdGroupActOffRequestDto);
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 광고 그룹명 변경
	 */
	@PutMapping("/adGroupName")
	public ResponseEntity<FindAdGroupResponseDto> updateAdGroupName(@RequestBody UpdateAdGroupNameRequestDto updateAdGroupNameRequestDto) {
		aGroupService.updateAdGroupName(updateAdGroupNameRequestDto);
		return ResponseEntity.ok()
				.build();
	}

}
