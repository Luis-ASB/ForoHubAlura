package com.aluralatam.foroalura.controller;

import com.aluralatam.foroalura.exception.ResourceNotFoundException;
import com.aluralatam.foroalura.model.Topic;
import com.aluralatam.foroalura.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public Topic createTopic(@Valid @RequestBody Topic topic) {
        return topicService.createTopic(topic);
    }

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable(value = "id") Long topicId) {
        Topic topic = topicService.getTopicById(topicId).orElseThrow(() -> new ResourceNotFoundException("Topic not found for this id :: " + topicId));
        return ResponseEntity.ok().body(topic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable(value = "id") Long topicId, @Valid @RequestBody Topic topicDetails) {
        Topic updatedTopic = topicService.updateTopic(topicId, topicDetails);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable(value = "id") Long topicId) {
        topicService.deleteTopic(topicId);
        return ResponseEntity.noContent().build();
    }
}
