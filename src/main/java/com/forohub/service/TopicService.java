package com.forohub.service;

import com.forohub.dto.TopicRequest;
import com.forohub.dto.TopicResponse;
import com.forohub.dto.UpdateTopicRequest;
import com.forohub.entity.Topic;
import com.forohub.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TopicResponse create(TopicRequest req) {
        if (repository.existsByTitleAndMessage(req.getTitle(), req.getMessage())) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
        }
        Topic topic = new Topic(req.getTitle(), req.getMessage(), req.getAuthor(), req.getCourse());
        Topic saved = repository.save(topic);
        return toResponse(saved);
    }

    public Page<TopicResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public TopicResponse findById(Long id) {
        Topic t = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));
        return toResponse(t);
    }

    @Transactional
    public TopicResponse update(Long id, UpdateTopicRequest req) {
        Topic t = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));
        t.setTitle(req.getTitle());
        t.setMessage(req.getMessage());
        t.setStatus(req.getStatus());
        return toResponse(t);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Tópico no encontrado");
        }
        repository.deleteById(id);
    }

    private TopicResponse toResponse(Topic t) {
        return new TopicResponse(
            t.getId(), t.getTitle(), t.getMessage(), t.getCreatedAt(), t.getStatus(), t.getAuthor(), t.getCourse()
        );
    }
}
