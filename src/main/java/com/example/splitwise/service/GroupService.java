package com.example.splitwise.service;

import com.example.splitwise.Repositories.GroupRepository;
import com.example.splitwise.Repositories.UserRepository;
import com.example.splitwise.model.Group;
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

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public Group addUserToGroup(Long groupId, Long userId) {
        Optional<Group> groupOpt = groupRepository.findById(groupId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (groupOpt.isPresent() && userOpt.isPresent()) {
            Group group = groupOpt.get();
            User user = userOpt.get();
            group.getUsers().add(user);
            return groupRepository.save(group);
        }
        return null;
    }

    public Group removeUserFromGroup(Long groupId, Long userId) {
        Optional<Group> groupOpt = groupRepository.findById(groupId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (groupOpt.isPresent() && userOpt.isPresent()) {
            Group group = groupOpt.get();
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
