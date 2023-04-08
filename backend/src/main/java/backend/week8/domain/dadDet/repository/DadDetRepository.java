package backend.week8.domain.dadDet.repository;

import backend.week8.domain.dadDet.dto.response.AdCheckListResponseDto;
import backend.week8.domain.dadDet.dto.response.AdStatusResponseDto;
import backend.week8.domain.dadDet.entity.DadDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DadDetRepository extends JpaRepository<DadDet, Long> {
	@Modifying(clearAutomatically = true)
	@Query("UPDATE DadDet d SET d.dadUseConfigYn=:useConfig WHERE d.ad.adId IN (SELECT a.adId FROM Ad a WHERE a.item.itemId IN(:itemIds))")
	void updateUseConfigByItemIds(List<Long> itemIds, int useConfig);

	@Query("SELECT d FROM DadDet d JOIN FETCH d.kwd k WHERE d.ad.adId IN (SELECT a.adId FROM Ad a WHERE a.item.itemId=:itemId) AND k.kwdName LIKE %:keywordName% AND d.dadActYn=1")
	List<DadDet> findDadDetInItemLikeKeywordName(long itemId, String keywordName);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE DadDet d SET d.dadUseConfigYn=:useConfig WHERE d.kwd.kwdId IN :kwdIds")
	void updateUseConfigByKwdIds(List<Long> kwdIds, int useConfig);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE DadDet d SET d.dadActYn=0 WHERE d.kwd.kwdId IN :kwdIds")
	void updateDadDetActOff(List<Long> kwdIds);

	@Query("SELECT new backend.week8.domain.dadDet.dto.response.AdCheckListResponseDto(d.dadDetId, a.item.itemName, d.kwd.kwdName) " +
			"FROM DadDet d JOIN d.ad a JOIN d.kwd k JOIN d.cnrReq c " +
			"WHERE k.kwdName LIKE %:kwdName% AND c.cnrIngStatus= 'REQ'")
	List<AdCheckListResponseDto> findAllAdCheckList(String kwdName);

	@Query("SELECT new backend.week8.domain.dadDet.dto.response.AdStatusResponseDto(d.dadDetId, i.itemName, k.kwdName, i.adultYn) " +
			"FROM DadDet d JOIN d.ad a JOIN a.item i JOIN d.kwd k JOIN a.agroup ag JOIN d.cnrReq c " +
			"WHERE c.cnrIngStatus='APPROVAL' AND d.dadActYn=1 AND d.dadUseConfigYn=1 AND a.adActYn=1 AND a.adUseConfigYn=1 AND ag.agroupActYn=1 AND ag.agroupUseConfigYn=1 " +
			"ORDER BY d.dadDetId")
	List<AdStatusResponseDto> findAdStatus();

	@Query("SELECT a.adv.advId FROM DadDet d JOIN d.ad a WHERE d.dadDetId=:dadDetId")
	String findAdvIdByDadDetId(Long dadDetId);
}