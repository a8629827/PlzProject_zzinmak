package com.lyj.securitydomo.service;

import com.lyj.securitydomo.domain.Address;
import com.lyj.securitydomo.dto.AddressDTO;
import com.lyj.securitydomo.repository.AddressRepository;
import com.lyj.securitydomo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public Address addAddress(Address address) {
        return null;
    }

    @Override
    public Address updateAddress(Long addressId, Address address) {
        return null;
    }

    @Override
    public void deleteAddress(Long addressId) {

    }

    @Override
    public List<AddressDTO> getAddressesByUserId(Long userId) {
        return addressRepository.findByUserId(userId).stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .collect(Collectors.toList());
    }
}
