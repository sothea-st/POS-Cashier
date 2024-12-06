package com.example.pos.system.layer.service.companyService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.pos.system.constant.JavaStorage;
import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.domain.settings.Company;
import com.example.pos.system.domain.general.FileStore;
import com.example.pos.system.layer.repository.FileStoreRepository;
import com.example.pos.system.layer.repository.companyRepository.CompanyRepository;
import com.example.pos.system.constant.util.exception.customeException.JavaNotFoundByIdGiven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
 
@Service
public class CompanyService {
     @Autowired
     private CompanyRepository repo;

     @Autowired
     private FileStoreRepository fileStoreRepo;

     @Autowired
     private HttpSession session;

     public Company addCompany(Company c, MultipartFile file) throws IOException {
          Object createBy = session.getAttribute(JavaConstant.userId);

          Company company = new Company();
          company.setCompanyName(c.getCompanyName());
          company.setAddress(c.getAddress());
          company.setContact(c.getContact());
          company.setCreateBy((Integer)createBy);
          company.setVattin(c.getVattin());
          if( file == null || file.isEmpty() ) {
               company.setPhoto(JavaConstant.defaultNameImage);
          } else {
               // JavaStorage.storeImage(file); for save image to path assets/product in project
               String fileName = JavaStorage.setFileName(file.getOriginalFilename());
               company.setPhoto(fileName);
               
               FileStore f = new FileStore(fileName, fileName, file.getContentType(), file.getBytes());
               fileStoreRepo.save(f); // save data to pos_file table
          }
          repo.save(company);
          return company;
     }

     public List<Company> getCompany(){
          List<Company> list = repo.getCompany();
          return list;
     }

     public Company getCompanyById(int id) {
          Company dataCompany = repo.getCompanyById(id);
          if( dataCompany == null ) throw new JavaNotFoundByIdGiven();
          return dataCompany;
     }

     public void deleteCompany(int id,Company com) {
          Optional<Company> data = repo.findById(id);
          Company c = data.get();
          c.setStatus(com.isStatus());
          c.setDeleted(com.isDeleted());
          repo.save(c);
     }

     public Company updateCompany(int id , Company c , MultipartFile file) throws IOException{
          Optional<Company> data = repo.findById(id);
          Company com = data.get();
          com.setCompanyName(c.getCompanyName());
          com.setAddress(c.getAddress());
          com.setContact(c.getContact());
          com.setVattin(c.getVattin());
          String photo = com.getPhoto();
          if( Objects.equals(photo,JavaConstant.defaultNameImage) ) photo = "";

          if( file == null || file.isEmpty() ) {
               if( photo.isEmpty() ) com.setPhoto(JavaConstant.defaultNameImage);
          } else {
               String fileName = JavaStorage.setFileName(file.getOriginalFilename());
               com.setPhoto(fileName);
               // save data to table pos_file
               FileStore f = new FileStore(fileName, fileName, file.getContentType(), file.getBytes());
               fileStoreRepo.save(f);
          }
          repo.save(com);
          return com;
     }

}
