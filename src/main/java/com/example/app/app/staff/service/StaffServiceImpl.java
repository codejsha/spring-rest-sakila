package com.example.app.app.staff.service;

import com.example.app.app.staff.domain.dto.StaffDetailsDto;
import com.example.app.app.staff.domain.dto.StaffDto;
import com.example.app.app.staff.domain.mapper.StaffMapper;
import com.example.app.app.staff.repository.StaffRepository;
import com.example.app.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Override
    @Transactional(readOnly = true)
    public List<StaffDto.Staff> getStaffList(Pageable pageable) {
        var list = staffRepository.findAll(pageable);
        return staffMapper.mapToDtoList(list);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StaffDto.Staff> getStaff(Integer staffId) {
        var entity = staffRepository.findById(staffId).orElseThrow(() ->
                new ResourceNotFoundException("Staff not found with id '" + staffId + "'"));
        return Optional.of(staffMapper.mapToDto(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StaffDetailsDto.StaffDetails> getStaffDetailsList() {
        return staffRepository.findAllStaffDetailsList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StaffDetailsDto.StaffDetails> getStaffDetails(Integer staffId) {
        var model = staffRepository.findStaffDetailsById(staffId);
        if (model.isEmpty()) {
            throw new ResourceNotFoundException("Staff not found with id '" + staffId + "'");
        }
        return model;
    }

    @Override
    @Transactional
    public StaffDto.Staff addStaff(StaffDto.StaffRequest model) {
        var savedEntity = staffRepository.save(staffMapper.mapToEntity(model));
        return staffMapper.mapToDto(savedEntity);
    }

    @Override
    @Transactional
    public StaffDto.Staff updateStaff(Integer staffId, StaffDto.StaffRequest model) {
        var entity = staffRepository.findById(staffId).orElseThrow(() ->
                new ResourceNotFoundException("Staff not found with id '" + staffId + "'"));
        entity.update(staffMapper.mapToEntity(model));
        return staffMapper.mapToDto(entity);
    }

    @Override
    @Transactional
    public void removeStaff(Integer staffId) {
        staffRepository.deleteById(staffId);
    }
}
