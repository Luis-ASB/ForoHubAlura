package com.aluralatam.foroalura.service;

import com.aluralatam.foroalura.model.Topic;
import com.aluralatam.foroalura.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    public Topic updateTopic(Long id, Topic topicDetails) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic not found for this id :: " + id));
        topic.setTitulo(topicDetails.getTitulo());
        topic.setMensaje(topicDetails.getMensaje());
        topic.setStatus(topicDetails.getStatus());
        topic.setAutor(topicDetails.getAutor());
        topic.setCurso(topicDetails.getCurso());
        return topicRepository.save(topic);
    }

    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
}
