package com.cg.model.dto;

import com.cg.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationRegionDTO implements Validator {

    private Long id;
    private String provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;

    @NotEmpty(message = "Address is required")
    private String address;

    public LocationRegion toLocationRegion() {
        return new LocationRegion()
                .setId(id)
                .setProvinceId(provinceId)
                .setProvinceName(provinceName)
                .setDistrictId(districtId)
                .setDistrictName(districtName)
                .setWardId(wardId)
                .setWardName(wardName)
                .setAddress(address);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LocationRegionDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LocationRegionDTO locationRegionDTO = (LocationRegionDTO) target;

        String address = locationRegionDTO.getAddress();

        if (address.length() == 0) {
            errors.rejectValue("address", "address.null", "Address is required");
        }
    }
}
