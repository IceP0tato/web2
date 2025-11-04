package example2.d02;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GoodsService {
    private final GoodsRepository goodsRepository;

    // 저장
    public GoodsDto goodsSave(GoodsDto goodsDto) {
        // Dto to Entity
        GoodsEntity entity = goodsDto.toEntity();
        // 엔티티 영속화
        GoodsEntity savedEntity = goodsRepository.save(entity);
        if (savedEntity.getGno() >= 0) {
            // PK 생성 시 생성된 엔티티를 Dto 로 반환
            return savedEntity.toDto();
        }
        return goodsDto;
    }

    // 전체 조회
    public List<GoodsDto> goodsAll() {
        List<GoodsEntity> goodsEntityList = goodsRepository.findAll();
        // Entity to Dto
        // 방법1
        /*
        List<GoodsDto> goodsDtoList = new ArrayList<>();
        for (int i=0; i<goodsEntityList.size(); i++) {
            // 엔티티를 꺼내 Dto 로 변환 후 List 에 저장
            GoodsEntity entity = goodsEntityList.get(i);
            goodsDtoList.add(entity.toDto());
        }
        // Dto List 반환
        return goodsDtoList;
        */

        // 방법2 (Stream API)
        return goodsRepository.findAll().stream()
                .map(GoodsEntity::toDto)
                .collect(Collectors.toList());
    }

    // 개별 조회
    public GoodsDto goodsGet(int gno) {
        Optional<GoodsEntity> optional = goodsRepository.findById(gno);
        if (optional.isPresent()) {
            GoodsEntity entity = optional.get();
            return entity.toDto();
        }
        return null;
    }

    // 개별 삭제
    public boolean goodsDelete(int gno) {
        // .existsById(pk) : pk 값이 존재하면 true, 없으면 false
        if (goodsRepository.existsById(gno)) {
            goodsRepository.deleteById(gno);
            return true;
        }
        return false;
    }

    // 개별 수정 (Transactional)
    public GoodsDto goodsUpdate(GoodsDto goodsDto) {
        Optional<GoodsEntity> optional = goodsRepository.findById(goodsDto.getGno());
        if (optional.isPresent()) {
            GoodsEntity entity = optional.get();
            entity.setGname(goodsDto.getGname());
            entity.setGprice(goodsDto.getGprice());
            entity.setGdesc(goodsDto.getGdesc());
            // commit 시 변경된 값이 존재할 경우 자동으로 수정 날짜 (JPA Auditing) 변경
            return entity.toDto();
        }
        return goodsDto;
    }
}
