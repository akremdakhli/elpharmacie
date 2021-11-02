package ELpharmacie.Service;

import ELpharmacie.entities.Pool;

import java.util.List;

public interface PoolManagerInterface {
    List<Pool> retrieveAllPool();
    Pool addPool(Pool Po);
    void deletePool(Long id);
    void deletePool(String id);
    Pool updatePool(Pool Po);
    public Pool  FindPoolById(Long id);
    public Pool FindPoolById(String id);
}
