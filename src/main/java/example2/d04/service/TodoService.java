package example2.d04.service;

import example2.d04.model.dto.TodoDto;
import example2.d04.model.entity.TodoEntity;
import example2.d04.model.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    public List<TodoDto> query1(String title) {
        // 쿼리 메소드
        List<TodoEntity> result1 = todoRepository.findByTitle(title);
        System.out.println("result1 = " + result1);

        // 네이티브 쿼리 메소드
        List<TodoEntity> result2 = todoRepository.query1(title);
        System.out.println("result2 = " + result2);

        return result2.stream()
                .map(TodoEntity::toDto)
                .collect(Collectors.toList());
    }

    public List<TodoDto> query2(String title, String content) {
        List<TodoEntity> result1 = todoRepository.findByTitleAndContent(title, content);
        System.out.println("result1 = " + result1);

        List<TodoEntity> result2 = todoRepository.query2(title, content);
        System.out.println("result2 = " + result2);

        return result2.stream()
                .map(TodoEntity::toDto)
                .collect(Collectors.toList());
    }

    public List<TodoDto> query3(String title) {
        List<TodoEntity> result1 = todoRepository.findByTitleContaining(title);
        System.out.println("result1 = " + result1);

        List<TodoEntity> result2 = todoRepository.query3(title);
        System.out.println("result2 = " + result2);

        return result2.stream()
                .map(TodoEntity::toDto)
                .collect(Collectors.toList());
    }

    // Spring Page
    public Page<TodoDto> page(int page, int size) {
        // page-1: 조회할 페이지는 0부터 시작함 -> 1페이지가 0이니 -1 처리
        // size: 현재 페이지에 조회할 자료 개수
        // Sort.by(Sort.Direction.DESC, "id"): id 속성으로 내림차순 정렬
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<TodoEntity> result = todoRepository.findAll(pageRequest); // .findAll(페이징 객체)

        // Page 타입은 기본적으로 stream 제공
        return result.map(TodoEntity::toDto);
    }

    public Page<TodoDto> page2(String keyword, int page, int size) {
        Page<TodoEntity> result;
        Pageable pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC, "id"));

        if (keyword == null || keyword.isEmpty()) {
            // 검색이 없을 경우 전체 조회
            result = todoRepository.findAll(pageable);
        } else {
            // 검색이 있을 경우 검색 조회
            result = todoRepository.findByTitleContaining(keyword, pageable);
        }

        return result.map(TodoEntity::toDto);
    }
}
