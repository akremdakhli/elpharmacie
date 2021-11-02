package ELpharmacie.Service;


import ELpharmacie.entities.Client;
import ELpharmacie.entities.WorkField;

import java.util.List;

public interface ClientManagerInterface {
	
    List<Client> retrieveAllClient();
    void deleteClientById(Long id);
    void deleteClientById(String id);
    Client updateClient(Client Cl);
    Client FindClientById(long id);
    Client FindClientById(String id);
    long getNombreClient();
    Client SignUpClient(Client user);
    long getNBClientsbysubmonth(String Month);
    Client addClient(Client Cl);
    long getNbClientByWorkfield(WorkField field);
}
