package assignments.flutter_ex1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoDto createTodo(TodoDto todoDto) {
        TodoEntity entity = todoDto.toEntity();
        TodoEntity savedEntity = todoRepository.save(entity);

        if (savedEntity.getId() >= 0) {
            return savedEntity.toDto();
        }
        return todoDto;
    }

    public List<TodoDto> getTodoList() {
        return todoRepository.findAll().stream()
                .map(TodoEntity::toDto)
                .collect(Collectors.toList());
    }
}
