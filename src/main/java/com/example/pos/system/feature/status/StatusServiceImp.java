package com.example.pos.system.feature.status;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.pos.system.domain.Status;
import com.example.pos.system.feature.status.dto.StatusRequest;
import com.example.pos.system.feature.status.dto.StatusResponse;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class StatusServiceImp implements StatusService {

  private final StatusRepository statusRepository;
  private String idNotFound = "Id has not been found .";
  private String nameAlreadyExisted = "The Status Name is already existed.";

  /*
   * read status by id
   * required paramater id
   */
  @Override
  public StatusResponse readById(Integer id) {
    Status status = statusRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
    return mStatusResponse(status);

  }

  /*
   * read all status
   * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0
   * value was given from controller
   */
  @Override
  public JavaCollectionResponse<?> read(Integer pageSize, Integer pageNumber) {
    List<StatusResponse> data = null;

    if (pageNumber == null && pageSize == null) {
      data = statusRepository.findByStatusTrueAndIsDeletedFalse().stream()
          .map(this::mStatusResponse)
          .toList();
      return JavaCollectionResponse.builder()
          .count(data.size())
          .data(data)
          .build();
    } else {
      Sort sortById = Sort.by(Sort.Direction.DESC, "id");
      PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
      Page<Status> pages = statusRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

      List<StatusResponse> content = pages.getContent()
          .stream()
          .map(c -> mStatusResponse(c))
          .toList();

      return JavaCollectionResponse.builder()
          .count(pages.getTotalElements())
          .data(content)
          .build();
    }

  }

  /*
   * create new status
   * required paramater statusRequest
   */
  @Override
  public StatusResponse create(StatusRequest statusRequest) {

    // validate name already exist
    if (statusRepository.existsByStatusName(statusRequest.statusName())) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, nameAlreadyExisted);
    }

    Status status = new Status();
    status.setStatusName(statusRequest.statusName());
    statusRepository.save(status);
    return mStatusResponse(status);
  }

  /*
   * update status by id
   * required paramater id , statusRequest
   */
  @Override
  public StatusResponse update(Integer id, StatusRequest statusRequest) {
    Status status = statusRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

    if (!statusRequest.statusName().equals(status.getStatusName())) {
      // validate name already exist
      if (statusRepository.existsByStatusName(statusRequest.statusName())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, nameAlreadyExisted);
      }
    }

    status.setStatusName(statusRequest.statusName());
    statusRepository.save(status);
    return mStatusResponse(status);
  }

  /*
   * helper method mStatusResponse
   */
  private StatusResponse mStatusResponse(Status status) {
    return StatusResponse.builder()
        .id(status.getId())
        .statusName(status.getStatusName())
        .status(status.getStatus())
        .isDeleted(status.getIsDeleted())
        .build();
  }

  /*
   * delete status by id
   */
  @Override
  public void delete(Integer id) {
    Status status = statusRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
    status.setStatus(false);
    status.setIsDeleted(true);
    statusRepository.save(status);
  }

  /*
   * read search status
   * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0
   * value was given from controller
   */
  @Override
  public JavaCollectionResponse<?> search(Integer pageSize, Integer pageNumber, String searchValue) {
    List<StatusResponse> data = null;
    
    if (pageNumber == null && pageSize == null) {
      data = statusRepository.searchByStatusName(searchValue).stream()
          .map(this::mStatusResponse)
          .toList();
      return JavaCollectionResponse.builder()
          .count(data.size())
          .data(data)
          .build();
    } else {
      Sort sortById = Sort.by(Sort.Direction.DESC, "id");
      PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
      Page<Status> pages = statusRepository.searchByStatusName(pageRequest, searchValue);

      List<StatusResponse> content = pages.getContent()
          .stream()
          .map(c -> mStatusResponse(c))
          .toList();

      return JavaCollectionResponse.builder()
          .count(pages.getTotalElements())
          .data(content)
          .build();
    }
  }

}
