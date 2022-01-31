package transfertnational.example.transfertnational.repository;
import transfertnational.example.transfertnational.model.TransfertNational;
import java.util.List;

public interface TransfertRepositoryCustom {

	    List<TransfertNational> findTransfertNationalByIdAgentAndIdClientAndPiAndNumGsmAndCodeTransfertAndStatus(Long idAgent,Long idClient,String pi,String numGsm ,
	    		 String codeTransfert,String status);
	
}
