package com.oocl.leader.controllers;


import com.oocl.leader.controllers.DTO.LeaderDTO;
import com.oocl.leader.entities.Leader;
import com.oocl.leader.repositories.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/leaders")
public class LeaderController {

    private LeaderRepository leaderRepository;

    @Autowired
    public  LeaderController(LeaderRepository leaderRepository){
        this.leaderRepository = leaderRepository;
    }

    @Transactional
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public LeaderDTO saveLeader(@RequestBody Leader leader){
        return new LeaderDTO(leaderRepository.save(leader));
    }

    @Transactional
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LeaderDTO> getAllLeaders(){
        List<LeaderDTO> leaders = new ArrayList<>();
        leaderRepository.findAll().stream().forEach(leader -> leaders.add(new LeaderDTO(leader)));
        return leaders;
    }
}
