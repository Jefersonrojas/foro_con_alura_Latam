package com.forohub.controller;

import com.forohub.dto.TopicRequest;
import com.forohub.dto.TopicResponse;
import com.forohub.dto.UpdateTopicRequest;
import com.forohub.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TopicResponse> create(@Valid @RequestBody TopicRequest req) {
        TopicResponse resp = service.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping
    public Page<TopicResponse> all(@PageableDefault(size = 10, sort = "createdAt,desc") Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public TopicResponse one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public TopicResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTopicRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
