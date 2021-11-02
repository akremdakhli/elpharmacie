package ELpharmacie.Service;


import ELpharmacie.entities.Donation;

import java.util.List;

public interface DonationManagerInterface {
    List<Donation> retrieveAllDonation();
  //  void addDonation(Donation Do,long idEvent,long idOrder);
    void deleteDonation(Long id);
    void deleteDonation(String id);
    Donation updateDonation(Donation Do);
    public Donation FindDonationById(Long id);
    public Donation FindDonationById(String id);
}
