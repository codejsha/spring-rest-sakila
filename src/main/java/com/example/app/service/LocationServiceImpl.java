package com.example.app.service;

import com.example.app.exception.ResourceNotFoundException;
import com.example.app.model.internal.AddressModel;
import com.example.app.model.internal.CityModel;
import com.example.app.model.mapping.mapper.AddressMapper;
import com.example.app.model.mapping.mapper.CityMapper;
import com.example.app.model.request.AddressRequestModel;
import com.example.app.model.request.CityRequestModel;
import com.example.app.repository.AddressRepository;
import com.example.app.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final AddressMapper addressMapper;
    private final CityMapper cityMapper;

    @Override
    public List<AddressModel> getAllAddresses() {
        var result = addressRepository.findAll();
        return addressMapper.mapToDtoList(result);
    }

    @Override
    public Optional<AddressModel> getAddressById(String id) {
        var result = addressRepository.findById(Integer.valueOf(id));
        var entity = result.orElseThrow(() ->
                new ResourceNotFoundException("Address not found with id '" + id + "'"));
        return Optional.of(addressMapper.mapToDto(entity));
    }

    @Override
    public List<AddressModel> getAllAddressesDetail() {
        return addressRepository.findAllAddressesDetail();
    }

    @Override
    public Optional<AddressModel> getAddressDetailById(String id) {
        var result = addressRepository.findAddressDetailById(Integer.valueOf(id));
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Address not found with id '" + id + "'");
        }
        return result;
    }

    @Override
    public AddressModel addAddress(AddressRequestModel model) {
        var entity = addressMapper.mapToEntity(model);
        var result = addressRepository.save(entity);
        return addressMapper.mapToDto(result);
    }

    @Override
    public AddressModel updateAddress(String id, AddressRequestModel model) {
        var result = addressRepository.findById(Integer.valueOf(id));
        var entity = result.orElseThrow(() ->
                new ResourceNotFoundException("Address not found with id '" + id + "'"));
        addressMapper.updateEntity(model, entity);
        var updated = addressRepository.save(entity);
        return addressMapper.mapToDto(updated);
    }

    @Override
    public void deleteAddressById(String id) {
        addressRepository.deleteById(Integer.valueOf(id));
    }

    @Override
    public List<CityModel> getAllCities() {
        var result = cityRepository.findAll();
        return cityMapper.mapToDtoList(result);
    }

    @Override
    public Optional<CityModel> getCityById(String id) {
        var result = cityRepository.findById(Integer.valueOf(id));
        var entity = result.orElseThrow(() ->
                new ResourceNotFoundException("City not found with id '" + id + "'"));
        return Optional.of(cityMapper.mapToDto(entity));
    }

    @Override
    public List<CityModel> getAllCitiesDetail() {
        return cityRepository.findAllCitiesDetail();
    }

    @Override
    public Optional<CityModel> getCityDetailById(String id) {
        var result = cityRepository.findCityDetailById(Integer.valueOf(id));
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("City not found with id '" + id + "'");
        }
        return result;
    }

    @Override
    public CityModel addCity(CityRequestModel model) {
        var entity = cityMapper.mapToEntity(model);
        var result = cityRepository.save(entity);
        return cityMapper.mapToDto(result);
    }

    @Override
    public CityModel updateCity(String id, CityRequestModel model) {
        var result = cityRepository.findById(Integer.valueOf(id));
        var entity = result.orElseThrow(() ->
                new ResourceNotFoundException("City not found with id '" + id + "'"));
        cityMapper.updateEntity(model, entity);
        return cityMapper.mapToDto(cityRepository.save(entity));
    }

    @Override
    public void deleteCityById(String id) {
        cityRepository.deleteById(Integer.valueOf(id));
    }
}
