package com.example.agent.service;

import com.example.agent.exception.AgentDuplicatedException;
import com.example.agent.exception.AgentNotFoundException;
import com.example.agent.model.Agent;
import com.example.agent.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {
    private  final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    //return list of agents
    public List<Agent> getAllAgent(){
        return agentRepository.findAll();
    }
    //return agent by email
    public Agent getAgentByEmail(String email){
        return  agentRepository.findAgentByEmail(email);
    }
    //add agent
    public Agent addAgent(Agent agent) throws AgentDuplicatedException {
        Agent agentFromDB = agentRepository.findById(agent.getId()).orElse(null);
        if (agentFromDB != null)
            throw new AgentDuplicatedException(agent.getId());
        return agentRepository.save(agent);
    }
    //delete agent
    public Long deleteAgent(Long id) throws AgentNotFoundException {
        Agent agentFromDB = agentRepository.findById(id).orElse(null);
        if (agentFromDB == null)
            throw new AgentNotFoundException("aucun Agent");
        agentRepository.delete(agentFromDB);
        return id;
    }

    public Agent updateAgent(Agent agent) throws AgentNotFoundException {
        Agent agentFromDB = agentRepository.findById(agent.getId()).orElse(null);
        if (agentFromDB == null)
            throw new AgentNotFoundException("aucun agent avec cette id");
        agent.setId(agentFromDB.getId());
        return agentRepository.save(agent);
    }





}
