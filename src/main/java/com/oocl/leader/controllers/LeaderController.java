package com.oocl.leader.controllers;


import com.oocl.leader.controllers.DTO.LeaderDTO;
import com.oocl.leader.entities.Klass;
import com.oocl.leader.entities.Leader;
import com.oocl.leader.exceptions.BadRequestException;
import com.oocl.leader.exceptions.ResourceNotFoundException;
import com.oocl.leader.repositories.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Transactional
    @GetMapping(path = "/{leaderID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LeaderDTO getLeaderById(@PathVariable Long leaderID){
        Leader leader = leaderRepository.findById(leaderID).orElseThrow(()->new ResourceNotFoundException("leader not found"));
        return new LeaderDTO(leader);
    }

    @Transactional
    @PutMapping(path = "/{leaderID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateLeaderById(@PathVariable Long leaderID, @RequestBody Leader leader){
        leaderRepository.findById(leaderID).orElseThrow(()-> new BadRequestException("bad request"));
        leader.setId(leaderID);
        leaderRepository.save(leader);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    @DeleteMapping(path = "/{leaderID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LeaderDTO deleteLeaderById(@PathVariable Long leaderID){
        Leader leader = leaderRepository.findById(leaderID).orElseThrow(()-> new BadRequestException("bad request"));
        LeaderDTO leaderDTO = new LeaderDTO(leader);
        Klass klass = leader.getKlass();
        leaderRepository.delete(leader);
        if(klass!=null) klass.setLeader(null);
        return leaderDTO;
    }
}
