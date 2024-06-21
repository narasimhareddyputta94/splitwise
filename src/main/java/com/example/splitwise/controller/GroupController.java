package com.example.splitwise.controller;

import com.example.splitwise.model.Group;
import com.example.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{groupId}")
    public Optional<Group> getGroupById(@PathVariable Long groupId) {
        return groupService.getGroupById(groupId);
    }

    @PostMapping("/{groupId}/users/{userId}")
    public Group addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        return groupService.addUserToGroup(groupId, userId);
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    public Group removeUserFromGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        return groupService.removeUserFromGroup(groupId, userId);
    }

    @DeleteMapping("/{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
    }
}
