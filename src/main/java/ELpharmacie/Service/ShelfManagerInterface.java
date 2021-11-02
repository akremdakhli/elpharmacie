package ELpharmacie.Service;


import ELpharmacie.entities.Shelf;

import java.util.List;

public interface ShelfManagerInterface {
    List<Shelf> retrieveAllShelf();
    Shelf addShelf(Shelf Sh);
    void deleteShelf(Long id);
    void deleteShelf(String id);
    Shelf updateShelf(Shelf Sh);
    Shelf FindShelf(Long id);
    Shelf FindShelf(String id);
    Shelf FindByTypeShelf(String type);
    void AffectProductAShelf(long shelfId, long productId);
    List<String> getAllProductsNamesByShelf(long shelfId);
    void desaffecterProductFromShelf(long shelfId, long productId);
    long selectIdshelfbytype(String type);



    }
