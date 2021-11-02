package ELpharmacie.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id_event;

	private String eventName;
	private double raisedAmount_event;
	private String place_event;
	private String target_event;
	//@Temporal (TemporalType.DATE)
	private LocalDateTime date_event;
	private int nombreplace;
	private int nombredeplaceinitiale;




	/*@OneToMany(cascade = CascadeType.PERSIST, mappedBy="event")
	private List<Donation> donation;*/

	@OneToMany(mappedBy="event",
			cascade = {CascadeType.ALL, CascadeType.REMOVE})
	private List<Donation> donation = new ArrayList<>();

	
	/*@ManyToOne
	Pool pool;*/

	/*@ManyToMany(cascade = CascadeType.ALL, mappedBy="event")
	private List<Client> clients;*/

	@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	/*@JoinTable(
			name = "client_event",
			joinColumns = @JoinColumn(name = "event_id"),
			inverseJoinColumns = @JoinColumn(name = "client_id"))*/
	private List<Client> clients;

	public void addclients(List<Client> addedCleints){

		addedCleints.forEach(client -> {

			if (clientExist(client.getIdUser())){
				throw new IllegalStateException("client Not Exist");
			}
			clients.add(client);
		});
	}

	public void addclients(Client addedClients){
		/*if (clientExist(addedClients.getIdUser()))
		{throw new IllegalStateException("Client Not even Exist");}*/
		clients.add(addedClients);

	}
	private  boolean clientExist ( Long idClient ){
		for ( Client client:clients   ){
			if ( client.getIdUser()!=idClient)
				return true  ;

		}
		return  false ;
	}

	//public void addClient (List<Client> adeddclient) {
		//if(clients == null)
			//clients = new ArrayList<>();
		//adeddclient.forEach(client -> {clients.add(client); });

	public boolean addclient(Client emp) {
		if(clients == null)
			clients = new ArrayList<>();

		return clients.add(emp);
	}

	public boolean removeclient(Client emp) {
		if(clients == null)
			clients = new ArrayList<>();

		return clients.remove(emp);
	}


}







