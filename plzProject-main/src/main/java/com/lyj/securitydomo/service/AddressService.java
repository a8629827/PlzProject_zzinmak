package com.lyj.securitydomo.service;

import com.lyj.securitydomo.domain.Address;
import com.lyj.securitydomo.dto.AddressDTO;
import com.lyj.securitydomo.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    Address addAddress(Address address);
    Address updateAddress(Long addressId, Address address);
    void deleteAddress(Long addressId);
    List<AddressDTO> getAddressesByUserId(Long userId);
}
