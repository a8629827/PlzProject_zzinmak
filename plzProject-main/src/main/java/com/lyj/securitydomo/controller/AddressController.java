package com.lyj.securitydomo.controller;

import com.lyj.securitydomo.domain.Address;
import com.lyj.securitydomo.dto.AddressDTO;
import com.lyj.securitydomo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final ModelMapper modelMapper;

    // 새로운 주소 추가
    @PostMapping("/add")
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        Address savedAddress = addressService.addAddress(address);
        return new ResponseEntity<>(modelMapper.map(savedAddress, AddressDTO.class), HttpStatus.CREATED);
    }

    // 기존 주소 업데이트
    @PutMapping("/update/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        Address updatedAddress = addressService.updateAddress(addressId, address);
        return new ResponseEntity<>(modelMapper.map(updatedAddress, AddressDTO.class), HttpStatus.OK);
    }

    // ID로 주소 삭제
    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 특정 사용자 ID로 주소 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressDTO>> getAddressesByUserId(@PathVariable Long userId) {
        List<AddressDTO> addresses = addressService.getAddressesByUserId(userId);
        List<AddressDTO> addressDTOs = addresses.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(addressDTOs, HttpStatus.OK);
    }
}
