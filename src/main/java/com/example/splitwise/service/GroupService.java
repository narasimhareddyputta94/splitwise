package com.example.splitwise.service;

import com.example.splitwise.Repositories.GroupRepository;
import com.example.splitwise.Repositories.UserRepository;
import com.example.splitwise.model.GroupEntity;
import com.example.splitwise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public GroupEntity createGroup(GroupEntity group) {
        return groupRepository.save(group);
    }

    public List<GroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<GroupEntity> getGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public GroupEntity addUserToGroup(Long groupId, Long userId) {
        Optional<GroupEntity> groupOpt = groupRepository.findById(groupId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (groupOpt.isPresent() && userOpt.isPresent()) {
            GroupEntity group = groupOpt.get();
            User user = userOpt.get();
            group.getUsers().add(user);
            return groupRepository.save(group);
        }
        return null;
    }

    public GroupEntity removeUserFromGroup(Long groupId, Long userId) {
        Optional<GroupEntity> groupOpt = groupRepository.findById(groupId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (groupOpt.isPresent() && userOpt.isPresent()) {
            GroupEntity group = groupOpt.get();
            User user = userOpt.get();
            group.getUsers().remove(user);
            return groupRepository.save(group);
        }
        return null;
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }
}
