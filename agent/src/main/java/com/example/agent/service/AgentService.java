package com.example.agent.service;



import com.example.agent.exception.AgentDuplicatedException;
import com.example.agent.exception.AgentNotFoundException;
import com.example.agent.model.Agent;
import com.example.agent.repository.AgentRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public Agent getAgentById(Long id){
        return  agentRepository.findAgentById(id);
    }
    //add agent
    // public void addAgent(Agent agent) throws AgentDuplicatedException

    //   Agent agentFromDB = agentRepository.findById(agent.getId()).orElse(null);
    //   if (agentFromDB != null)
        	 //       throw new AgentDuplicatedException(agent.getId());
        //   agent.setPassword(new BCryptPasswordEncoder().encode(agent.getPassword()));
        //      return agentRepository.save(agent);
        //  }
    
    
    public Agent addAgent(Agent agent) throws AgentDuplicatedException
	{
		if(agentRepository.findAgentByEmail(agent.getEmail()) != null) throw new AgentDuplicatedException(" Agent existe deja");
		
		String password= agent.getPassword();
		
		agent.setPassword(new BCryptPasswordEncoder().encode(agent.getPassword()));
		agent.setRole("Agent");
		
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

 //   public Agent updateAgent(Long id,Agent agent) throws AgentNotFoundException {
   //     Agent agentFromDB = agentRepository.findById(id).orElse(null);
     //   if (agentFromDB == null)
       //     throw new AgentNotFoundException("aucun agent avec cette id");
       // agent.setPassword(new BCryptPasswordEncoder().encode(agent.getPassword()));
       // return agentRepository.save(agentFromDB);
    //}
    
    
    public Agent updateAgent(Long id,Agent agent) throws AgentNotFoundException, AgentDuplicatedException
	{
		Agent updated = agentRepository.findById(id).orElseThrow(() -> new AgentNotFoundException("Aucun agent avec l'id "+id+" trouvé"));
		
		//verifier l'unicité du nouveau username
		if(agentRepository.findAgentByEmail(agent.getEmail()) != null) throw new AgentDuplicatedException(" Agent existe deja");


		if(agent.getNom()!=null && !agent.getNom().isEmpty()) updated.setNom(agent.getNom());
		if(agent.getPrenom()!=null && !agent.getPrenom().isEmpty()) updated.setPrenom(agent.getPrenom());
		if(agent.getEmail()!=null && !agent.getEmail().isEmpty()) updated.setEmail(agent.getEmail());
		if(agent.getPassword()!=null && !agent.getPassword().isEmpty()) updated.setPassword(new BCryptPasswordEncoder().encode(agent.getPassword()));
		
		return agentRepository.save(updated);

	}



}
