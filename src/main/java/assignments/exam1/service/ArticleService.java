package assignments.exam1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import assignments.exam1.model.dto.ArticleDto;
import assignments.exam1.model.mapper.ArticleMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleMapper articleMapper;

    public boolean postArticle(ArticleDto articleDto) {
        return articleMapper.postArticle(articleDto);
    }

    public boolean deleteArticle(int aNo, String aPwd) {
        return articleMapper.deleteArticle(aNo, aPwd);
    }

    public List<ArticleDto> getArticle(int mNo) {
        return articleMapper.getArticle(mNo);
    }

    public ArticleDto findArticle(int aNo) {
        return articleMapper.findArticle(aNo);
    }
}
