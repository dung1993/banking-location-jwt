package com.cg.service.locationRegion;

import com.cg.model.LocationRegion;
import com.cg.repository.LocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class LocationRegionServiceImpl implements ILocationRegionService{

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Override
    public List<LocationRegion> findALl() {
        return null;
    }

    @Override
    public Optional<LocationRegion> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public LocationRegion save(LocationRegion locationRegion) {
        return locationRegionRepository.save(locationRegion);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(LocationRegion locationRegion) {

    }
}
